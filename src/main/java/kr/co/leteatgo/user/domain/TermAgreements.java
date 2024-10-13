package kr.co.leteatgo.user.domain;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import kr.co.leteatgo.user.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = BaseEntity.class)
@Audited(withModifiedFlag = true)
@Entity
public class TermAgreements extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean serviceUse;
	private Boolean personalCollect;
	private Boolean locationBasedServiceUse;
	private Boolean electronicFinanceUse;
	private Boolean personalCollectForMarketing;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@Builder
	private TermAgreements(Boolean serviceUse, Boolean personalCollect,
		Boolean locationBasedServiceUse, Boolean electronicFinanceUse,
		Boolean personalCollectForMarketing, User user) {
		this.serviceUse = serviceUse;
		this.personalCollect = personalCollect;
		this.locationBasedServiceUse = locationBasedServiceUse;
		this.electronicFinanceUse = electronicFinanceUse;
		this.personalCollectForMarketing = personalCollectForMarketing;
		this.user = user;
	}
}
