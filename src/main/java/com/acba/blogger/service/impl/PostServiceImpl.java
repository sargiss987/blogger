package com.acba.blogger.service.impl;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.mapper.CommentMapper;
import com.acba.blogger.model.Comment;
import com.acba.blogger.model.User;
import com.acba.blogger.repository.CommentRepository;
import com.acba.blogger.repository.PostRepository;
import com.acba.blogger.service.PostService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
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
}
