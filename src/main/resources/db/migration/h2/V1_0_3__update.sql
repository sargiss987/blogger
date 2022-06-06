CREATE TRIGGER on_insert_to_post_grades
AFTER INSERT ON post_grades
FOR EACH ROW CALL "com.acba.blogger.util.UpdatePostRatingTrigger";


