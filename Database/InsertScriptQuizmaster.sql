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

INSERT INTO course VALUES (2,'Programming',22),(3,'Databases',23),(4,'Advanced Programming',22);

--
-- Dumping data for table `group`
--

INSERT INTO `group` VALUES (2,5,2),(3,22,3),(4,23,4),(9,5,2),(10,22,3),(11,23,4);

--
-- Dumping data for table `question`
--

INSERT INTO question VALUES (7,4,'Which HTML element contains CSS sheets?','<style>','<head>','<base>','<link>'),(8,4,'Test Vraag om te verwijderen?','A1','B1','C1','D1'),(9,6,'Programming Quiz 1?','A2','B2','C2','D2'),(10,6,'Programming Quiz 2?','A3','B3','C3','D3'),(11,7,'Advanced Programming Quiz 1?','A4','B4','C4','D4'),(12,7,'Advanced Programming Quiz 2?','A5','B5','C5','D5');

--
-- Dumping data for table `quiz`
--

INSERT INTO quiz VALUES (4,2,'Programmin 1',8.00),(5,3,'Database ontwerp',7.00),(6,3,'Database bouw en gebruik',8.00),(7,4,'Advanced programming 1',8.00);

--
-- Dumping data for table `role`
--

INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');

--
-- Dumping data for table `studentincourse`
--


--
-- Dumping data for table `user`
--

INSERT INTO user VALUES (1,'s1','s1','student'),(2,'d1','d1','docent'),(3,'a1','a1','admin'),(4,'t1','t1','technischBeheerder'),(5,'c1','c1','coordinator'),(6,'s2','s2','student'),(7,'s3','s3','student'),(8,'s4','s4','student'),(9,'s5','s5','student'),(10,'d2','d2','docent'),(11,'d3','d3','docent'),(12,'d4','d4','docent'),(13,'d5','d5','docent'),(14,'a2','a2','admin'),(15,'a3','a3','admin'),(16,'a4','a4','admin'),(17,'a5','a5','admin'),(18,'t2','t2','technischBeheerder'),(19,'t3','t3','technischBeheerder'),(20,'t4','t4','technischBeheerder'),(21,'t5','t5','technischBeheerder'),(22,'c2','c2','coordinator'),(23,'c3','c3','coordinator'),(24,'c4','c4','coordinator'),(27,'q','q','technischBeheerder');

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
