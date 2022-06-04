package com.acba.blogger.mapper;

import com.acba.blogger.dto.user.UserCommentDto;
import com.acba.blogger.model.Comment;

public class UserCommentMapper {

  public static UserCommentDto commentToUserCommentDto(Comment comment) {
    return new UserCommentDto(comment.getContent(), comment.getRating());
  }
}
