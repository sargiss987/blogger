package com.acba.blogger.mapper;

import com.acba.blogger.dto.user.CreateUserDto;
import com.acba.blogger.dto.user.CreateUserResponseDto;
import com.acba.blogger.dto.user.UserDto;
import com.acba.blogger.model.Role;
import com.acba.blogger.model.User;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

  public static UserDto userToUserDto(User user) {
    return new UserDto(
        user.getId(),
        user.isEnabled(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getRole().getType(),
        user.getComments().stream()
            .map(UserCommentMapper::commentToUserCommentDto)
            .collect(Collectors.toSet()),
        user.getPosts().stream()
            .map(UserPostMapper::postToUserPostDto)
            .collect(Collectors.toSet()));
  }

  public static User createUserDtoToUser(
      CreateUserDto createUserDto, BCryptPasswordEncoder passwordEncoder) {
    return new User(
        createUserDto.getFirstName(),
        createUserDto.getLastName(),
        createUserDto.getEmail(),
        passwordEncoder.encode(createUserDto.getPassword()),
        Role.getInstance(createUserDto.getRoleType()));
  }

  public static CreateUserResponseDto userToCreateUserResponseDto(User user) {
    return new CreateUserResponseDto(
        user.getId(),
        user.isEnabled(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getRole().getType());
  }
}
