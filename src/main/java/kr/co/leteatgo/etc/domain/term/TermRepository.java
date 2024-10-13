package kr.co.leteatgo.etc.domain.term;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {

	@EntityGraph(attributePaths = {"child"})
	List<Term> findAllByTypeAndParentIsNullOrderBySort(TermType type);
}
