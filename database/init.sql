CREATE SCHEMA `bike_app_db`;

CREATE TABLE `bike_app_db`.`journey` (
  `journey_id` INT NOT NULL AUTO_INCREMENT,
  `departure` DATETIME NULL,
  `return` DATETIME NULL,
  `departure_station_id` INT NULL,
  `departure_station_name` VARCHAR(45) NULL,
  `return_station_id` INT NULL,
  `return_station_name` VARCHAR(45) NULL,
  `distance` INT NULL,
  `duration` INT NULL,
  PRIMARY KEY (`journey_id`));

CREATE TABLE `bike_app_db`.`station` (
  `station_id` INT NOT NULL AUTO_INCREMENT,
  `journey_station_id` INT NULL,
  `name_finnish` VARCHAR(45) NULL,
  `name_swedish` VARCHAR(45) NULL,
  `address_finnish` VARCHAR(45) NULL,
  `address_swedish` VARCHAR(45) NULL,
  `city_finnish` VARCHAR(45) NULL,
  `city_swedish` VARCHAR(45) NULL,
  `operator` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `x` DOUBLE NULL,
  `y` DOUBLE NULL,
  PRIMARY KEY (`station_id`));

UPDATE station SET city_finnish = 'Helsinki', city_swedish = 'Helsingfors' where station_id > 110;