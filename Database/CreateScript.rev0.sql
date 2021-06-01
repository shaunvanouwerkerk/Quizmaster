-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema QuizzyDraw
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema QuizzyDraw
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `QuizzyDraw` DEFAULT CHARACTER SET utf8 ;
USE `QuizzyDraw` ;

-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Role` (
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roleName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_Gebruiker_Rol1_idx` (`roleName` ASC) VISIBLE,
  CONSTRAINT `fk_Gebruiker_Rol1`
    FOREIGN KEY (`roleName`)
    REFERENCES `QuizzyDraw`.`Role` (`roleName`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Course` (
  `nameCourse` VARCHAR(45) NOT NULL,
  `idCoordinator` INT NOT NULL,
  PRIMARY KEY (`nameCourse`),
  INDEX `fk_Cursus_Gebruiker1_idx` (`idCoordinator` ASC) VISIBLE,
  CONSTRAINT `fk_Cursus_Gebruiker1`
    FOREIGN KEY (`idCoordinator`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Quiz` (
  `idQuiz` INT NOT NULL,
  `nameCourse` VARCHAR(45) NOT NULL,
  `nameQuiz` VARCHAR(45) NOT NULL,
  `succesDefinition` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`idQuiz`, `nameCourse`),
  INDEX `fk_Quiz_Cursus1_idx` (`nameCourse` ASC) VISIBLE,
  CONSTRAINT `fk_Quiz_Cursus1`
    FOREIGN KEY (`nameCourse`)
    REFERENCES `QuizzyDraw`.`Course` (`nameCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Group` (
  `groupName` VARCHAR(45) NOT NULL,
  `idCoordinator` INT NOT NULL,
  PRIMARY KEY (`groupName`),
  INDEX `fk_Group_Gebruiker1_idx` (`idCoordinator` ASC) VISIBLE,
  CONSTRAINT `fk_Group_Gebruiker1`
    FOREIGN KEY (`idCoordinator`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Question` (
  `idQuestion` INT NOT NULL,
  `questionString` VARCHAR(45) NOT NULL,
  `idQuiz` INT NOT NULL,
  PRIMARY KEY (`idQuestion`, `idQuiz`),
  INDEX `fk_Vraag_Quiz1_idx` (`idQuiz` ASC) VISIBLE,
  CONSTRAINT `fk_Vraag_Quiz1`
    FOREIGN KEY (`idQuiz`)
    REFERENCES `QuizzyDraw`.`Quiz` (`idQuiz`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Answer` (
  `idQuestion` INT NOT NULL,
  `idAnswer` INT NOT NULL,
  `answer` VARCHAR(45) NOT NULL,
  `correct` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idQuestion`, `idAnswer`),
  CONSTRAINT `fk_table1_Vraag`
    FOREIGN KEY (`idQuestion`)
    REFERENCES `QuizzyDraw`.`Question` (`idQuestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`GroepWithStudent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`GroepWithStudent` (
  `idUser` INT NOT NULL,
  `groupName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`, `groupName`),
  INDEX `fk_Gebruiker_has_Group_Group1_idx` (`groupName` ASC) VISIBLE,
  INDEX `fk_Gebruiker_has_Group_Gebruiker1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Gebruiker_has_Group_Gebruiker1`
    FOREIGN KEY (`idUser`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Gebruiker_has_Group_Group1`
    FOREIGN KEY (`groupName`)
    REFERENCES `QuizzyDraw`.`Group` (`groupName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`UserMakesQuiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`UserMakesQuiz` (
  `afnameNummerTotaalSysteem` INT NOT NULL AUTO_INCREMENT,
  `idQuiz` INT NOT NULL,
  `naamCursus` VARCHAR(45) NOT NULL,
  `idGebruiker` INT NOT NULL,
  `result` DOUBLE NOT NULL,
  PRIMARY KEY (`afnameNummerTotaalSysteem`),
  INDEX `fk_Quiz_has_Gebruiker_Gebruiker1_idx` (`idGebruiker` ASC) VISIBLE,
  INDEX `fk_Quiz_has_Gebruiker_Quiz1_idx` (`idQuiz` ASC, `naamCursus` ASC) VISIBLE,
  CONSTRAINT `fk_Quiz_has_Gebruiker_Quiz1`
    FOREIGN KEY (`idQuiz` , `naamCursus`)
    REFERENCES `QuizzyDraw`.`Quiz` (`idQuiz` , `nameCourse`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Quiz_has_Gebruiker_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
