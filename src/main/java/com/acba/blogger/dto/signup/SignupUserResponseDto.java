package com.acba.blogger.dto.signup;

public class SignupUserResponseDto {

  private final Long id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String roleType;

  public SignupUserResponseDto(
      Long id, String firstName, String lastName, String email, String roleType) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roleType = roleType;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getRoleType() {
    return roleType;
  }
}
