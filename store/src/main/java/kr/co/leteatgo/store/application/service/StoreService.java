package kr.co.leteatgo.store.application.service;

import common.aws.s3.AwsS3Folder;
import common.aws.s3.PreSignedUrl;
import common.aws.s3.PreSignedUrls;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kr.co.leteatgo.store.application.GeoUtils;
import kr.co.leteatgo.store.application.dto.AddMenuGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuItemRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionGroupRequest;
import kr.co.leteatgo.store.application.dto.AddMenuOptionItemRequest;
import kr.co.leteatgo.store.application.dto.ChangeStoreStatusRequest;
import kr.co.leteatgo.store.application.dto.CoordinateDto;
import kr.co.leteatgo.store.application.dto.CreateImageSubmissionRequest;
import kr.co.leteatgo.store.application.dto.ImageSubmissionResponse;
import kr.co.leteatgo.store.application.dto.LicenseDto;
import kr.co.leteatgo.store.application.dto.LocationDto;
import kr.co.leteatgo.store.application.dto.MenuOptionGroupResponse;
import kr.co.leteatgo.store.application.dto.MenuSetResponse;
import kr.co.leteatgo.store.application.dto.RegisterStoreRequest;
import kr.co.leteatgo.store.application.dto.SearchStoresCriteria;
import kr.co.leteatgo.store.application.dto.StoreDetailResponse;
import kr.co.leteatgo.store.application.dto.StoreSimpleResponse;
import kr.co.leteatgo.store.application.dto.TownDto;
import kr.co.leteatgo.store.application.mapper.StoreMapper;
import kr.co.leteatgo.store.domain.common.Cursor;
import kr.co.leteatgo.store.domain.common.ImagePath;
import kr.co.leteatgo.store.domain.store.StoreImageSubmission;
import kr.co.leteatgo.store.domain.store.entity.MenuGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuItem;
import kr.co.leteatgo.store.domain.store.entity.MenuItemOptionGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuOptionGroup;
import kr.co.leteatgo.store.domain.store.entity.MenuOptionItem;
import kr.co.leteatgo.store.domain.store.entity.Store;
import kr.co.leteatgo.store.domain.store.entity.StoreLicense;
import kr.co.leteatgo.store.domain.store.entity.StoreLocation;
import kr.co.leteatgo.store.domain.store.entity.StoreMenuSet;
import kr.co.leteatgo.store.infrastructure.repository.StoreImageSubmissionRepository;
import kr.co.leteatgo.store.infrastructure.repository.StoreMenuGroupRepository;
import kr.co.leteatgo.store.infrastructure.repository.StoreMenuItemRepository;
import kr.co.leteatgo.store.infrastructure.repository.StoreMenuOptionGroupRepository;
import kr.co.leteatgo.store.infrastructure.repository.StoreMenuSetRepository;
import kr.co.leteatgo.store.infrastructure.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreRepository storeRepository;
  private final StoreMenuSetRepository menuSetRepository;
  private final StoreMenuGroupRepository menuGroupRepository;
  private final StoreMenuOptionGroupRepository menuOptionGroupRepository;
  private final StoreMenuItemRepository menuItemRepository;
  private final StoreImageSubmissionRepository storeImageSubmissionRepository;

  private final StoreMapper storeMapper;
  private final LegApiClient legApiClient;

  @Transactional(readOnly = true)
  public Cursor<StoreSimpleResponse, UUID> searchStores(SearchStoresCriteria criteria) {
    return storeRepository.searchStores(criteria);
  }

  @Transactional(readOnly = true)
  public StoreDetailResponse getStoreDetail(UUID storeId) {
    Store store = getStoreById(storeId);
    return storeMapper.entityToDetail(store);
  }

  @Transactional(readOnly = true)
  public MenuSetResponse getStoreMenuSet(UUID storeId) {
    Store store = getStoreById(storeId);
    StoreMenuSet menuSet = store.getMenuSet();
    return storeMapper.entityToMenuSetResponse(menuSet);
  }

  @Transactional(readOnly = true)
  public Integer getDistanceFromStore(UUID storeId, CoordinateDto coordinate) {
    Store store = getStoreById(storeId);
    Point point = store.getLocation().getPoint();
    return GeoUtils.betweenDistance(point.getX(), point.getY(), coordinate.lng(), coordinate.lat());
  }

  @Transactional
  public PreSignedUrls registerStore(RegisterStoreRequest request) {
    PreSignedUrls preSignedUrls = legApiClient.createPreSignedUrls(
        AwsS3Folder.STORE_LICENCE_IMAGES, 2);
    PreSignedUrl licenseImagePsu = preSignedUrls.get(0);
    PreSignedUrl businessPermitImagePsu = preSignedUrls.get(1);

    StoreLicense license = createStoreLicense(request.license(),
        licenseImagePsu, businessPermitImagePsu);
    StoreLocation location = createStoreLocation(request.location());
    Store store = storeMapper.create(request, license, location);
    storeRepository.save(store);

    PreSignedUrl bankBookImagePsu = legApiClient.registerBankAccount(store.getId(),
        request.bankAccount()).get();

    legApiClient.registerStoreAccount(store.getId(), request.credential().loginId(),
        request.credential().loginPwd());

    return PreSignedUrls.of(
        licenseImagePsu.labeling("licenseImage"),
        businessPermitImagePsu.labeling("businessPermitImage"),
        bankBookImagePsu.labeling("bankBookImage")
    );
  }

  @Transactional
  public void changeStoreStatus(UUID storeId, ChangeStoreStatusRequest request) {
    Store store = getStoreById(storeId);
    store.updateStoreStatus(request.status());
  }

  @Transactional(readOnly = true)
  public List<ImageSubmissionResponse> getImageSubmissions() {
    List<StoreImageSubmission> submissions = storeImageSubmissionRepository.findAll();
    return submissions.stream().map(storeMapper::entityToImageSubmissionResponse).toList();
  }

  @Transactional
  public PreSignedUrls createImageSubmission(UUID storeId, CreateImageSubmissionRequest request) {
    Store store = getStoreById(storeId);

    deleteExistSubmission(store);

    List<String> images = request.images();
    int customImageCount = getCustomImageCount(images); // TODO

    PreSignedUrls preSignedUrls = legApiClient.createPreSignedUrls(
        AwsS3Folder.STORE_IMAGES, customImageCount);

    List<ImagePath> storeImages = createStoreImages(images, preSignedUrls);

    StoreImageSubmission submission = new StoreImageSubmission(store, storeImages);
    storeImageSubmissionRepository.save(submission);

    return preSignedUrls.numbering("customImage");
  }

  @Transactional
  public void approveImageSubmission(Long submissionId) {
    StoreImageSubmission submission = storeImageSubmissionRepository.findById(submissionId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    List<ImagePath> images = submission.getImages();
    if (images.isEmpty()) {
      throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, "이미지가 없습니다.");
    }

    Store store = submission.getStore();
    store.updateMainImage(images.get(0));
    store.updateSubImages(images.subList(1, images.size()));

    storeImageSubmissionRepository.delete(submission);
  }

  @Transactional
  public void addMenuGroup(UUID storeId, Long menuSetId, AddMenuGroupRequest request) {
    Store store = getStoreById(storeId);

    StoreMenuSet menuSet = menuSetRepository.findById(menuSetId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    if (!menuSet.equals(store.getMenuSet())) {
      throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE);
    }

    MenuGroup menuGroup = storeMapper.createMenuGroup(request);
    menuSet.addMenuGroup(menuGroup);
  }

  @Transactional
  public PreSignedUrls addMenuItem(UUID storeId, Long menuGroupId, AddMenuItemRequest request) {
    Store store = getStoreById(storeId);

    MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    StoreMenuSet menuSet = menuGroup.getMenuSet();
    if (!menuSet.equals(store.getMenuSet())) {
      throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE);
    }

    PreSignedUrl preSignedUrl = null;
    if (request.hasImage()) {
      preSignedUrl = legApiClient.createPreSignedUrls(AwsS3Folder.STORE_MENU_IMAGES, 1).get();
    }

    MenuItem menuItem = storeMapper.createMenuItem(request, preSignedUrl);
    menuGroup.addMenuItem(menuItem);

    return request.hasImage() ? PreSignedUrls.of(preSignedUrl) : PreSignedUrls.of();
  }

  @Transactional
  public void addMenuOptionGroup(UUID storeId, AddMenuOptionGroupRequest request) {
    Store store = getStoreById(storeId);
    MenuOptionGroup optionGroup = storeMapper.createMenuOptionGroup(request, store);
    menuOptionGroupRepository.save(optionGroup);
  }

  @Transactional
  public void addMenuOptionItem(UUID storeId, Long optionGroupId,
      AddMenuOptionItemRequest request) {
    Store store = getStoreById(storeId);

    MenuOptionGroup optionGroup = menuOptionGroupRepository.findById(optionGroupId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    if (!optionGroup.getStore().equals(store)) {
      throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE);
    }

    MenuOptionItem optionItem = storeMapper.createMenuOptionItem(request, optionGroup);
    optionGroup.addMenuOptionItem(optionItem);
  }

  @Transactional
  public void addMenuItemOptionGroup(UUID storeId, Long menuItemId,
      AddMenuItemOptionGroupRequest request) {
    Store store = getStoreById(storeId);

    MenuItem menuItem = menuItemRepository.findById(menuItemId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    if (!menuItem.getMenuGroup().getMenuSet().equals(store.getMenuSet())) {
      throw new LegException(ErrorCode.NO_AUTHORITY_RESOURCE);
    }

    MenuOptionGroup optionGroup = menuOptionGroupRepository.findById(request.optionGroupId())
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));

    MenuItemOptionGroup menuItemOptionGroup = storeMapper.createMenuItemOptionGroup(request,
        menuItem, optionGroup);
    menuItem.addMenuItemOptionGroup(menuItemOptionGroup);
  }

  @Transactional(readOnly = true)
  public List<MenuOptionGroupResponse> getMenuOptionGroups(UUID storeId) {
    Store store = getStoreById(storeId);
    return menuOptionGroupRepository.findByStore(store).stream()
        .map(storeMapper::entityToMenuOptionGroupResponse)
        .toList();
  }

  private static List<ImagePath> createStoreImages(List<String> images,
      PreSignedUrls preSignedUrls) {
    List<ImagePath> storeImages = new ArrayList<>();
    int customImageIndex = 0;
    for (String image : images) {
      if (Objects.isNull(image)) {
        String customImage = preSignedUrls.get(customImageIndex++).imageUrl();
        storeImages.add(ImagePath.valueOf(customImage));
      } else {
        storeImages.add(ImagePath.valueOf(image));
      }
    }
    return storeImages;
  }

  private static int getCustomImageCount(List<String> images) {
    return images.stream()
        .filter(Objects::isNull)
        .mapToInt(__ -> 1).sum();
  }

  private void deleteExistSubmission(Store store) {
    storeImageSubmissionRepository.findByStore(store)
        .ifPresent(storeImageSubmissionRepository::delete);
  }

  private StoreLicense createStoreLicense(LicenseDto license, PreSignedUrl licenseImagePsu,
      PreSignedUrl businessPermitImagePsu) {
    ImagePath licenseImage = ImagePath.valueOf(licenseImagePsu.imageUrl());
    ImagePath businessPermitImage = ImagePath.valueOf(businessPermitImagePsu.imageUrl());
    return storeMapper.createStoreLicense(license, licenseImage, businessPermitImage);
  }

  private StoreLocation createStoreLocation(LocationDto location) {
    TownDto town = legApiClient.getContainTown(
        location.coordinate().lng(),
        location.coordinate().lat());
    return storeMapper.createStoreLocation(location, town);
  }

  private Store getStoreById(UUID storeId) {
    return storeRepository.findById(storeId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
  }
}
