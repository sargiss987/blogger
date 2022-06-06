package com.acba.blogger.mapper;

import com.acba.blogger.dto.post.AddPostDto;
import com.acba.blogger.dto.post.AddPostResponseDto;
import com.acba.blogger.dto.post.PostDto;
import com.acba.blogger.model.Category;
import com.acba.blogger.model.Post;
import com.acba.blogger.model.User;
import com.acba.blogger.model.enums.CategoryType;
import java.util.Set;
import java.util.stream.Collectors;

public class PostMapper {

  public static AddPostResponseDto postToAddPostResponseDto(Post post) {
    return new AddPostResponseDto(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getCategories().stream().map(Category::getType).collect(Collectors.toSet()));
  }

  public static Post addPostDtoToPost(AddPostDto addPostDto, Long userId) {
    Post post = new Post(addPostDto.getTitle(), addPostDto.getContent());
    post.setBlogger(new User(userId));
    setCategories(post, addPostDto.getCategories());
    return post;
  }

  public static PostDto postToPostDto(Post post) {
    return new PostDto(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.isActive(),
        post.getRating(),
        createFullName(post.getBlogger().getFirstName(), post.getBlogger().getLastName()),
        post.getComments().stream()
            .map(CommentMapper::commentToPostCommentDto)
            .collect(Collectors.toList()));
  }

  private static void setCategories(Post post, Set<String> categories) {
    post.setCategories(
        categories.stream()
            .filter(type -> CategoryType.getIdByType(type) != null)
            .map(type -> Category.getInstance(CategoryType.getIdByType(type)))
            .collect(Collectors.toSet()));
  }

  private static String createFullName(String firstName, String lastName) {
    return firstName + " " + lastName;
  }
}
