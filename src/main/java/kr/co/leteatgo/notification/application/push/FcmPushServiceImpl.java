package kr.co.leteatgo.notification.application.push;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.notification.application.push.dto.PushInfo;
import kr.co.leteatgo.notification.application.push.dto.PushOneToManyRequest;
import kr.co.leteatgo.notification.application.push.dto.PushOneToOneRequest;
import kr.co.leteatgo.notification.domain.DeviceToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Profile("!local")
@Service
public class FcmPushServiceImpl implements PushService {

	private final FirebaseMessaging fcm;

	/**
	 * 특정 기기에 푸시 보낼 때 사용
	 *
	 * @return Message Id
	 */
	@Override
	public String pushToOne(PushOneToOneRequest request) {
		String deviceToken = request.deviceToken();
		PushInfo pushInfo = request.pushInfo();
		return sendMessage(makeMessage(deviceToken, pushInfo));
	}

	private Message makeMessage(String deviceToken, PushInfo pushInfo) {
		Notification notification = FcmMessageFactory.createNotification(pushInfo);
		return FcmMessageFactory.createMessage(deviceToken, notification, pushInfo.data());
	}

	private String sendMessage(Message message) {
		try {
			return fcm.send(message);
		} catch (FirebaseMessagingException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, "Firebase Push 발신 중 오류가 발생하였습니다.");
		}
	}

	/**
	 * 단체에게 같은 내용의 푸시 보낼 때 사용
	 *
	 * @return Failed Device Tokens
	 */
	@Override
	public List<DeviceToken> pushToMany(PushOneToManyRequest reqDto) {
		MulticastMessage message = makeMulticastMessage(reqDto);
		BatchResponse response = sendMulticastMessage(message);
		if (isSuccessful(response)) {
			return new ArrayList<>();
		}
		return getFailedTokens(reqDto.deviceTokens(), response.getResponses());
	}

	private MulticastMessage makeMulticastMessage(PushOneToManyRequest reqDto) {
		PushInfo pushInfo = reqDto.pushInfo();
		Notification notification = FcmMessageFactory.createNotification(pushInfo);
		return FcmMessageFactory.createMulticastMessage(reqDto.deviceTokens(), notification,
			pushInfo.data());
	}

	private BatchResponse sendMulticastMessage(MulticastMessage message) {
		try {
			return fcm.sendMulticast(message);
		} catch (FirebaseMessagingException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, "Firebase Push 발신 중 오류가 발생하였습니다.");
		}
	}

	private boolean isSuccessful(BatchResponse response) {
		return 0 == response.getFailureCount();
	}

	private List<DeviceToken> getFailedTokens(List<String> deviceTokens,
		List<SendResponse> responses) {
		List<DeviceToken> failedTokens = new ArrayList<>();
		for (int i = 0; i < responses.size(); i++) {
			if (responses.get(i).isSuccessful()) {
				continue;
			}
			String deviceToken = deviceTokens.get(i);
			failedTokens.add(new DeviceToken(deviceToken));
		}
		log.warn("List of tokens that caused failures: {}", failedTokens);
		return failedTokens;
	}

	/**
	 * 사용자와 사장님에게 동시에 푸시 보낼 때 사용한다. 각각의 개별 메세지들을 한번에 보내는 방식
	 *
	 * @return Failed Device Tokens
	 */
	@Override
	public List<DeviceToken> manyPushes(List<PushOneToOneRequest> requests) {
		List<Message> messages = requests.stream()
			.map(request -> makeMessage(request.deviceToken(), request.pushInfo()))
			.toList();
		BatchResponse batchResponse = sendMessages(messages);
		List<String> deviceTokens = requests.stream().map(PushOneToOneRequest::deviceToken).toList();
		return getFailedTokens(deviceTokens, batchResponse.getResponses());
	}

	private BatchResponse sendMessages(List<Message> messages) {
		try {
			return fcm.sendAll(messages);
		} catch (FirebaseMessagingException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, "Firebase Push 발신 중 오류가 발생하였습니다.");
		}
	}

	/**
	 * 토픽 구독한 기기들에 푸시
	 *
	 * @return Message Id
	 */
	@Override
	public String pushByTopic(String topic) {
		try {
			Message message = Message.builder()
				.putData("score", "850")
				.putData("time", "2:45")
				.setTopic(topic)
				.build();
			return fcm.send(message);
		} catch (FirebaseMessagingException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR,
				"Firebase Push 발신 중 오류가 발생하였습니다.");
		}
	}

	/**
	 * 토픽 조건문으로 필터링하여 푸시
	 *
	 * @return Message Id
	 */
	@Override
	public String pushByTopicCondition(String topicCondition) {
		try {
			Message message = Message.builder()
				.putData("score", "850")
				.putData("time", "2:45")
				.setCondition(topicCondition)
				.build();
			return fcm.send(message);
		} catch (FirebaseMessagingException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR,
				"Firebase Push 발신 중 오류가 발생하였습니다.");
		}
	}
}
