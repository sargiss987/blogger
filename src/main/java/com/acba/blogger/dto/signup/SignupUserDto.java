package com.acba.blogger.dto.signup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignupUserDto {

  private static final String PASSWORD_PATTERN =
      "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_{}]).{8,20})";

  @NotBlank(message = "{validation.firstName.error.message}")
  private final String firstName;

  @NotBlank(message = "{validation.lastName.error.message}")
  private final String lastName;

  @NotBlank(message = "{validation.email.error.message}")
  private final String email;

  @Pattern(regexp = PASSWORD_PATTERN, message = "{validation.password.error.message}")
  private final String password;

  public SignupUserDto(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
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
}
