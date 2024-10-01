package kr.co.leteatgo.notification.application.push.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PushOneToManyRequest(
    @NotEmpty List<String> deviceTokens,
    @NotNull PushInfo pushInfo
) {

}
