CREATE TRIGGER on_insert_to_post_grades
AFTER INSERT ON post_grades
FOR EACH ROW
UPDATE posts p JOIN
      (SELECT posts_id, avg(grade) as average
       from post_grades pg
       group by posts_id) pg
       on p.id = pg.posts_id
SET p.rating = pg.average