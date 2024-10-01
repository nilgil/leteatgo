package kr.co.leteatgo.store.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import kr.co.leteatgo.store.domain.foodtype.FoodType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {

  @Query("select f from FoodType f order by f.sort asc")
  List<FoodType> getAllOrderBySort();

  @EntityGraph(attributePaths = "images")
  Optional<FoodType> findByName(String name);
}
