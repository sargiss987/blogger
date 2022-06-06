package com.acba.blogger.repository;

import com.acba.blogger.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "SELECT * FROM posts ORDER BY rating DESC", nativeQuery = true)
  Optional<List<Post>> getAllPostsSortedByRating();
}
