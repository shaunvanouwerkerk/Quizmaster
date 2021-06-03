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
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
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
  `idCourse` INT NOT NULL,
  `nameCourse` VARCHAR(45) NOT NULL,
  `idCoordinatorCourse` INT NOT NULL,
  INDEX `fk_Cursus_Gebruiker1_idx` (`idCoordinatorCourse` ASC) VISIBLE,
  PRIMARY KEY (`idCourse`),
  CONSTRAINT `fk_Cursus_Gebruiker1`
    FOREIGN KEY (`idCoordinatorCourse`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Quiz` (
  `idQuiz` INT NOT NULL,
  `idCourse` INT NOT NULL,
  `nameQuiz` VARCHAR(45) NOT NULL,
  `succesDefinition` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`idQuiz`, `idCourse`),
  INDEX `fk_Quiz_Course1_idx` (`idCourse` ASC) VISIBLE,
  CONSTRAINT `fk_Quiz_Course1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `QuizzyDraw`.`Course` (`idCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Group` (
  `idGroup` INT NOT NULL,
  `idCoordinatorGroup` INT NOT NULL,
  `idCourse` INT NOT NULL,
  INDEX `fk_Group_Gebruiker1_idx` (`idCoordinatorGroup` ASC) VISIBLE,
  INDEX `fk_Group_Course1_idx` (`idCourse` ASC) VISIBLE,
  PRIMARY KEY (`idGroup`),
  CONSTRAINT `fk_Group_Gebruiker1`
    FOREIGN KEY (`idCoordinatorGroup`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Group_Course1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `QuizzyDraw`.`Course` (`idCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`Question` (
  `idQuestion` INT NOT NULL,
  `idQuiz` INT NOT NULL,
  `questionString` VARCHAR(45) NOT NULL,
  `answerA` VARCHAR(45) NOT NULL,
  `answerB` VARCHAR(45) NOT NULL,
  `answerC` VARCHAR(45) NOT NULL,
  `answerD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idQuestion`, `idQuiz`),
  INDEX `fk_Vraag_Quiz1_idx` (`idQuiz` ASC) VISIBLE,
  CONSTRAINT `fk_Vraag_Quiz1`
    FOREIGN KEY (`idQuiz`)
    REFERENCES `QuizzyDraw`.`Quiz` (`idQuiz`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`UserMakesQuiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`UserMakesQuiz` (
  `takeTestNumber` INT NOT NULL AUTO_INCREMENT,
  `idQuiz` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `numberAnswersRight` INT NOT NULL,
  `dateQuiz` DATE NOT NULL,
  PRIMARY KEY (`takeTestNumber`),
  INDEX `fk_Quiz_has_Gebruiker_Gebruiker1_idx` (`idGebruiker` ASC) VISIBLE,
  INDEX `fk_Quiz_has_Gebruiker_Quiz1_idx` (`idQuiz` ASC) VISIBLE,
  CONSTRAINT `fk_Quiz_has_Gebruiker_Quiz1`
    FOREIGN KEY (`idQuiz`)
    REFERENCES `QuizzyDraw`.`Quiz` (`idQuiz`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Quiz_has_Gebruiker_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuizzyDraw`.`StudentInCourse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuizzyDraw`.`StudentInCourse` (
  `idCourse` INT NOT NULL,
  `idStudent` INT NOT NULL,
  PRIMARY KEY (`idCourse`, `idStudent`),
  INDEX `fk_Course_has_User_User1_idx` (`idStudent` ASC) VISIBLE,
  INDEX `fk_Course_has_User_Course1_idx` (`idCourse` ASC) VISIBLE,
  CONSTRAINT `fk_Course_has_User_Course1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `QuizzyDraw`.`Course` (`idCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Course_has_User_User1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `QuizzyDraw`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');
INSERT INTO user VALUES (1,'s1','s1','student'),(2,'d1','d1','docent'),(3,'a1','a1','admin'),(4,'t1','t1','technischBeheerder'),(5,'c1','c1','coordinator'),(6,'s2','s2','student'),(7,'s3','s3','student'),(8,'s4','s4','student'),(9,'s5','s5','student'),(10,'d2','d2','docent'),(11,'d3','d3','docent'),(12,'d4','d4','docent'),(13,'d5','d5','docent'),(14,'a2','a2','admin'),(15,'a3','a3','admin'),(16,'a4','a4','admin'),(17,'a5','a5','admin'),(18,'t2','t2','technischBeheerder'),(19,'t3','t3','technischBeheerder'),(20,'t4','t4','technischBeheerder'),(21,'t5','t5','technischBeheerder'),(22,'c2','c2','coordinator'),(23,'c3','c3','coordinator'),(24,'c4','c4','coordinator'),(25,'c5','c5','coordinator');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
