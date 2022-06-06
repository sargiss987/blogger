package com.acba.blogger.dto.post;

import java.util.Set;

public class AddPostDto {

  private final String title;
  private final String content;
  private final Set<String> categories;

  public AddPostDto(String title, String content, Set<String> categories) {
    this.title = title;
    this.content = content;
    this.categories = categories;
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
