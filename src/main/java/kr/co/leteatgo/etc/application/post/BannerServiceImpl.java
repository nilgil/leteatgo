package kr.co.leteatgo.etc.application.post;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.etc.application.post.dto.BannerResponse;
import kr.co.leteatgo.etc.application.post.dto.CreateBannerRequest;
import kr.co.leteatgo.etc.domain.post.Event;
import kr.co.leteatgo.etc.domain.post.MainBanner;
import kr.co.leteatgo.etc.domain.post.MainBannerRepository;
import kr.co.leteatgo.etc.domain.post.MainBannerType;
import kr.co.leteatgo.etc.domain.post.Notice;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

	private final MainBannerRepository mainBannerRepository;
	private final EventService eventService;
	private final NoticeService noticeService;

	@Override
	public Slice<BannerResponse> getBanners(Pageable pageable) {
		return mainBannerRepository.findSliceBy(pageable)
			.map(BannerResponse::from);
	}

	@Transactional
	@Override
	public void addBanner(CreateBannerRequest request) {
		if (MainBannerType.EVENT == request.type()) {
			addEventBanner(request.id());
		} else {
			addNoticeBanner(request.id());
		}
	}

	@Transactional
	@Override
	public void resetBanners(List<CreateBannerRequest> requests) {
		mainBannerRepository.deleteAll();
		Collections.reverse(requests);
		requests.forEach(this::addBanner);
	}

	private void addEventBanner(Long eventId) {
		Event event = eventService.getEventById(eventId);
		MainBanner mainBanner = new MainBanner(event);
		mainBannerRepository.save(mainBanner);
	}

	private void addNoticeBanner(Long noticeId) {
		Notice notice = noticeService.getNoticeById(noticeId);
		MainBanner mainBanner = new MainBanner(notice);
		mainBannerRepository.save(mainBanner);
	}
}
