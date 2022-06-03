package com.acba.blogger.mapper;

import com.acba.blogger.dto.login.LoginUserResponseDto;
import com.acba.blogger.model.User;

public class LoginUserMapper {

  public static LoginUserResponseDto userToLoginUserResponseDto(User user) {
    return new LoginUserResponseDto(user.getId(), user.getEmail(), user.getRole().getType());
  }
}
