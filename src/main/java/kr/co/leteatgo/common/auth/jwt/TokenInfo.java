package kr.co.leteatgo.common.auth.jwt;

import java.util.List;
import java.util.UUID;

public class TokenInfo implements Account {

	private final TokenType tokenType;
	private final AccountType accountType;
	private final UUID id;
	private final List<String> roles;

	public TokenInfo(String tokenType, String accountType, String id, List<String> roles) {
		this.tokenType = TokenType.valueOf(tokenType);
		this.accountType = AccountType.valueOf(accountType);
		this.id = UUID.fromString(id);
		this.roles = roles;
	}

	public TokenType tokenType() {
		return this.tokenType;
	}

	@Override
	public AccountType accountType() {
		return this.accountType;
	}

	@Override
	public UUID id() {
		return this.id;
	}

	@Override
	public List<String> roles() {
		return this.roles;
	}
}
