package common.hosts;

public enum LegService {
  AUTH(30000),
  USER(30020),
  STORE(30030),
  BANK(30070),
  NOTIFICATION(30090),
  GEO(30100),
  ETC(30200);

  private final int port;

  LegService(int port) {
    this.port = port;
  }

  public String host(Profile profile) {
    if (profile == Profile.LOCAL || profile == Profile.DEV) {
      return String.format("http://localhost:%d/%s", this.port, this.name().toLowerCase());
    } else {
      return String.format("http://leg-%s:%d/%s", this.name().toLowerCase(), this.port,
          this.name().toLowerCase());
    }
  }

}
