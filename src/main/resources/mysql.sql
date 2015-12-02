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

CREATE TABLE `greenlight`.`event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`event_id`));

ALTER TABLE `greenlight`.`comment`
ADD COLUMN `event_id` INT NOT NULL AFTER `message`;

ALTER TABLE `greenlight`.`comment`
ADD INDEX `fk_comment_event_idx` (`event_id` ASC);
ALTER TABLE `greenlight`.`comment`
ADD CONSTRAINT `fk_comment_event`
  FOREIGN KEY (`event_id`)
  REFERENCES `greenlight`.`event` (`event_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `greenlight`.`street` (
  `street_id` INT NOT NULL AUTO_INCREMENT,
  `street_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`street_id`));

CREATE TABLE `greenlight`.`channel` (
  `channel_id` INT NOT NULL AUTO_INCREMENT,
  `channel_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`channel_id`));

