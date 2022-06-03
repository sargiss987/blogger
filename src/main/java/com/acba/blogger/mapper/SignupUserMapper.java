package com.acba.blogger.mapper;

import com.acba.blogger.dto.signup.SignupUserDto;
import com.acba.blogger.dto.signup.SignupUserResponseDto;
import com.acba.blogger.model.Role;
import com.acba.blogger.model.User;
import com.acba.blogger.model.enums.RoleType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignupUserMapper {

  public static User signupUserDtoToUser(
      SignupUserDto signupUserDto, BCryptPasswordEncoder passwordEncode) {
    return new User(
        signupUserDto.getFirstName(),
        signupUserDto.getLastName(),
        signupUserDto.getEmail(),
        passwordEncode.encode(signupUserDto.getPassword()),
        Role.isBlogger());
  }

  public static SignupUserResponseDto userToSignupUserResponseDto(User user) {
    return new SignupUserResponseDto(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        RoleType.BLOGGER.name());
  }
}
