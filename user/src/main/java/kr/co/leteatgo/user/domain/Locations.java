package kr.co.leteatgo.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import kr.co.leteatgo.user.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Locations {

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Location> locations = new ArrayList<>();

  public Locations(List<Location> locations) {
    if (locations == null) {
      throw new IllegalArgumentException("locations must not be null");
    }
    if (locations.size() > 3) {
      throw new IllegalArgumentException("max size of locations is 3");
    }
    this.locations = locations;
  }

  public Stream<Location> stream() {
    return this.locations.stream();
  }

  public void addLocation(Location location) {
    List<Location> duplicatedMarks = locations.stream()
        .filter(Location::getMarked)
        .filter(ul -> ul.isMarkedBy(location.getAlias()))
        .toList();
    locations.removeAll(duplicatedMarks);

    locations.stream()
        .filter(Location::getActive)
        .forEach(userLocation1 -> userLocation1.activeLocation(false));

    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    locations.remove(location);

    if (locations.stream().anyMatch(Location::getActive)) {
      return;
    }

    locations.stream()
        .max(Comparator.comparing(BaseEntity::getModifiedAt))
        .ifPresent(userLocation1 -> userLocation1.activeLocation(true));
  }
}
