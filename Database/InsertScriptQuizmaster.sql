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
-- Dumping data for table `course`
--

INSERT INTO course (nameCourse, idCoordinatorCourse) VALUES ('Programming',22),('Databases',23),('Advanced Programming',22),
('NoSQL Databases', 23), ('GIT', 24); 

--
-- Dumping data for table `group`
--

INSERT INTO `group` (idCoordinatorGroup, idCourse) VALUES (5,2),(22,3),(23,4),(5,2),(22,3),(23,4);

--
-- Dumping data for table `question`
--

INSERT INTO question (idQuiz, questionString, answerA, answerB, answerC, answerD) 
VALUES (1,'Which HTML element contains CSS sheets?','<style>','<head>','<base>','<link>'),
(2,'Database Question 1?','A1','B1','C1','D1'),
(2,'Database Question 2?','A2','B2','C2','D2'),
(2,'Database Question 3?','A3','B3','C3','D3'),
(4,'Advanced Programming 1 Question 1?','a1','b1','c1','d1'),
(4,'Advanced Programming 1 Question 2?','a2','b2','c2','d2'),
(5,'Advanced Programming 2 Question 1?','a3','b3','c3','d3'),
(6,'JSON en CouchDB Question','a4','b4','c4','d4'),
(7,'Test Vraag om te verwijderen?','a1','b1','c1','d1');

--
-- Dumping data for table `quiz`
--

INSERT INTO quiz (idCourse, nameQuiz, succesDefinition) VALUES 
(1,'Basic Programming',8), 
(2,'Database ontwerp',7),
(2,'Database bouw en gebruik',8),
(3,'Advanced programming 1',8),
(3, 'Advanced programming 2', 6),
(4, 'JSON en CouchDB',7),
(5, 'GIT commandos',8);


-- Dumping data for table `role`
--

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');

--
-- Dumping data for table `studentincourse`
--


--
-- Dumping data for table `user`
--

INSERT INTO user(password, name, roleName) VALUES ('s1','s1','student'),('d1','d1','docent'),('a1','a1','admin'),('t1','t1','technischBeheerder'),('c1','c1','coordinator'),
('s2','s2','student'),('s3','s3','student'),('s4','s4','student'),('s5','s5','student'),
('d2','d2','docent'),('d3','d3','docent'),('d4','d4','docent'),('d5','d5','docent'),
('a2','a2','admin'),('a3','a3','admin'),('a4','a4','admin'),('a5','a5','admin'),
('t2','t2','technischBeheerder'),('t3','t3','technischBeheerder'),('t4','t4','technischBeheerder'),
('t5','t5','technischBeheerder'),('c2','c2','coordinator'),('c3','c3','coordinator'),('c4','c4','coordinator'),
('q','q','technischBeheerder');
--
-- Dumping data for table `usermakesquiz`
--
INSERT INTO `quizmaster`.`studentincourse` (`idCourse`, `idStudent`) VALUES ('1', '1'),('2', '1'),('3', '1'),('4', '5')
,('1', '6'),('1', '7'),('1', '8'),('1', '8'),('2', '6'),('2', '7'),('2', '8'),('2', '9');


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
