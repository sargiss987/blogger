package com.acba.blogger.service.impl;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.mapper.CommentMapper;
import com.acba.blogger.model.Comment;
import com.acba.blogger.model.Post;
import com.acba.blogger.model.PostGrade;
import com.acba.blogger.model.User;
import com.acba.blogger.repository.CommentRepository;
import com.acba.blogger.repository.PostGradeRepository;
import com.acba.blogger.repository.PostRepository;
import com.acba.blogger.service.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final PostGradeRepository postGradeRepository;

  public PostServiceImpl(
      PostRepository postRepository,
      CommentRepository commentRepository,
      PostGradeRepository postGradeRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
    this.postGradeRepository = postGradeRepository;
  }

  @Override
  @Transactional
  public Optional<Comment> addComment(User user, Long postId, AddCommentDto addCommentDto) {
    return postRepository
        .findById(postId)
        .map(
            post ->
                commentRepository.save(
                    CommentMapper.addCommentDtoToComment(addCommentDto, user, post)));
  }

  @Override
  public Optional<List<Post>> getAllPostsSortedByRating() {
    return postRepository.getAllPostsSortedByRating();
  }

  @Override
  @Transactional
  public Optional<PostGrade> estimatePost(Long id, Integer grade) {
    Optional<Post> post = postRepository.findById(id);
    Optional<PostGrade> postGrade = Optional.empty();
    if (post.isPresent() && PostGrade.isValidGrade(grade)) {
      postGrade = Optional.of(postGradeRepository.save(new PostGrade(grade, post.get())));
    }
    return postGrade;
  }
}
