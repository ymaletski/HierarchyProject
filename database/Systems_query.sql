-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema systems
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `systems` ;

-- -----------------------------------------------------
-- Schema systems
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `systems` DEFAULT CHARACTER SET utf8 ;
USE `systems` ;

-- -----------------------------------------------------
-- Table `systems`.`addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`addresses` ;

CREATE TABLE IF NOT EXISTS `systems`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `postcode` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`schools`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`schools` ;

CREATE TABLE IF NOT EXISTS `systems`.`schools` (
  `school_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`school_id`, `address_id`),
  INDEX `fk_schools_Addresses_idx` (`address_id` ASC),
  CONSTRAINT `fk_schools_Addresses`
    FOREIGN KEY (`address_id`)
    REFERENCES `systems`.`addresses` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`pupils`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`pupils` ;

CREATE TABLE IF NOT EXISTS `systems`.`pupils` (
  `pupil_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `school_id` INT NOT NULL,
  PRIMARY KEY (`pupil_id`, `school_id`),
  INDEX `fk_pupils_schools_idx` (`school_id` ASC),
  CONSTRAINT `fk_pupils_schools`
    FOREIGN KEY (`school_id`)
    REFERENCES `systems`.`schools` (`school_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`teachers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`teachers` ;

CREATE TABLE IF NOT EXISTS `systems`.`teachers` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `school_id` INT NOT NULL,
  PRIMARY KEY (`teacher_id`, `school_id`),
  INDEX `fk_teachers_schools_idx` (`school_id` ASC),
  CONSTRAINT `fk_teachers_schools`
    FOREIGN KEY (`school_id`)
    REFERENCES `systems`.`schools` (`school_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`universities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`universities` ;

CREATE TABLE IF NOT EXISTS `systems`.`universities` (
  `university_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`university_id`, `address_id`),
  INDEX `fk_universities_addresses_idx` (`address_id` ASC),
  CONSTRAINT `fk_universities_addresses`
    FOREIGN KEY (`address_id`)
    REFERENCES `systems`.`addresses` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`specialities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`specialities` ;

CREATE TABLE IF NOT EXISTS `systems`.`specialities` (
  `speciality_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`speciality_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`students` ;

CREATE TABLE IF NOT EXISTS `systems`.`students` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `speciality_id` INT NOT NULL,
  `university_id` INT NOT NULL,
  PRIMARY KEY (`student_id`, `speciality_id`, `university_id`),
  INDEX `fk_students_specialities_idx` (`speciality_id` ASC),
  INDEX `fk_students_universities_idx` (`university_id` ASC),
  CONSTRAINT `fk_students_specialities`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `systems`.`specialities` (`speciality_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_students_universities`
    FOREIGN KEY (`university_id`)
    REFERENCES `systems`.`universities` (`university_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`lecturers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`lecturers` ;

CREATE TABLE IF NOT EXISTS `systems`.`lecturers` (
  `lecturer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `university_id` INT NOT NULL,
  PRIMARY KEY (`lecturer_id`, `university_id`),
  INDEX `fk_lecturers_universities_idx` (`university_id` ASC),
  CONSTRAINT `fk_lecturers_universities`
    FOREIGN KEY (`university_id`)
    REFERENCES `systems`.`universities` (`university_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`universities_has_specialities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`universities_has_specialities` ;

CREATE TABLE IF NOT EXISTS `systems`.`universities_has_specialities` (
  `university_id` INT NOT NULL,
  `speciality_id` INT NOT NULL,
  PRIMARY KEY (`university_id`, `speciality_id`),
  INDEX `fk_universities_has_specialities_specialities_idx` (`speciality_id` ASC),
  INDEX `fk_universities_has_specialities_universities_idx` (`university_id` ASC),
  CONSTRAINT `fk_universities_has_specialities_universities`
    FOREIGN KEY (`university_id`)
    REFERENCES `systems`.`universities` (`university_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_universities_has_specialities_specialities`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `systems`.`specialities` (`speciality_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`companies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`companies` ;

CREATE TABLE IF NOT EXISTS `systems`.`companies` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`company_id`, `address_id`),
  INDEX `fk_companies_addresses_idx` (`address_id` ASC),
  CONSTRAINT `fk_companies_addresses`
    FOREIGN KEY (`address_id`)
    REFERENCES `systems`.`addresses` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`projects` ;

CREATE TABLE IF NOT EXISTS `systems`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `company_id`),
  INDEX `fk_projects_companies_idx` (`company_id` ASC),
  CONSTRAINT `fk_projects_companies`
    FOREIGN KEY (`company_id`)
    REFERENCES `systems`.`companies` (`company_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `systems`.`projects_has_specialities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `systems`.`projects_has_specialities` ;

CREATE TABLE IF NOT EXISTS `systems`.`projects_has_specialities` (
  `project_id` INT NOT NULL,
  `speciality_id` INT NOT NULL,
  INDEX `fk_projects_has_specialities_specialities_idx` (`speciality_id` ASC),
  INDEX `fk_projects_has_specialities_projects_idx` (`project_id` ASC),
  CONSTRAINT `fk_projects_has_specialities_projects`
    FOREIGN KEY (`project_id`)
    REFERENCES `systems`.`projects` (`project_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_projects_has_specialities_specialities`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `systems`.`specialities` (`speciality_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
