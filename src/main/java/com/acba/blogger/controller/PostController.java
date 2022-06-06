package com.acba.blogger.controller;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.dto.comment.AddCommentResponseDto;
import com.acba.blogger.dto.post.PostDto;
import com.acba.blogger.dto.post.PostGradeResponseDto;
import com.acba.blogger.mapper.CommentMapper;
import com.acba.blogger.mapper.PostGradeMapper;
import com.acba.blogger.mapper.PostMapper;
import com.acba.blogger.service.AuthService;
import com.acba.blogger.service.PostService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping
  public ResponseEntity<List<PostDto>> getAllPostsSortedByRating() {
    return postService
        .getAllPostsSortedByRating()
        .map(
            posts ->
                ResponseEntity.ok(
                    posts.stream().map(PostMapper::postToPostDto).collect(Collectors.toList())))
        .orElse(ResponseEntity.ok(new ArrayList<>()));
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

  @PostMapping("{id}/estimates")
  public ResponseEntity<PostGradeResponseDto> estimatePost(
      @PathVariable Long id, @RequestParam Integer grade) {

    return postService
        .estimatePost(id, grade)
        .map(
            postGrade ->
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(PostGradeMapper.postGradeToPostGradeResponseDto(postGrade)))
        .orElse(ResponseEntity.notFound().build());
  }
}
