package kr.co.leteatgo.etc.domain.post;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

  Slice<Notice> findSliceByVisibleIsTrue(Pageable pageable);

  Slice<Notice> findSliceBy(Pageable pageable);
}
