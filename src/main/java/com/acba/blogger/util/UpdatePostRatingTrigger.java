package com.acba.blogger.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.h2.api.Trigger;

public class UpdatePostRatingTrigger implements Trigger {

  @Override
  public void fire(Connection connection, Object[] objects, Object[] objects1) throws SQLException {
    try (PreparedStatement statement =
        connection.prepareStatement(
            "UPDATE posts p SET p.rating =\n"
                + " (SELECT avg(grade)\n"
                + " FROM post_grades pg\n"
                + " WHERE pg.posts_id = p.id)")) {
      statement.executeUpdate();
    }
  }
}
