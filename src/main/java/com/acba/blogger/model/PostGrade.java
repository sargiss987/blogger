package com.acba.blogger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post_grades")
public class PostGrade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "grade")
  private Integer grade;

  @ManyToOne
  @JoinColumn(name = "posts_id")
  private Post post;

  public PostGrade(Integer grade, Post post) {
    this.grade = grade;
    this.post = post;
  }

  public static boolean isValidGrade(Integer grade) {
    return (grade != null && grade > 0 && grade <= 5);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
