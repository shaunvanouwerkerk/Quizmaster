-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: quizzydraw
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema QuizzyDraw
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema QuizzyDraw
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `QuizzyDraw` DEFAULT CHARACTER SET utf8 ;
USE `QuizzyDraw` ;

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  roleName varchar(45) NOT NULL,
  PRIMARY KEY (roleName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');


--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  idUser int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  roleName varchar(45) NOT NULL,
  PRIMARY KEY (idUser),
  UNIQUE KEY name_UNIQUE (`name`),
  KEY fk_Gebruiker_Rol1_idx (roleName),
  CONSTRAINT fk_Gebruiker_Rol1 FOREIGN KEY (roleName) REFERENCES `role` (roleName) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO user VALUES (1,'s1','s1','student'),(2,'d1','d1','docent'),(3,'a1','a1','admin'),(4,'t1','t1','technischBeheerder'),(5,'c1','c1','coordinator'),(6,'s2','s2','student'),(7,'s3','s3','student'),(8,'s4','s4','student'),(9,'s5','s5','student'),(10,'d2','d2','docent'),(11,'d3','d3','docent'),(12,'d4','d4','docent'),(13,'d5','d5','docent'),(14,'a2','a2','admin'),(15,'a3','a3','admin'),(16,'a4','a4','admin'),(17,'a5','a5','admin'),(18,'t2','t2','technischBeheerder'),(19,'t3','t3','technischBeheerder'),(20,'t4','t4','technischBeheerder'),(21,'t5','t5','technischBeheerder'),(22,'c2','c2','coordinator'),(23,'c3','c3','coordinator'),(24,'c4','c4','coordinator'),(25,'c5','c5','coordinator');



--
-- Table structure for table `course`
--
CREATE TABLE course (
  idCourse int NOT NULL,
  nameCourse varchar(45) NOT NULL,
  idCoordinatorCourse int NOT NULL,
  PRIMARY KEY (idCourse),
  KEY fk_Cursus_Gebruiker1_idx (idCoordinatorCourse),
  CONSTRAINT fk_Cursus_Gebruiker1 FOREIGN KEY (idCoordinatorCourse) REFERENCES `user` (idUser) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--


--
-- Table structure for table `group`
--

CREATE TABLE `group` (
  idGroup int NOT NULL,
  idCoordinatorGroup int NOT NULL,
  idCourse int NOT NULL,
  PRIMARY KEY (idGroup),
  KEY fk_Group_Gebruiker1_idx (idCoordinatorGroup),
  KEY fk_Group_Course1_idx (idCourse),
  CONSTRAINT fk_Group_Course1 FOREIGN KEY (idCourse) REFERENCES course (idCourse) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_Group_Gebruiker1 FOREIGN KEY (idCoordinatorGroup) REFERENCES `user` (idUser) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--


--
-- Table structure for table `question`
--

CREATE TABLE question (
  idQuestion int NOT NULL,
  idQuiz int NOT NULL,
  questionString varchar(45) NOT NULL,
  answerA varchar(45) NOT NULL,
  answerB varchar(45) NOT NULL,
  answerC varchar(45) NOT NULL,
  answerD varchar(45) NOT NULL,
  PRIMARY KEY (idQuestion,idQuiz),
  KEY fk_Vraag_Quiz1_idx (idQuiz),
  CONSTRAINT fk_Vraag_Quiz1 FOREIGN KEY (idQuiz) REFERENCES quiz (idQuiz) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--


--
-- Table structure for table `quiz`
--

CREATE TABLE quiz (
  idQuiz int NOT NULL,
  idCourse int NOT NULL,
  nameQuiz varchar(45) NOT NULL,
  succesDefinition decimal(5,2) NOT NULL,
  PRIMARY KEY (idQuiz,idCourse),
  KEY fk_Quiz_Course1_idx (idCourse),
  CONSTRAINT fk_Quiz_Course1 FOREIGN KEY (idCourse) REFERENCES course (idCourse) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

--
-- Table structure for table `studentincourse`
--


CREATE TABLE studentincourse (
  idCourse int NOT NULL,
  idStudent int NOT NULL,
  PRIMARY KEY (idCourse,idStudent),
  KEY fk_Course_has_User_User1_idx (idStudent),
  KEY fk_Course_has_User_Course1_idx (idCourse),
  CONSTRAINT fk_Course_has_User_Course1 FOREIGN KEY (idCourse) REFERENCES course (idCourse) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_Course_has_User_User1 FOREIGN KEY (idStudent) REFERENCES `user` (idUser) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentincourse`
--

--
-- Table structure for table `usermakesquiz`
--

CREATE TABLE usermakesquiz (
  takeTestNumber int NOT NULL AUTO_INCREMENT,
  idQuiz int NOT NULL,
  idGebruiker int NOT NULL,
  numberAnswersRight int NOT NULL,
  dateQuiz date NOT NULL,
  PRIMARY KEY (takeTestNumber),
  KEY fk_Quiz_has_Gebruiker_Gebruiker1_idx (idGebruiker),
  KEY fk_Quiz_has_Gebruiker_Quiz1_idx (idQuiz),
  CONSTRAINT fk_Quiz_has_Gebruiker_Gebruiker1 FOREIGN KEY (idGebruiker) REFERENCES `user` (idUser) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_Quiz_has_Gebruiker_Quiz1 FOREIGN KEY (idQuiz) REFERENCES quiz (idQuiz) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermakesquiz`
--


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed

-- Create User & Grant access
CREATE USER 'quizzyuser'@'localhost' IDENTIFIED BY 'quizzyuserPW';
GRANT ALL PRIVILEGES ON quizzydraw .* TO 'quizzyuser'@'localhost';

