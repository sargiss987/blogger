package com.acba.blogger.model;

import com.acba.blogger.model.enums.CategoryType;
import java.util.Set;

public class Category {

  private Long id;

  private String type;

  private Set<Post> posts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }
}
