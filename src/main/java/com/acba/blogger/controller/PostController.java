package com.acba.blogger.controller;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.dto.comment.AddCommentResponseDto;
import com.acba.blogger.mapper.CommentMapper;
import com.acba.blogger.service.AuthService;
import com.acba.blogger.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;
  private final AuthService authService;

  public PostController(PostService postService, AuthService authService) {
    this.postService = postService;
    this.authService = authService;
  }

  @PostMapping("{id}/comments")
  public ResponseEntity<AddCommentResponseDto> addComment(
      @PathVariable Long id, @RequestBody AddCommentDto addCommentDto) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    return authService
        .findUserByEmail(username)
        .map(
            user ->
                postService
                    .addComment(user, id, addCommentDto)
                    .map(
                        comment ->
                            ResponseEntity.status(HttpStatus.CREATED)
                                .body(CommentMapper.commentToAddCommentResponseDto(comment)))
                    .orElse(ResponseEntity.notFound().build()))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
