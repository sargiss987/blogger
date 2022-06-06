package com.acba.blogger.repository;

import com.acba.blogger.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "SELECT * FROM posts ORDER BY rating DESC", nativeQuery = true)
  Optional<List<Post>> getAllPostsSortedByRating();

  @Modifying
  @Transactional
  @Query(
      value =
          "UPDATE posts p JOIN "
              + " (SELECT posts_id, avg(grade) as average"
              + " from post_grades pg"
              + " group by posts_id) pg"
              + " on p.id = pg.posts_id"
              + " SET p.rating = pg.average",
      nativeQuery = true)
  void updatePostRating();
}
