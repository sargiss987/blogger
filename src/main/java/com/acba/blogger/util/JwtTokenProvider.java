package com.acba.blogger.util;

import com.acba.blogger.model.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
  private static final String AUTHORITIES = "authorities";

  private final long expirationTime;
  private final String secret;

  public JwtTokenProvider(
      @Value("${jwt.token-exp-time}") long expirationTime, @Value("${jwt.secret}") String secret) {
    this.expirationTime = expirationTime;
    this.secret = secret;
  }

  public String generateJwtToken(UserPrincipal userPrincipal) {
    return JWT.create()
        .withIssuer("auth0")
        .withIssuedAt(new Date())
        .withSubject(userPrincipal.getUsername())
        .withArrayClaim(AUTHORITIES, getClaimsFromUser(userPrincipal))
        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
        .sign(Algorithm.HMAC512(secret.getBytes(StandardCharsets.UTF_8)));
  }

  public boolean isTokenValid(String token) {
    JWTVerifier verifier = getJwtVerifier();
    Date expiration = verifier.verify(token).getExpiresAt();
    return !expiration.before(new Date());
  }

  public String getSubject(String token) {
    JWTVerifier verifier = getJwtVerifier();
    return verifier.verify(token).getSubject();
  }

  public List<GrantedAuthority> getAuthorities(String token) {
    String[] claims = getClaimsFromToken(token);
    return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
    return userPrincipal.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toArray(String[]::new);
  }

  private JWTVerifier getJwtVerifier() {
    return JWT.require(Algorithm.HMAC512(secret)).build();
  }

  private String[] getClaimsFromToken(String token) {
    JWTVerifier verifier = getJwtVerifier();
    return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
  }
}
