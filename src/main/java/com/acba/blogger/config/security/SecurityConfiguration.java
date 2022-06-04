package com.acba.blogger.config.security;

import com.acba.blogger.config.filter.CustomHttpForbiddenEntryPoint;
import com.acba.blogger.config.filter.JwtAuthenticationFilter;
import com.acba.blogger.model.enums.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

  private static final String[] PUBLIC_URLS = {
    "/h2/**", "/api/v1/auth/signup", "/api/v1/auth/login"
  };

  private final CustomHttpForbiddenEntryPoint forbiddenEntryPoint;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfiguration(
      CustomHttpForbiddenEntryPoint forbiddenEntryPoint,
      JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.forbiddenEntryPoint = forbiddenEntryPoint;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.headers().frameOptions().sameOrigin();
    http.csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/users/**")
        .hasAuthority(RoleType.ADMIN.name())
        .antMatchers(PUBLIC_URLS)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(forbiddenEntryPoint)
        .and()
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
