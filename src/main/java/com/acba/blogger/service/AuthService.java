package com.acba.blogger.service;

import com.acba.blogger.dto.signup.SignupUserDto;
import com.acba.blogger.model.User;
import java.util.Optional;

public interface AuthService {

  User signup(SignupUserDto signupUserDto);

  Optional<User> findUserByEmail(String email);

  void addHeadAdmin();
}
