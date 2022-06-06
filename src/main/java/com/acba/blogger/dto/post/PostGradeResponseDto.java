package com.acba.blogger.dto.post;

public class PostGradeResponseDto {

  private final Long id;
  private final Integer grade;

  public PostGradeResponseDto(Long id, Integer grade) {
    this.id = id;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public Integer getGrade() {
    return grade;
  }
}
