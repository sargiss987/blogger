package com.acba.blogger.controller;

import com.acba.blogger.config.security.CustomAuthenticationManager;
import com.acba.blogger.dto.login.LoginUserDto;
import com.acba.blogger.dto.login.LoginUserResponseDto;
import com.acba.blogger.dto.signup.SignupUserDto;
import com.acba.blogger.dto.signup.SignupUserResponseDto;
import com.acba.blogger.mapper.LoginUserMapper;
import com.acba.blogger.mapper.SignupUserMapper;
import com.acba.blogger.model.User;
import com.acba.blogger.model.UserPrincipal;
import com.acba.blogger.service.AuthService;
import com.acba.blogger.util.JwtTokenProvider;
import javax.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private static final String TOKEN_PREFIX = "Bearer ";
  private static final String JWT_TOKEN_HEADER = "Authorization";

  private final AuthService authService;
  private final CustomAuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthController(
      AuthService authService,
      CustomAuthenticationManager authenticationManager,
      JwtTokenProvider jwtTokenProvider) {
    this.authService = authService;
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @PostMapping("signup")
  public ResponseEntity<SignupUserResponseDto> signup(@RequestBody SignupUserDto signupUserDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(SignupUserMapper.userToSignupUserResponseDto(authService.signup(signupUserDto)));
  }

  @PostMapping("login")
  public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
    authenticate(loginUserDto.getEmail(), loginUserDto.getPassword());
    User user = authService.findUserByEmail(loginUserDto.getEmail());
    HttpHeaders headers = getJwtHeader(new UserPrincipal(user));
    return ResponseEntity.ok()
        .headers(headers)
        .body(LoginUserMapper.userToLoginUserResponseDto(user));
  }

  @PostConstruct
  public void addHeadAdmin(){
    authService.addHeadAdmin();
  }

  private void authenticate(String username, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }

  private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(JWT_TOKEN_HEADER, TOKEN_PREFIX + jwtTokenProvider.generateJwtToken(userPrincipal));
    return headers;
  }
}
