package com.acba.blogger.dto.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EditUserDto {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;
  private final String roleType;

  public EditUserDto(
      String firstName, String lastName, String email, String password, String roleType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.roleType = roleType;
  }

  public static String encodePasswordIfExist(
      String password, BCryptPasswordEncoder passwordEncoder) {
    if (password != null) {
      return passwordEncoder.encode(password);
    }
    return null;
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

  public String getPassword() {
    return password;
  }

  public String getRoleType() {
    return roleType;
  }
}
