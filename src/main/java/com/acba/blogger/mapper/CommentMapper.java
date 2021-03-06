package com.acba.blogger.mapper;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.dto.comment.AddCommentResponseDto;
import com.acba.blogger.dto.post.PostCommentDto;
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

  public static PostCommentDto commentToPostCommentDto(Comment comment) {
    return new PostCommentDto(
        comment.getId(),
        comment.getContent(),
        comment.getRating(),
        createFullName(comment.getOwner().getFirstName(), comment.getOwner().getLastName()));
  }

  private static String createFullName(String firstName, String lastName) {
    return firstName + " " + lastName;
  }
}
