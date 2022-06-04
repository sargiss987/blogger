package com.acba.blogger.service;

import com.acba.blogger.dto.signup.SignupUserDto;
import com.acba.blogger.model.User;

public interface AuthService {

  User signup(SignupUserDto signupUserDto);

  User findUserByEmail(String email);

  void addHeadAdmin();
}
