package com.acba.blogger.dto.post;

public class PostCommentDto {

  private final Long id;
  private final String content;
  private final Integer rating;
  private final String owner;

  public PostCommentDto(Long id, String content, Integer rating, String owner) {
    this.id = id;
    this.content = content;
    this.rating = rating;
    this.owner = owner;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public Integer getRating() {
    return rating;
  }

  public String getOwner() {
    return owner;
  }
}
