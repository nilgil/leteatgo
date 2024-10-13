package kr.co.leteatgo.etc.application.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.etc.application.post.dto.DetailEventResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleEventResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteEventRequest;
import kr.co.leteatgo.etc.application.storage.AwsS3Service;
import kr.co.leteatgo.etc.domain.post.Event;
import kr.co.leteatgo.etc.domain.post.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	private final AwsS3Service awsS3Service;

	private static Integer getUploadImagesCount(WriteEventRequest request) {
		Integer imagesCount = request.contentImagesCount();
		return request.hasBannerImage() ? imagesCount + 1 : imagesCount;
	}

	@Override
	public Event getEventById(Long eventId) {
		return eventRepository.findById(eventId)
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
	}

	@Override
	public Slice<SimpleEventResponse> getSimpleEvents(Pageable pageable) {
		return eventRepository.findSliceByVisibleIsTrue(pageable)
			.map(SimpleEventResponse::from);
	}

	@Override
	public DetailEventResponse getDetailEvent(Long eventId) {
		Event event = eventRepository.findEventWithContentImages(eventId)
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
		if (!event.visible()) {
			throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE, "cannot access invisible event");
		}
		return DetailEventResponse.from(event);
	}

	@Transactional
	@Override
	public PreSignedUrls writeEvent(WriteEventRequest request) {
		Integer imagesCount = getUploadImagesCount(request);
		PreSignedUrls preSignedUrls = awsS3Service.createPreSignedUrls(
			AwsS3Folder.EVENT_IMAGES, imagesCount);

		PreSignedUrl bannerImagePsu = preSignedUrls.get(0).labeling("bannerImage");
		PreSignedUrls contentImagePsus = preSignedUrls.boundCopy(1, preSignedUrls.size())
			.numbering("contentImage");

		Event event = request.toEntity(bannerImagePsu.imageUrl(), contentImagePsus.imageUrls());
		eventRepository.save(event);

		return PreSignedUrls.of(bannerImagePsu).merge(contentImagePsus);
	}

	@Transactional
	@Override
	public void changeEventVisible(Long eventId, Boolean visible) {
		Event event = getEventById(eventId);
		event.changeVisible(visible);
	}

	@Transactional
	@Override
	public void deleteEvent(Long eventId) {
		Event event = getEventById(eventId);
		eventRepository.delete(event);
	}
}
