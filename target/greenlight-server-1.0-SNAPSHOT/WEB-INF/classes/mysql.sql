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

CREATE TABLE `greenlight`.`street_channel` (
  `sc_id` INT NOT NULL AUTO_INCREMENT,
  `street_id` INT NOT NULL,
  `channel_id` INT NOT NULL,
  PRIMARY KEY (`sc_id`));

ALTER TABLE `greenlight`.`street_channel`
ADD INDEX `fk_sc_street_idx` (`street_id` ASC),
ADD INDEX `fk_sc_channel_idx` (`channel_id` ASC);
ALTER TABLE `greenlight`.`street_channel`
ADD CONSTRAINT `fk_sc_street`
  FOREIGN KEY (`street_id`)
  REFERENCES `greenlight`.`street` (`street_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_sc_channel`
  FOREIGN KEY (`channel_id`)
  REFERENCES `greenlight`.`channel` (`channel_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `greenlight`.`user_channel` (
  `channel_id` INT NOT NULL AUTO_INCREMENT,
  `channel_name` TEXT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`channel_id`));

CREATE TABLE `greenlight`.`user_channel_street` (
  `ucs_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `channel_id` INT NOT NULL,
  PRIMARY KEY (`ucs_id`));

ALTER TABLE `greenlight`.`user_channel_street`
CHANGE COLUMN `user_id` `street_id` INT(11) NOT NULL ;

ALTER TABLE `greenlight`.`user_channel_street`
ADD INDEX `fk_ucs_channel_idx` (`channel_id` ASC),
ADD INDEX `fk_ucs_street_idx` (`street_id` ASC);
ALTER TABLE `greenlight`.`user_channel_street`
ADD CONSTRAINT `fk_ucs_channel`
  FOREIGN KEY (`channel_id`)
  REFERENCES `greenlight`.`user_channel` (`channel_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_ucs_street`
  FOREIGN KEY (`street_id`)
  REFERENCES `greenlight`.`street` (`street_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `greenlight`.`event`
ADD COLUMN `longitude` DOUBLE NULL AFTER `user_id`,
ADD COLUMN `latitude` DOUBLE NULL AFTER `longitude`;

ALTER TABLE `greenlight`.`event`
ADD COLUMN `altitude` DOUBLE NULL AFTER `latitude`;

CREATE TABLE `greenlight`.`files` (
  `files_id` INT NOT NULL AUTO_INCREMENT,
  `files_data` BLOB ,
  PRIMARY KEY (`files_id`));

ALTER TABLE `greenlight`.`event`
ADD COLUMN `audio_id` INT(11) NULL AFTER `altitude`;




