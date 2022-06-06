package com.acba.blogger.dto.comment;

public class AddCommentResponseDto {

  private final Long id;
  private final String content;

  public AddCommentResponseDto(Long id, String content) {
    this.id = id;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
