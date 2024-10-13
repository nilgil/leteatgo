package kr.co.leteatgo.etc.application.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.etc.application.post.dto.DetailNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteNoticeRequest;
import kr.co.leteatgo.etc.domain.post.Notice;

public interface NoticeService {

	Notice getNoticeById(Long noticeId);

	Slice<SimpleNoticeResponse> getSimpleNotices(Pageable pageable);

	DetailNoticeResponse getDetailNotice(Long noticeId);

	@Transactional
	PreSignedUrls writeNotice(WriteNoticeRequest request);

	@Transactional
	void changeNoticeVisible(Long noticeId, Boolean visible);

	@Transactional
	void deleteNotice(Long noticeId);
}
