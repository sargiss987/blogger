package com.acba.blogger.mapper;

import com.acba.blogger.dto.post.PostGradeResponseDto;
import com.acba.blogger.model.PostGrade;

public class PostGradeMapper {

  public static PostGradeResponseDto postGradeToPostGradeResponseDto(PostGrade postGrade) {
    return new PostGradeResponseDto(postGrade.getId(), postGrade.getGrade());
  }
}
