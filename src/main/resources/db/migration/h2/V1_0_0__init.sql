-- -----------------------------------------------------
-- Schema blogger_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `blogger_db`;
USE `blogger_db` ;

-- -----------------------------------------------------
-- Table `blogger_db`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`user_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `blogger_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `is_enabled` BOOLEAN NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `user_role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `blogger_db`.`user_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_user_user_role_idx` ON `blogger_db`.`user` (`user_role_id` ASC);

-- -----------------------------------------------------
-- Table `blogger_db`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`post` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `is_active` BOOLEAN NOT NULL,
  `rating` INT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `blogger_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_post_user1_idx` ON `blogger_db`.`post` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `blogger_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `rating` INT NULL,
  `post_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `blogger_db`.`post` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `blogger_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_comment_post1_idx` ON `blogger_db`.`comment` (`post_id` ASC);
CREATE INDEX `fk_comment_user1_idx` ON `blogger_db`.`comment` (`user_id` ASC);

-- -----------------------------------------------------
-- Table `blogger_db`.`post_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`post_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `blogger_db`.`post_category_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blogger_db`.`post_category_post` (
  `post_category_id` BIGINT NOT NULL,
  `post_id` BIGINT NOT NULL,
  PRIMARY KEY (`post_category_id`, `post_id`),
  CONSTRAINT `fk_post_category_has_post_post_category1`
    FOREIGN KEY (`post_category_id`)
    REFERENCES `blogger_db`.`post_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_post_category_has_post_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `blogger_db`.`post` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE INDEX `fk_post_category_has_post_post1_idx` ON `blogger_db`.`post_category_post` (`post_id` ASC);
CREATE INDEX `fk_post_category_has_post_post_category1_idx` ON `blogger_db`.`post_category_post` (`post_category_id` ASC);

INSERT INTO user_role (type) VALUES ('BLOGGER');
INSERT INTO user_role (type) VALUES ('ADMIN');

INSERT INTO post_category (type) VALUES ('SPORT');
INSERT INTO post_category (type) VALUES ('POLITICS');
INSERT INTO post_category (type) VALUES ('EDUCATION');
INSERT INTO post_category (type) VALUES ('LIFESTYLE');
INSERT INTO post_category (type) VALUES ('FOOD');
INSERT INTO post_category (type) VALUES ('TRAVELING');

