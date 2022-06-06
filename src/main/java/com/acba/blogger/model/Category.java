package com.acba.blogger.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "post_category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "type", nullable = false)
  private String type;

  @ManyToMany
  @JoinTable(
      name = "post_category_post",
      joinColumns = @JoinColumn(name = "post_category_id"),
      inverseJoinColumns = @JoinColumn(name = "post_id"))
  private Set<Post> posts;

  public Category(Long id) {
    this.id = id;
  }

  public static Category getInstance(Long id) {
    return new Category(id);
  }

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
