package com.acba.blogger.dto.login;

public class LoginUserResponseDto {

  private final Long id;
  private final String email;
  private final String roleType;

  public LoginUserResponseDto(Long id, String email, String roleType) {
    this.id = id;
    this.email = email;
    this.roleType = roleType;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getRoleType() {
    return roleType;
  }
}
