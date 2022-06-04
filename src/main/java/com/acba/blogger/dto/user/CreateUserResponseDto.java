package com.acba.blogger.dto.user;

public class CreateUserResponseDto {
  private final Long id;
  private final Boolean isEnabled;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String roleType;

  public CreateUserResponseDto(
      Long id,
      Boolean isEnabled,
      String firstName,
      String lastName,
      String email,
      String roleType) {
    this.id = id;
    this.isEnabled = isEnabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roleType = roleType;
  }

  public Long getId() {
    return id;
  }

  public Boolean getEnabled() {
    return isEnabled;
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
