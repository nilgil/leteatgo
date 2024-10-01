package common.aws.s3;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record PreSignedUrls(List<PreSignedUrl> preSignedUrls) {

  public PreSignedUrls {
    if (preSignedUrls == null) {
      throw new IllegalArgumentException("preSignedUrls must not be null");
    }
  }

  public static PreSignedUrls of(PreSignedUrl... preSignedUrls) {
    return new PreSignedUrls(List.of(preSignedUrls));
  }

  public Stream<PreSignedUrl> stream() {
    return preSignedUrls.stream();
  }

  public PreSignedUrl get() {
    return preSignedUrls.get(0);
  }

  public PreSignedUrl get(int index) {
    return preSignedUrls.get(index);
  }

  public List<String> imageUrls() {
    return preSignedUrls.stream()
        .map(PreSignedUrl::imageUrl)
        .toList();
  }

  public int size() {
    return preSignedUrls.size();
  }

  public boolean isEmpty() {
    return preSignedUrls.isEmpty();
  }

  public PreSignedUrls boundCopy(int fromIndex, int toIndex) {
    return new PreSignedUrls(preSignedUrls.subList(fromIndex, toIndex));
  }

  public PreSignedUrls merge(PreSignedUrls source) {
    List<PreSignedUrl> mergedStream = Stream
        .concat(this.preSignedUrls.stream(), source.stream())
        .toList();
    return new PreSignedUrls(mergedStream);
  }

  public PreSignedUrls numbering(String prefix) {
    if (prefix.contains(".")) {
      throw new IllegalArgumentException("prefix must not contain '-'");
    }
    List<PreSignedUrl> preSignedUrls1 = IntStream.range(0, this.preSignedUrls.size())
        .mapToObj(i -> this.preSignedUrls.get(i).labeling(String.format("%s.%d", prefix, i + 1)))
        .toList();
    return new PreSignedUrls(preSignedUrls1);
  }
}
