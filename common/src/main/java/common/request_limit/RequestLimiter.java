package common.request_limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestLimiter {

  private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();
  private final Map<String, Long> requestTimestampPerIpAddress = new ConcurrentHashMap<>();
  private final int limit;
  private final int seconds;

  public RequestLimiter(int limit, int seconds) {
    this.limit = limit;
    this.seconds = seconds;
  }

  public boolean isAllowed(String ipAddress) {
    requestCountsPerIpAddress.putIfAbsent(ipAddress, new AtomicInteger(0));
    requestTimestampPerIpAddress.putIfAbsent(ipAddress, System.currentTimeMillis());

    long secondsSinceLastRequest =
        (System.currentTimeMillis() - requestTimestampPerIpAddress.get(ipAddress)) / 1000;
    if (secondsSinceLastRequest > seconds) {
      requestTimestampPerIpAddress.put(ipAddress, System.currentTimeMillis());
      requestCountsPerIpAddress.get(ipAddress).set(0);
    }

    return requestCountsPerIpAddress.get(ipAddress).incrementAndGet() <= limit;
  }
}
