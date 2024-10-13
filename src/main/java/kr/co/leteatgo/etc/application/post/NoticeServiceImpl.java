package kr.co.leteatgo.etc.application.post;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.etc.application.post.dto.DetailNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteNoticeRequest;
import kr.co.leteatgo.etc.application.storage.AwsS3Service;
import kr.co.leteatgo.etc.domain.post.Notice;
import kr.co.leteatgo.etc.domain.post.NoticeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
	private final AwsS3Service awsS3Service;

	@Override
	public Notice getNoticeById(Long noticeId) {
		return noticeRepository.findById(noticeId).orElseThrow(
			() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
	}

	@Override
	public Slice<SimpleNoticeResponse> getSimpleNotices(Pageable pageable) {
		return noticeRepository.findSliceByVisibleIsTrue(pageable)
			.map(SimpleNoticeResponse::from);
	}

	@Override
	public DetailNoticeResponse getDetailNotice(Long noticeId) {
		Notice notice = getNoticeById(noticeId);
		if (!notice.visible()) {
			throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE, "cannot access invisible notice");
		}
		return DetailNoticeResponse.from(notice);
	}

	@Override
	public PreSignedUrls writeNotice(WriteNoticeRequest request) {

		if (!request.hasBannerImage()) {
			Notice notice = request.toEntity(null);
			noticeRepository.save(notice);
			return new PreSignedUrls(List.of());
		}

		PreSignedUrl preSignedUrl = awsS3Service.createPreSignedUrls(AwsS3Folder.NOTICE_IMAGES, 1)
			.get(0).labeling("noticeBannerImage");
		Notice notice = request.toEntity(preSignedUrl.imageUrl());
		noticeRepository.save(notice);

		return PreSignedUrls.of(preSignedUrl);
	}

	@Override
	public void changeNoticeVisible(Long noticeId, Boolean visible) {
		Notice notice = getNoticeById(noticeId);
		notice.changeVisible(visible);
	}

	@Override
	public void deleteNotice(Long noticeId) {
		Notice notice = getNoticeById(noticeId);
		noticeRepository.delete(notice);
	}
}
