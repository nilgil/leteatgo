package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import java.util.List;
import kr.co.leteatgo.store.domain.common.BaseUuidEntity;
import kr.co.leteatgo.store.domain.common.ImagePath;
import kr.co.leteatgo.store.domain.common.PhoneNumber;
import kr.co.leteatgo.store.domain.store.enums.StoreStatus;
import kr.co.leteatgo.store.domain.store.value.StoreBusinessHours;
import kr.co.leteatgo.store.domain.store.value.StoreContact;
import kr.co.leteatgo.store.domain.store.value.StoreDescription;
import kr.co.leteatgo.store.domain.store.value.StoreImages;
import kr.co.leteatgo.store.domain.store.value.StoreName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Store extends BaseUuidEntity {

  @Embedded
  private StoreName name;
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "phone_number"))
  private PhoneNumber phoneNumber;
  @Embedded
  private StoreContact contact;
  @Enumerated(EnumType.STRING)
  private StoreStatus status;
  @OneToOne(cascade = CascadeType.ALL)
  private StoreLicense license;
  @OneToOne(cascade = CascadeType.ALL)
  private StoreLocation location;
  @OneToOne(cascade = CascadeType.ALL)
  private StoreStats stats;
  @OneToOne(cascade = CascadeType.ALL)
  private StoreTermAgreements termAgreements;
  @Enumerated(EnumType.STRING)
  private StoreAgent agent;

  private Integer minCookTime;
  @Embedded
  private StoreDescription description;
  @Embedded
  private StoreMaterialInformation materialInformation;
  @Embedded
  private StoreFoodTypes foodTypes;
  @Embedded
  private StoreBusinessHours businessHours;
  @Embedded
  private StoreImages images;
  @Embedded
  private StoreFixSuggests fixSuggests;

  @OneToOne(cascade = CascadeType.ALL)
  private StoreEvent event;
  @OneToOne(cascade = CascadeType.ALL)
  private StoreMenuSet menuSet;

  @Builder
  private Store(StoreName name, PhoneNumber phoneNumber, StoreContact contact, StoreLicense license,
      StoreLocation location, StoreTermAgreements termAgreements, StoreAgent agent) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.contact = contact;
    this.license = license;
    this.location = location;
    this.termAgreements = termAgreements;
    this.agent = agent != null ? agent : StoreAgent.WHYNOTUS;
    this.stats = StoreStats.init();
    this.event = StoreEvent.init();
    this.menuSet = StoreMenuSet.init();
    this.status = StoreStatus.CLOSE;
    this.images = StoreImages.init();
  }

  public void updateStoreStatus(StoreStatus status) {
    this.status = status;
  }

  public void updateMainImage(ImagePath mainImage) {
    this.images.updateMainImage(mainImage);
  }

  public void updateSubImages(List<ImagePath> images) {
    this.images.updateSubImages(images);
  }

  public void addMenuGroup(MenuGroup menuGroup) {
    this.menuSet.addMenuGroup(menuGroup);
  }
}
