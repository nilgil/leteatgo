package kr.co.leteatgo.store.infrastructure.common.config;

import static common.hosts.LegService.AUTH;
import static common.hosts.LegService.BANK;
import static common.hosts.LegService.ETC;
import static common.hosts.LegService.GEO;
import static common.hosts.LegService.NOTIFICATION;
import static common.hosts.LegService.STORE;
import static common.hosts.LegService.USER;

import common.hosts.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  private final Profile profile;

  public WebClientConfig(@Value("${spring.profiles.active}") String profile) {
    this.profile = Profile.find(profile);
  }

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }

  @Bean
  public WebClient authWebClient() {
    return WebClient.builder()
        .baseUrl(AUTH.host(profile))
        .build();
  }

  @Bean
  public WebClient userWebClient() {
    return WebClient.builder()
        .baseUrl(USER.host(profile))
        .build();
  }

  @Bean
  public WebClient storeWebClient() {
    return WebClient.builder()
        .baseUrl(STORE.host(profile))
        .build();
  }

  @Bean
  public WebClient bankWebClient() {
    return WebClient.builder()
        .baseUrl(BANK.host(profile))
        .build();
  }

  @Bean
  public WebClient notiWebClient() {
    return WebClient.builder()
        .baseUrl(NOTIFICATION.host(profile))
        .build();
  }

  @Bean
  public WebClient geoWebClient() {
    return WebClient.builder()
        .baseUrl(GEO.host(profile))
        .build();
  }

  @Bean
  public WebClient etcWebClient() {
    return WebClient.builder()
        .baseUrl(ETC.host(profile))
        .build();
  }
}
