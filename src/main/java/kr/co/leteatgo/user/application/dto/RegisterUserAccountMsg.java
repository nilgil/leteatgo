package kr.co.leteatgo.user.application.dto;

import java.util.UUID;

public record RegisterUserAccountMsg(
	UUID userId,
	String phoneNumber
) {

}
