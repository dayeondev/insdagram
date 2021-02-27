CREATE TABLE ACCOUNT (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(128) NULL,
  `bio` VARCHAR(255) NULL,
  `name` VARCHAR(32) NULL,
  `website` VARCHAR(128) NULL,
  `profile_image` INT NULL,
  `phone_number` VARCHAR(32) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IMAGE (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `directory` VARCHAR(255) NULL,
  `caption` VARCHAR(255) NULL,
  `location` VARCHAR(32) NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`));


