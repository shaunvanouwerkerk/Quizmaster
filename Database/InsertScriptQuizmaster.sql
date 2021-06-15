-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: quizmaster
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


-- Dumping data for table `role`
--

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');

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
-- Dumping data for table `course`
--

INSERT INTO course (nameCourse, idCoordinatorCourse) VALUES ('Programming',22),('Databases',23),('Advanced Programming',22),
('NoSQL Databases', 23), ('GIT', 24); 

--
-- Dumping data for table `group`
--

INSERT INTO `group`(nameGroup, idCoordinatorGroup, idCourse) VALUES ('TR',23,7), ('BB',22,3), ('QQ',23,4), ('DD',5,2), ('EE',23,6), ('FF',24,3), ('GG',23,4),
 ('HH',24,3), ('II',22,4), ('JJ',23,3), ('KK',23,3), ('LL',24,3), ('STV',22,5), ('PRS',23,3),
  ('QUARANTEAM',23,6), ('SS',23,5);

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

--
-- Dumping data for table `question`
--

INSERT INTO `question` VALUES (1,1,'Which HTML element contains CSS sheets?','<style>','<head>','<base>','<link>'),
(2,1,'Which command will stop an infinite loop?','Ctrl-C','Alt-C','Shift-C','Esc'),
(3,1,'Software requirements specify___','What the task must perform','How the program will accomplish task','How to divide tasks into subtasks','How to test program when it is done'),
(4,1,'A loop that never ends is___','Infinite loop','While loop','Recursive loop','for loop'),
(5,1,'What does the expression float a=35/0 return?','Infinity','Not a number','0','Run time exception'),
(6,1,'In Java, jar stands for___','Java ARchive','Java Archive Runner','Java Application Runner','Java R'),
(7,2,'An attribute is an?','Column of a table','Row of a table','Two dimensional table','Key of a table'),
(8,2,'Row is synonymous with a term?','Record','Column','Relation','Field'),
(9,2,'A relation is considered a___?','Two-dimensional table','Column','Three-dimensional table','One-dimensional table'),
(10,2,'SQL stands for?','Structured Query Language','Structured Question Language','Sequential Query Language','Sequential Question Language'),
(11,4,'Which is the challenge in a J2EE?','Reliability','Fault tolerance','Durability','Scalability'),
(12,4,'Which is NOT a J2EE client?','JSP','Web applications','Applets','Java Web Start clients'),
(13,4,'Which is NOT a reserved word?','then','while','goto','if'),
(14,6,'JSON en CouchDB Question 1','antwoord a1','antwoord b1','antwoord c1','antwoord d1'),
(15,6,'JSON en CouchDB Question 2','antwoord a2','antwoord b2','antwoord c2','antwoord d2'),
(16,7,'Test Vraag om te verwijderen?','a1','b1','c1','d1'),
(17,7,'Test Vraag om te verwijderen?','a1','b1','c1','d1');


--
-- Dumping data for table `studentincourse`
--
INSERT INTO `quizmaster`.`studentincourse` (`idCourse`, `idStudent`) VALUES ('1', '1'),('2', '1'),('3', '1'),('4', '5'),
('1', '6'),('1', '7'),('1', '8'),('1', '8'),('2', '6'),('2', '7'),('2', '8'),('2', '9');

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
