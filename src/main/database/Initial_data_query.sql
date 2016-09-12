-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema initial_data
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `initial_data` ;

-- -----------------------------------------------------
-- Schema initial_data
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `initial_data` DEFAULT CHARACTER SET utf8 ;
USE `initial_data` ;

-- -----------------------------------------------------
-- Table `initial_data`.`names_male`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`names_male` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`names_male` (
  `name_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`names_female`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`names_female` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`names_female` (
  `name_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`surnames_male`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`surnames_male` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`surnames_male` (
  `surname_id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`surname_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`surnames_female`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`surnames_female` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`surnames_female` (
  `surname_id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`surname_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`schools`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`schools` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`schools` (
  `school_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`school_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`universities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`universities` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`universities` (
  `university_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`university_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`specialities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`specialities` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`specialities` (
  `specialitiy_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`specialitiy_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`companies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`companies` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`companies` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`company_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `initial_data`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `initial_data`.`projects` ;

CREATE TABLE IF NOT EXISTS `initial_data`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`project_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
