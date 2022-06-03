package com.acba.blogger.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;

  public CustomAuthenticationManager(
      UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserDetails userPrincipal = userDetailsService.loadUserByUsername(authentication.getName());
    if (passwordEncoder.matches(
        authentication.getCredentials().toString(), userPrincipal.getPassword())) {
      return new UsernamePasswordAuthenticationToken(
          userPrincipal.getUsername(), userPrincipal.getPassword(), userPrincipal.getAuthorities());
    } else {
      throw new BadCredentialsException("Wrong password");
    }
  }
}
