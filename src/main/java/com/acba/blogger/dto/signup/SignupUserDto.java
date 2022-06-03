package com.acba.blogger.dto.signup;

public class SignupUserDto {

  private final String firstName;
  private final String lastName;
  private final String email;
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
