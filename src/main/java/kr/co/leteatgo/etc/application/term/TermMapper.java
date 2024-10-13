package kr.co.leteatgo.etc.application.term;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.co.leteatgo.etc.application.term.dto.CreateTermRequest;
import kr.co.leteatgo.etc.application.term.dto.TermResponse;
import kr.co.leteatgo.etc.application.term.dto.UpdateTermRequest;
import kr.co.leteatgo.etc.domain.term.Term;

@Component
public class TermMapper {

	public Term create(CreateTermRequest request, String contentValue, Term parentTerm) {
		return Term.builder()
			.type(request.type())
			.title(request.title())
			.keyName(request.key())
			.content(contentValue)
			.required(request.required())
			.sort(request.sort())
			.parent(parentTerm)
			.build();
	}

	public Term update(Term term, Term parentTerm, UpdateTermRequest request) {
		return Term.builder()
			.id(term.getId())
			.type(term.getType())
			.parent(parentTerm)
			.title(request.title())
			.required(request.required())
			.sort(request.sort())
			.content(request.content())
			.build();
	}

	public TermResponse termToResponse(Term term) {
		List<TermResponse> child = term.getChild()
			.stream()
			.sorted(Comparator.comparingInt(Term::getSort))
			.map(this::termToResponse)
			.toList();
		return TermResponse.builder()
			.id(term.getId())
			.title(term.getTitle())
			.key(term.getKeyName())
			.content(term.getContent())
			.required(term.getRequired())
			.sort(term.getSort())
			.child(child)
			.build();
	}
}
