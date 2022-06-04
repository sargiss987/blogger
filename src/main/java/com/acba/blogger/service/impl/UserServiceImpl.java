package com.acba.blogger.service.impl;

import com.acba.blogger.dto.user.CreateUserDto;
import com.acba.blogger.dto.user.EditUserDto;
import com.acba.blogger.exception.UserEmailAlreadyExistException;
import com.acba.blogger.mapper.UserMapper;
import com.acba.blogger.model.User;
import com.acba.blogger.model.enums.RoleType;
import com.acba.blogger.repository.UserRepository;
import com.acba.blogger.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    return Optional.of(userRepository.findAll());
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.disableUser(id);
  }

  @Override
  public void editUser(Long id, EditUserDto editUserDto) {
    userRepository.editUser(
        id,
        editUserDto.getFirstName(),
        editUserDto.getLastName(),
        editUserDto.getEmail(),
        RoleType.getId(editUserDto.getRoleType()),
        EditUserDto.encodePasswordIfExist(editUserDto.getPassword(), passwordEncoder));
  }

  @Override
  public User createUser(CreateUserDto createUserDto) {
    if (userRepository.findByEmail(createUserDto.getEmail()).isPresent()) {
      throw new UserEmailAlreadyExistException(
          String.format("User with %s already exist", createUserDto.getEmail()));
    }
    return userRepository.save(UserMapper.createUserDtoToUser(createUserDto, passwordEncoder));
  }
}
