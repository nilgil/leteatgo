package kr.co.leteatgo.store.domain.common;

import common.exception.ErrorCode;
import common.exception.LegException;
import jakarta.persistence.Embeddable;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ImagePath {

  private String value;

  private ImagePath(String value) {
    try {
      new URL(value);
    } catch (MalformedURLException e) {
      throw new LegException(ErrorCode.BAD_REQUEST);
    }
    this.value = value;
  }

  public static ImagePath valueOf(String value) {
    return new ImagePath(value);
  }
}
