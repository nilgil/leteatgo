package kr.co.leteatgo.etc.infrastructure.common.security;


import static org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils.roleHierarchyFromMap;

import common.auth.LegRoleHierarchy;
import kr.co.leteatgo.etc.infrastructure.common.security.filter.ClientAccountFilter;
import kr.co.leteatgo.etc.infrastructure.common.security.filter.ExceptionHandlerFilter;
import kr.co.leteatgo.etc.infrastructure.common.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.function.client.WebClient;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DefaultMethodSecurityExpressionHandler expressionHandler() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy(roleHierarchyFromMap(LegRoleHierarchy.hierarchyMap()));

    DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
    expressionHandler.setRoleHierarchy(roleHierarchy);
    return expressionHandler;
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring()
        .requestMatchers("/swagger**", "/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**");
  }

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, WebClient authWebClient)
      throws Exception {

    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
        .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new ClientAccountFilter(authWebClient), JwtAuthFilter.class)
        .addFilterBefore(new ExceptionHandlerFilter(), ClientAccountFilter.class)
        .build();
  }
}
