package com.acba.blogger.service;

import com.acba.blogger.dto.post.AddPostDto;
import com.acba.blogger.model.Post;

public interface UserService {

  Post addPost(Long userId, AddPostDto addPostDto);

  void deletePost(Long postId, Long userId);
}
