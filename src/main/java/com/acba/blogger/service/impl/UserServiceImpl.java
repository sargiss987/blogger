package com.acba.blogger.service.impl;

import com.acba.blogger.dto.post.AddPostDto;
import com.acba.blogger.mapper.PostMapper;
import com.acba.blogger.model.Post;
import com.acba.blogger.repository.PostRepository;
import com.acba.blogger.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final PostRepository postRepository;

  public UserServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post addPost(Long userId, AddPostDto addPostDto) {
    return postRepository.save(PostMapper.addPostDtoToPost(addPostDto, userId));
  }

  @Override
  public void deletePost(Long postId, Long userId) {
    postRepository
        .findById(postId)
        .filter(post -> post.getBlogger().getId().equals(userId))
        .ifPresent(postRepository::delete);
  }
}
