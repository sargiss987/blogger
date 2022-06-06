package com.acba.blogger.dto.post;

import java.util.Set;

public class AddPostResponseDto {

  private final Long id;
  private final String title;
  private final String content;
  private final Set<String> categories;

  public AddPostResponseDto(Long id, String title, String content, Set<String> categories) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.categories = categories;
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

  public Set<String> getCategories() {
    return categories;
  }
}
