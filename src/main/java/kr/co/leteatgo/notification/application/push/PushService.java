package kr.co.leteatgo.notification.application.push;

import java.util.List;

import kr.co.leteatgo.notification.application.push.dto.PushOneToManyRequest;
import kr.co.leteatgo.notification.application.push.dto.PushOneToOneRequest;
import kr.co.leteatgo.notification.domain.DeviceToken;

public interface PushService {

	String pushToOne(PushOneToOneRequest reqDto);

	List<DeviceToken> pushToMany(PushOneToManyRequest reqDto);

	List<DeviceToken> manyPushes(List<PushOneToOneRequest> reqDtos);

	String pushByTopic(String topic);

	String pushByTopicCondition(String topicCondition);
}
