-- -----------------------------------------------------
-- Table `blogger_db`.`post_grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`post_grades` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `posts_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_grades_posts1_idx` (`posts_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_grades_posts1`
    FOREIGN KEY (`posts_id`)
    REFERENCES `blogger_db`.`posts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `blogger_db`.`comment_grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`comment_grades` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `comments_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_grades_comments1_idx` (`comments_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_grades_comments1`
    FOREIGN KEY (`comments_id`)
    REFERENCES `blogger_db`.`comments` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
