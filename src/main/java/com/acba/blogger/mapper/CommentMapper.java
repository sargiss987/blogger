package com.acba.blogger.mapper;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.dto.comment.AddCommentResponseDto;
import com.acba.blogger.model.Comment;
import com.acba.blogger.model.Post;
import com.acba.blogger.model.User;

public class CommentMapper {

  public static Comment addCommentDtoToComment(AddCommentDto addCommentDto, User user, Post post) {
    return new Comment(addCommentDto.getContent(), user, post);
  }

  public static AddCommentResponseDto commentToAddCommentResponseDto(Comment comment) {
    return new AddCommentResponseDto(comment.getId(), comment.getContent());
  }
}
