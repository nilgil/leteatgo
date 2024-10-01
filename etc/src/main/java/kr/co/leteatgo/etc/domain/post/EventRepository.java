package kr.co.leteatgo.etc.domain.post;


import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

  Slice<Event> findSliceByVisibleIsTrue(Pageable pageable);

  Slice<Event> findSliceBy(Pageable pageable);

  @Query("select e from Event e join fetch e.contentImages where e.id = :eventId")
  Optional<Event> findEventWithContentImages(Long eventId);
}
