package kr.co.leteatgo.user.application.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ResetUserLocationsMessage(
    UUID userId,
    List<AddUserLocationMessage> locations
) {

}
