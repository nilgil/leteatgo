package kr.co.leteatgo.etc.domain.post;


import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainBannerRepository extends JpaRepository<MainBanner, Long> {

  Slice<MainBanner> findSliceBy(Pageable pageable);

  Optional<MainBanner> findByBannerTypeAndBannerId(MainBannerType bannerType, Long bannerId);
}
