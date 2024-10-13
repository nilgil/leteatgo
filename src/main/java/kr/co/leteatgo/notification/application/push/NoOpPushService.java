package kr.co.leteatgo.notification.application.push;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import kr.co.leteatgo.notification.application.push.dto.PushOneToManyRequest;
import kr.co.leteatgo.notification.application.push.dto.PushOneToOneRequest;
import kr.co.leteatgo.notification.domain.DeviceToken;

@Profile("local")
@Component
public class NoOpPushService implements PushService {

	@Override
	public String pushToOne(PushOneToOneRequest reqDto) {
		return "";
	}

	@Override
	public List<DeviceToken> pushToMany(PushOneToManyRequest reqDto) {
		return List.of();
	}

	@Override
	public List<DeviceToken> manyPushes(List<PushOneToOneRequest> reqDtos) {
		return List.of();
	}

	@Override
	public String pushByTopic(String topic) {
		return "";
	}

	@Override
	public String pushByTopicCondition(String topicCondition) {
		return "";
	}
}
