package com.acba.blogger.service;

import com.acba.blogger.dto.user.CreateUserDto;
import com.acba.blogger.dto.user.EditUserDto;
import com.acba.blogger.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<List<User>> getAllUsers();

  void deleteUser(Long id);

  void editUser(Long id, EditUserDto editUserDto);

  User createUser(CreateUserDto createUserDto);
}
