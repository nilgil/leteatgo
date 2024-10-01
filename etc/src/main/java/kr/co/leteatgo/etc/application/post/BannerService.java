package kr.co.leteatgo.etc.application.post;


import java.util.List;
import kr.co.leteatgo.etc.application.post.dto.BannerResponse;
import kr.co.leteatgo.etc.application.post.dto.CreateBannerRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BannerService {

  Slice<BannerResponse> getBanners(Pageable pageable);

  void addBanner(CreateBannerRequest reqDto);

  void resetBanners(List<CreateBannerRequest> reqList);
}
