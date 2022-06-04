package com.acba.blogger.mapper;

import com.acba.blogger.dto.user.UserPostDto;
import com.acba.blogger.model.Post;

public class UserPostMapper {

  public static UserPostDto postToUserPostDto(Post post) {
    return new UserPostDto(post.getTitle(), post.getContent(), post.isActive(), post.getRating());
  }
}
