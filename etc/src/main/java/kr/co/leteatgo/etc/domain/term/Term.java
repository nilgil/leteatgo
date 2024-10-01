package kr.co.leteatgo.etc.domain.term;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.co.leteatgo.etc.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Term extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private TermType type;
  private String title;
  private String key;
  @Column(columnDefinition = "TEXT")
  private String content;
  private Boolean required;
  private Integer sort;

  @ManyToOne
  private Term parent;

  @OneToMany(mappedBy = "parent")
  private List<Term> child;

  @Builder
  private Term(Long id, TermType type, String title, String key, String content, Boolean required, Integer sort,
      Term parent) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.key = key;
    this.content = content;
    this.required = required;
    this.sort = sort;
    this.parent = parent;
  }
}
