package com.acba.blogger.controller;

import com.acba.blogger.dto.post.AddPostDto;
import com.acba.blogger.dto.post.AddPostResponseDto;
import com.acba.blogger.mapper.PostMapper;
import com.acba.blogger.service.AuthService;
import com.acba.blogger.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final AuthService authService;

  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  @PostMapping("/posts")
  public ResponseEntity<AddPostResponseDto> addPost(@RequestBody AddPostDto addPostDto) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return authService
        .findUserByEmail(username)
        .map(
            user ->
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                        PostMapper.postToAddPostResponseDto(
                            userService.addPost(user.getId(), addPostDto))))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    authService
        .findUserByEmail(username)
        .ifPresent(user -> userService.deletePost(id, user.getId()));
    return ResponseEntity.ok().build();
  }
}
