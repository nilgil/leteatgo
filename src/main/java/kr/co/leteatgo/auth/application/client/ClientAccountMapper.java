package kr.co.leteatgo.auth.application.client;

import org.springframework.stereotype.Component;

import kr.co.leteatgo.auth.application.client.dto.RegisterClientRequest;
import kr.co.leteatgo.auth.domain.client.ClientAccount;

@Component
public class ClientAccountMapper {

	public ClientAccount create(RegisterClientRequest request) {
		return new ClientAccount(request.name());
	}
}
