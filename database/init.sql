CREATE SCHEMA `helsinki-city-bike-app` ;

CREATE TABLE `helsinki-city-bike-app`.`trip` (
  `trip_id` INT NOT NULL AUTO_INCREMENT,
  `departure` DATETIME NULL,
  `return` DATETIME NULL,
  `departure_station_id` INT NULL,
  `departure_station_name` VARCHAR(45) NULL,
  `return_station_id` INT NULL,
  `return_station_name` VARCHAR(45) NULL,
  `distance` INT NULL,
  `duration` INT NULL,
  PRIMARY KEY (`trip_id`));

CREATE TABLE `helsinki-city-bike-app`.`station` (
  `station_id` INT NOT NULL AUTO_INCREMENT,
  `station_id_trip_id` INT NULL,
  `name_finnish` VARCHAR(45) NULL,
  `name_swedish` VARCHAR(45) NULL,
  `address_finnish` VARCHAR(45) NULL,
  `address_swedish` VARCHAR(45) NULL,
  `city_finnish` VARCHAR(45) NULL,
  `city_swedish` VARCHAR(45) NULL,
  `operator` VARCHAR(45) NULL,
  `capacity` VARCHAR(45) NULL,
  `x` DOUBLE NULL,
  `y` DOUBLE NULL);

UPDATE station SET city_finnish = 'Helsinki', city_swedish = 'Helsingfors' where station_id > 110;