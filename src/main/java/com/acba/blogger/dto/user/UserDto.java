package com.acba.blogger.dto.user;

import java.util.Set;

public class UserDto {

  private final Long id;
  private final Boolean isEnabled;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String roleType;
  private final Set<UserCommentDto> comments;
  private final Set<UserPostDto> posts;

  public UserDto(
      Long id,
      Boolean isEnabled,
      String firstName,
      String lastName,
      String email,
      String roleType,
      Set<UserCommentDto> comments,
      Set<UserPostDto> posts) {
    this.id = id;
    this.isEnabled = isEnabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roleType = roleType;
    this.comments = comments;
    this.posts = posts;
  }

  public Long getId() {
    return id;
  }

  public Boolean getEnabled() {
    return isEnabled;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getRoleType() {
    return roleType;
  }

  public Set<UserCommentDto> getComments() {
    return comments;
  }

  public Set<UserPostDto> getPosts() {
    return posts;
  }
}
