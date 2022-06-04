package com.acba.blogger.dto.user;

public class UserCommentDto {

  private final String content;
  private final Integer rating;

  public UserCommentDto(String content, Integer rating) {
    this.content = content;
    this.rating = rating;
  }

  public String getContent() {
    return content;
  }

  public Integer getRating() {
    return rating;
  }
}
