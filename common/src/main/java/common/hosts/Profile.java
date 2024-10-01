package common.hosts;

import java.util.Arrays;

public enum Profile {
  LOCAL, DEV, STG, PROD;

  public static Profile find(String value) {
    return Arrays.stream(Profile.values())
        .filter(profile -> profile.toString().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
