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

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS course;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE course (
  idCourse int NOT NULL,
  nameCourse varchar(45) NOT NULL,
  idCoordinatorCourse int NOT NULL,
  PRIMARY KEY (idCourse),
  KEY fk_Cursus_Gebruiker1_idx (idCoordinatorCourse),
  CONSTRAINT fk_Cursus_Gebruiker1 FOREIGN KEY (idCoordinatorCourse) REFERENCES `user` (idUser) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--


--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS group;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--


--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS question;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--


--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS quiz;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE quiz (
  idQuiz int NOT NULL,
  idCourse int NOT NULL,
  nameQuiz varchar(45) NOT NULL,
  succesDefinition decimal(5,2) NOT NULL,
  PRIMARY KEY (idQuiz,idCourse),
  KEY fk_Quiz_Course1_idx (idCourse),
  CONSTRAINT fk_Quiz_Course1 FOREIGN KEY (idCourse) REFERENCES course (idCourse) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS role;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  roleName varchar(45) NOT NULL,
  PRIMARY KEY (roleName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('techbeheer');

--
-- Table structure for table `studentincourse`
--

DROP TABLE IF EXISTS studentincourse;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE studentincourse (
  idCourse int NOT NULL,
  idStudent int NOT NULL,
  PRIMARY KEY (idCourse,idStudent),
  KEY fk_Course_has_User_User1_idx (idStudent),
  KEY fk_Course_has_User_Course1_idx (idCourse),
  CONSTRAINT fk_Course_has_User_Course1 FOREIGN KEY (idCourse) REFERENCES course (idCourse) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_Course_has_User_User1 FOREIGN KEY (idStudent) REFERENCES `user` (idUser) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentincourse`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  idUser int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  roleName varchar(45) NOT NULL,
  PRIMARY KEY (idUser),
  UNIQUE KEY name_UNIQUE (`name`),
  KEY fk_Gebruiker_Rol1_idx (roleName),
  CONSTRAINT fk_Gebruiker_Rol1 FOREIGN KEY (roleName) REFERENCES `role` (roleName) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO user VALUES (1,'s','s','student'),(2,'d','d','docent'),(3,'a','a','admin'),(4,'t','t','techbeheer'),(5,'c','c','coordinator');

--
-- Table structure for table `usermakesquiz`
--

DROP TABLE IF EXISTS usermakesquiz;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

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
