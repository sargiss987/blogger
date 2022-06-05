package com.acba.blogger.service.impl;

import com.acba.blogger.dto.signup.SignupUserDto;
import com.acba.blogger.exception.UserEmailAlreadyExistException;
import com.acba.blogger.mapper.SignupUserMapper;
import com.acba.blogger.model.Role;
import com.acba.blogger.model.User;
import com.acba.blogger.model.UserPrincipal;
import com.acba.blogger.repository.UserRepository;
import com.acba.blogger.service.AuthService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new UserPrincipal(
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(String.format("User with %s not found", email))));
  }

  @Override
  @Transactional
  public User signup(SignupUserDto signupUserDto) {
    if (userRepository.findByEmail(signupUserDto.getEmail()).isPresent()) {
      throw new UserEmailAlreadyExistException(
          String.format("User with %s already exist", signupUserDto.getEmail()));
    }
    return userRepository.save(
        SignupUserMapper.signupUserDtoToUser(signupUserDto, passwordEncoder));
  }

  @Override
  @Transactional
  public void addHeadAdmin() {
    String email = "sargiss987@gmail.com";
    if (userRepository.findByEmail(email).isPresent()) return;

    User headAdmin =
        new User("Admin", "Admin", email, passwordEncoder.encode("admin"), Role.isAdmin());
    userRepository.save(headAdmin);
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
