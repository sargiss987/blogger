-- -----------------------------------------------------
-- Table `post_grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `post_grades` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `posts_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_post_grades_posts1`
    FOREIGN KEY (`posts_id`)
    REFERENCES `posts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_post_grades_posts1_idx` ON `post_grades` (`posts_id` ASC);

-- -----------------------------------------------------
-- Table `comment_grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comment_grades` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `comments_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_grades_comments1`
    FOREIGN KEY (`comments_id`)
    REFERENCES `comments` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_comment_grades_comments1_idx` ON `comment_grades` (`comments_id` ASC);



