-- INSERT ALL DATA IN TABLES
INSERT INTO course VALUES (1,'OOP',5),(2,'Programming',22),(3,'Databases',23),(4,'Advanced Programming',22);
INSERT INTO `group` Values(default, 5, 1), (default, 5, 2), (default, 22, 3), (default, 23, 4);
-- INSERT INTO Question
INSERT INTO quiz VALUES (1,1,'Bouw klassenstructuur',8),(2,1,'Gebruik klassen en JDBC',7),(3,1,'Bouw menu',6),(4,2,'Programming 1',8),(5,3,'Database ontwerp',7),(6,3,'Database bouw en gebruik',8),(7,4,'Advanced programming 1',8);
INSERT INTO role VALUES ('admin'),('coordinator'),('docent'),('student'),('technischBeheerder');
-- -- INSERT INTO studentincourse
INSERT INTO user VALUES (1,'s1','s1','student'),(2,'d1','d1','docent'),(3,'a1','a1','admin'),(4,'t1','t1','technischBeheerder'),(5,'c1','c1','coordinator'),(6,'s2','s2','student'),(7,'s3','s3','student'),(8,'s4','s4','student'),(9,'s5','s5','student'),(10,'d2','d2','docent'),(11,'d3','d3','docent'),(12,'d4','d4','docent'),(13,'d5','d5','docent'),(14,'a2','a2','admin'),(15,'a3','a3','admin'),(16,'a4','a4','admin'),(17,'a5','a5','admin'),(18,'t2','t2','technischBeheerder'),(19,'t3','t3','technischBeheerder'),(20,'t4','t4','technischBeheerder'),(21,'t5','t5','technischBeheerder'),(22,'c2','c2','coordinator'),(23,'c3','c3','coordinator'),(24,'c4','c4','coordinator'),(25,'c5','c5','coordinator');
-- -- INSERT INTO usermakesquiz