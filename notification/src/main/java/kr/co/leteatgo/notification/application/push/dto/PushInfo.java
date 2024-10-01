package kr.co.leteatgo.notification.application.push.dto;

import java.util.Map;

public record PushInfo(
    String title,
    String body,
    String imageUrl,
    Map<String, String> data
) {

}
