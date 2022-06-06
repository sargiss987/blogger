package com.acba.blogger.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  @PostMapping("{id}")
  public ResponseEntity<Void> estimateComment(@PathVariable Long id) {
    // TODO
    return ResponseEntity.ok().build();
  }
}
