package com.acba.blogger.dto.user;

public class UserPostDto {

  private final String title;
  private final String content;
  private final Boolean isActive;
  private final Integer rating;

  public UserPostDto(String title, String content, Boolean isActive, Integer rating) {
    this.title = title;
    this.content = content;
    this.isActive = isActive;
    this.rating = rating;
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
}
