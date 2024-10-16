package kr.co.leteatgo.etc.application.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.etc.application.post.dto.DetailEventResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleEventResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteEventRequest;
import kr.co.leteatgo.etc.domain.post.Event;

public interface EventService {

	Event getEventById(Long eventId);

	Slice<SimpleEventResponse> getSimpleEvents(Pageable pageable);

	DetailEventResponse getDetailEvent(Long eventId);

	PreSignedUrls writeEvent(WriteEventRequest request);

	void changeEventVisible(Long eventId, Boolean visible);

	void deleteEvent(Long eventId);
}
