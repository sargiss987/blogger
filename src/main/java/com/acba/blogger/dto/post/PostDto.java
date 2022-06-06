package com.acba.blogger.dto.post;

import java.util.List;

public class PostDto {

  private final Long id;
  private final String title;
  private final String content;
  private final Boolean isActive;
  private final Integer rating;
  private final String blogger;
  private final List<PostCommentDto> comments;

  public PostDto(
      Long id,
      String title,
      String content,
      Boolean isActive,
      Integer rating,
      String blogger,
      List<PostCommentDto> comments) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.isActive = isActive;
    this.rating = rating;
    this.blogger = blogger;
    this.comments = comments;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Boolean getActive() {
    return isActive;
  }

  public Integer getRating() {
    return rating;
  }

  public String getBlogger() {
    return blogger;
  }

  public List<PostCommentDto> getComments() {
    return comments;
  }
}
