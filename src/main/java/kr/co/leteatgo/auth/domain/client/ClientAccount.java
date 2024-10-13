package kr.co.leteatgo.auth.domain.client;

import java.util.List;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import kr.co.leteatgo.auth.domain.common.LegAccount;
import kr.co.leteatgo.auth.domain.credential.ApiKeyCredential;
import kr.co.leteatgo.common.auth.jwt.AccountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = LegAccount.class)
@Audited(withModifiedFlag = true)
@Entity
public class ClientAccount extends LegAccount implements ApiKeyCredential {

	private String name;
	private String apiKey;

	public ClientAccount(String name) {
		super(List.of(AccountType.CLIENT.toString()));
		this.name = name;
	}

	@Override
	public AccountType accountType() {
		return AccountType.CLIENT;
	}

	@Override
	public boolean matchApiKey(String apiKey) {
		return this.apiKey.equals(apiKey);
	}

	@Override
	public void updateApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
