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

INSERT INTO course VALUES (1,'OOP',5),(2,'Programming',22),(3,'Databases',23),(4,'Advanced Programming',22);

--
-- Dumping data for table `group`
--


--
-- Dumping data for table `question`
--


--
-- Dumping data for table `quiz`
--


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

INSERT INTO user VALUES (1,'s1','s1','student'),(2,'d1','d1','docent'),(3,'a1','a1','admin'),(4,'t1','t1','technischBeheerder'),(5,'c1','c1','coordinator'),(6,'s2','s2','student'),(7,'s3','s3','student'),(8,'s4','s4','student'),(9,'s5','s5','student'),(10,'d2','d2','docent'),(11,'d3','d3','docent'),(12,'d4','d4','docent'),(13,'d5','d5','docent'),(14,'a2','a2','admin'),(15,'a3','a3','admin'),(16,'a4','a4','admin'),(17,'a5','a5','admin'),(18,'t2','t2','technischBeheerder'),(19,'t3','t3','technischBeheerder'),(20,'t4','t4','technischBeheerder'),(21,'t5','t5','technischBeheerder'),(22,'c2','c2','coordinator'),(23,'c3','c3','coordinator'),(24,'c4','c4','coordinator'),(25,'c5','c5','coordinator');

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

CREATE USER 'quizzyuser'@'localhost' IDENTIFIED BY 'quizzyuserPW';
GRANT ALL PRIVILEGES ON quizzydraw .* TO 'quizzyuser'@'localhost';
