package kr.co.leteatgo.notification.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import kr.co.leteatgo.notification.application.push.PushService;
import kr.co.leteatgo.notification.application.push.dto.PushOneToManyRequest;
import kr.co.leteatgo.notification.application.push.dto.PushOneToOneRequest;
import kr.co.leteatgo.notification.domain.DeviceToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Push", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/push")
@RestController
public class PushControllerV1 {

  private final PushService pushService;

  @Operation(summary = "특정 기기에 푸시 (1 -> 1)")
  @PostMapping("/one-to-one")
  public String pushToOne(@Valid @RequestBody PushOneToOneRequest request) {
    return pushService.pushToOne(request);
  }

  @Operation(summary = "여러 기기에 같은 내용 푸시 (1 -> N)")
  @PostMapping("/one-to-many")
  public List<DeviceToken> pushToMany(@Valid @RequestBody PushOneToManyRequest request) {
    return pushService.pushToMany(request);
  }

  @Operation(summary = "여러개의 푸시 (1 -> 1) * N")
  @PostMapping("/many-to-many")
  public List<DeviceToken> manyPushes(@RequestBody List<PushOneToOneRequest> request) {
    return pushService.manyPushes(request);
  }
}
