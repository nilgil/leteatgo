package kr.co.leteatgo.store.presentation;

import common.aws.s3.PreSignedUrls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import kr.co.leteatgo.store.application.dto.AddMenuGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionItemRequest;
import kr.co.leteatgo.store.application.dto.ChangeStoreStatusRequest;
import kr.co.leteatgo.store.application.dto.CoordinateDto;
import kr.co.leteatgo.store.application.dto.CreateImageSubmissionRequest;
import kr.co.leteatgo.store.application.dto.ImageSubmissionResponse;
import kr.co.leteatgo.store.application.dto.MenuOptionGroupResponse;
import kr.co.leteatgo.store.application.dto.MenuSetResponse;
import kr.co.leteatgo.store.application.dto.RegisterStoreRequest;
import kr.co.leteatgo.store.application.dto.SearchStoresCriteria;
import kr.co.leteatgo.store.application.dto.StoreDetailResponse;
import kr.co.leteatgo.store.application.dto.StoreSimpleResponse;
import kr.co.leteatgo.store.application.service.StoreService;
import kr.co.leteatgo.store.domain.common.Cursor;
import kr.co.leteatgo.store.domain.store.enums.StoreSearchSort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Store", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
@RestController
public class StoreControllerV1 {

  private final StoreService storeService;

  @Operation(summary = "주변 매장 검색")
  @GetMapping
  Cursor<StoreSimpleResponse, UUID> searchNearlyStores(
      @RequestParam(required = false) UUID prevId,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "DISTANCE") StoreSearchSort sort,
      @RequestParam(defaultValue = "전체") String foodType,
      @RequestParam(defaultValue = "") String search,
      @RequestParam(defaultValue = "1000") Integer maxDistanceMeter,
      @RequestParam(required = true) Double lng,
      @RequestParam(required = true) Double lat
  ) {
    SearchStoresCriteria criteria = SearchStoresCriteria.builder()
        .prevId(prevId)
        .size(size)
        .sort(sort)
        .foodType(foodType)
        .search(search)
        .maxDistanceMeter(maxDistanceMeter)
        .coordinate(new CoordinateDto(lng, lat))
        .build();
    return storeService.searchStores(criteria);
  }

  @Operation(summary = "매장 상세 조회")
  @GetMapping("/{storeId}")
  StoreDetailResponse getStoreDetail(@PathVariable UUID storeId) {
    return storeService.getStoreDetail(storeId);
  }

  @Operation(summary = "매장 메뉴 조회")
  @GetMapping("/{storeId}/menu-set")
  MenuSetResponse getStoreMenuSet(@PathVariable UUID storeId) {
    return storeService.getStoreMenuSet(storeId);
  }

  @Operation(summary = "매장 메뉴 그룹 추가")
  @PreAuthorize("hasRole('STORE')")
  @PostMapping("/me/menu-sets/{menuSetId}/menu-groups")
  void addMenuGroup(
      @AuthenticationPrincipal UUID storeId,
      @PathVariable Long menuSetId,
      @RequestBody AddMenuGroupRequest request
  ) {
    storeService.addMenuGroup(storeId, menuSetId, request);
  }

  @Operation(summary = "매장 메뉴 아이템 추가")
  @PreAuthorize("hasRole('STORE')")
  @PostMapping("/me/menu-groups/{menuGroupId}/menu-items")
  PreSignedUrls addMenuItem(
      @AuthenticationPrincipal UUID storeId,
      @PathVariable Long menuGroupId,
      @RequestBody AddMenuItemRequest request
  ) {
    return storeService.addMenuItem(storeId, menuGroupId, request);
  }

  @Operation(summary = "매장 메뉴 옵션 그룹 추가")
  @PreAuthorize("hasRole('STORE')")
  @PostMapping("/me/menu-option-groups")
  void addMenuOptionGroup(
      @AuthenticationPrincipal UUID storeId,
      @RequestBody AddMenuOptionGroupRequest request
  ) {
    storeService.addMenuOptionGroup(storeId, request);
  }

  @Operation(summary = "매장 메뉴 옵션 아이템 추가")
  @PreAuthorize("hasRole('STORE')")
  @PostMapping("/me/menu-option-groups/{menuOptionGroupId}/menu-option-items")
  void addMenuOptionItem(
      @AuthenticationPrincipal UUID storeId,
      @PathVariable Long menuOptionGroupId,
      @RequestBody AddMenuOptionItemRequest request
  ) {
    storeService.addMenuOptionItem(storeId, menuOptionGroupId, request);
  }

  @Operation(summary = "매장 메뉴 옵션 그룹 목록 조회")
  @GetMapping("/{storeId}/menu-option-groups")
  List<MenuOptionGroupResponse> getMenuOptionGroups(@PathVariable UUID storeId) {
    return storeService.getMenuOptionGroups(storeId);
  }

  @Operation(summary = "매장 메뉴 아이템과 옵션 그룹 연결")
  @PreAuthorize("hasRole('STORE')")
  @PatchMapping("/me/menu-items/{menuItemId}")
  void addMenuItemOptionGroup(
      @AuthenticationPrincipal UUID storeId,
      @PathVariable Long menuItemId,
      @RequestBody AddMenuItemOptionGroupRequest request
  ) {
    storeService.addMenuItemOptionGroup(storeId, menuItemId, request);
  }

  @Operation(summary = "매장과의 거리 조회")
  @GetMapping("/{storeId}/distance")
  Integer getDistanceFromStore(
      @PathVariable UUID storeId,
      @RequestParam Double lng,
      @RequestParam Double lat
  ) {
    return storeService.getDistanceFromStore(storeId, new CoordinateDto(lng, lat));
  }

  @Operation(summary = "매장 등록")
  @PostMapping
  PreSignedUrls registerStore(@Valid @RequestBody RegisterStoreRequest request) {
    return storeService.registerStore(request);
  }

  @Operation(summary = "매장 상태 변경")
  @PreAuthorize("hasRole('STORE')")
  @PatchMapping("/me/status")
  void changeStoreStatus(
      @AuthenticationPrincipal UUID storeId,
      @RequestBody ChangeStoreStatusRequest request
  ) {
    storeService.changeStoreStatus(storeId, request);
  }

  @Operation(summary = "매장 이미지 제출 목록 조회")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/image-submissions")
  List<ImageSubmissionResponse> getImageSubmissions() {
    return storeService.getImageSubmissions();
  }

  @Operation(
      summary = "매장 이미지 제출",
      description = "리스트에 null을 담으면 사용자의 이미지를 제출한 것으로 간주합니다. "
          + "Leg의 이미지를 사용할 경우 해당 image url을 담아주세요."
  )
  @PreAuthorize("hasRole('STORE')")
  @PostMapping("/me/image-submissions")
  PreSignedUrls createImageSubmission(
      @AuthenticationPrincipal UUID storeId,
      @Valid @RequestBody CreateImageSubmissionRequest request
  ) {
    return storeService.createImageSubmission(storeId, request);
  }

  @Operation(summary = "매장 이미지 제출 승인")
  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("/image-submissions/{submissionId}/approve")
  void approveImageSubmission(@PathVariable Long submissionId) {
    storeService.approveImageSubmission(submissionId);
  }
}
