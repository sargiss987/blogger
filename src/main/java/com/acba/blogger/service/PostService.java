package com.acba.blogger.service;

import com.acba.blogger.dto.comment.AddCommentDto;
import com.acba.blogger.model.Comment;
import com.acba.blogger.model.User;
import java.util.Optional;

public interface PostService {

  Optional<Comment> addComment(User user, Long postId, AddCommentDto addCommentDto);
}
