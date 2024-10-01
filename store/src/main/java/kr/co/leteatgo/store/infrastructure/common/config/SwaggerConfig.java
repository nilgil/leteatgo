package kr.co.leteatgo.store.infrastructure.common.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;

@Profile({"local", "dev"})
@Configuration
public class SwaggerConfig {

  private static final String CONTACT_TO = "0giri";
  private static final String CONTACT_EMAIL = "dev.leteatgo@gmail.com";
  private static final String SECURITY_NAME = "Leg Authentication";

  @Value("${app.name}")
  private String appName;
  @Value("${app.version}")
  private String version;

  @Primary
  @Bean
  public GroupedOpenApi defaultGroupedOpenApi() {
    return createVersionedOpenApi(appName, version);
  }

  @Bean
  public GroupedOpenApi beforeVersionOpenApi() {
    OpenApiCustomizer openApiCustomizer = createOpenApiCustomizer(appName, "before");
    return GroupedOpenApi.builder()
        .group("before")
        .pathsToExclude(String.format("/v%s/**", version.split("\\.")[0]))
        .build()
        .addAllOpenApiCustomizer(List.of(openApiCustomizer));
  }

  private static GroupedOpenApi createVersionedOpenApi(String appName, String version) {
    OpenApiCustomizer openApiCustomizer = createOpenApiCustomizer(appName, version);
    return GroupedOpenApi.builder()
        .group(version)
        .pathsToMatch(String.format("/v%s/**", version.split("\\.")[0]))
        .build()
        .addAllOpenApiCustomizer(List.of(openApiCustomizer));
  }

  private static OpenApiCustomizer createOpenApiCustomizer(String appName, String version) {
    return openApi -> openApi
        .info(createInfo(appName, version))
        .servers(List.of(createServer(appName)))
        .security(List.of(createSchemaRequirement()))
        .getComponents()
        .addSecuritySchemes(SECURITY_NAME, createSecurityScheme());
  }

  private static Info createInfo(String appName, String version) {
    return new Info()
        .title(String.format("%s API Documentation", appName.toUpperCase()))
        .version(version)
        .contact(createContact());
  }

  private static Contact createContact() {
    return new Contact()
        .name(CONTACT_TO)
        .email(CONTACT_EMAIL);
  }

  private static Server createServer(String appName) {
    return new Server().url("/" + appName.substring(appName.indexOf("-") + 1));
  }

  private static SecurityRequirement createSchemaRequirement() {
    return new SecurityRequirement()
        .addList(SECURITY_NAME);
  }

  private static SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
        .type(Type.APIKEY).in(In.HEADER).name(HttpHeaders.AUTHORIZATION)
        .description("please include prefix ('Bearer ' or 'LegAK ')");
  }
}
