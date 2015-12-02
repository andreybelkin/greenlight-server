CREATE SCHEMA `greenlight` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `greenlight`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`));

CREATE TABLE `greenlight`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `message` TEXT NOT NULL,
  PRIMARY KEY (`comment_id`));

ALTER TABLE `greenlight`.`comment`
ADD INDEX `fk_comment_user_idx` (`user_id` ASC);
ALTER TABLE `greenlight`.`comment`
ADD CONSTRAINT `fk_comment_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `greenlight`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

