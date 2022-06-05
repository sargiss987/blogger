package com.acba.blogger.controller;

import com.acba.blogger.dto.user.CreateUserDto;
import com.acba.blogger.dto.user.CreateUserResponseDto;
import com.acba.blogger.dto.user.EditUserDto;
import com.acba.blogger.dto.user.UserDto;
import com.acba.blogger.mapper.UserMapper;
import com.acba.blogger.service.AdminService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminController {

  private final AdminService userService;

  public AdminController(AdminService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getUsers() {

    return userService
        .getAllUsers()
        .map(
            users ->
                ResponseEntity.ok()
                    .body(
                        users.stream().map(UserMapper::userToUserDto).collect(Collectors.toList())))
        .orElse(ResponseEntity.ok().body(new ArrayList<>()));
  }

  @PostMapping
  public ResponseEntity<CreateUserResponseDto> createUser(
      @RequestBody CreateUserDto createUserDto) {
    return ResponseEntity.ok(
        UserMapper.userToCreateUserResponseDto(userService.createUser(createUserDto)));
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> editUser(
      @PathVariable Long id, @RequestBody EditUserDto editUserDto) {
    userService.editUser(id, editUserDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}
