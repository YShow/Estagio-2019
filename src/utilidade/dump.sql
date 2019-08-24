-- MySQL dump 10.16  Distrib 10.2.25-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cantagalo
-- ------------------------------------------------------
-- Server version	10.2.25-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caixa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `preco_total` decimal(10,2) NOT NULL,
  `saida` decimal(10,2) DEFAULT NULL,
  `codigo_cliente` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_cliente` (`codigo_cliente`),
  CONSTRAINT `caixa_ibfk_1` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
INSERT INTO `caixa` VALUES (1,'1995-10-17',34080468.61,1.00,79),(2,'1987-04-17',92895536.90,2.00,4),(3,'1995-04-28',21064270.87,3.00,34),(4,'2008-09-28',29534156.98,4.00,89),(5,'2018-08-09',22983248.57,5.00,25),(6,'1990-11-09',59318888.95,6.00,80),(7,'1989-09-09',37763648.91,7.00,84),(8,'2012-08-10',84283632.73,8.00,9),(9,'2002-08-02',16119564.71,9.00,100),(10,'1981-03-25',86268216.82,10.00,87),(11,'2017-07-23',9658432.14,11.00,94),(12,'2019-11-06',72465408.31,12.00,95),(13,'2017-02-06',8942348.85,13.00,86),(14,'1984-03-29',12508291.15,14.00,13),(15,'2017-12-07',71020720.25,15.00,40),(16,'1993-07-28',26058214.17,16.00,88),(17,'2013-02-11',15301973.90,17.00,27),(18,'1997-09-20',53434760.91,18.00,73),(19,'1997-06-23',60834192.96,19.00,22),(20,'2009-05-16',90270168.17,20.00,14),(21,'1980-09-29',73151424.68,21.00,81),(22,'1996-09-23',74162648.64,22.00,19),(23,'1991-03-22',22412264.51,23.00,20),(24,'2013-06-20',74599744.25,24.00,25),(25,'2011-09-20',40769808.44,25.00,55),(26,'2013-07-21',93841344.37,26.00,20),(27,'2014-03-19',21490104.54,27.00,68),(28,'2014-08-03',36870308.42,28.00,35),(29,'2011-11-30',67564776.29,29.00,36),(30,'2005-04-03',42878704.91,30.00,35),(31,'1990-03-19',67290696.96,31.00,93),(32,'1994-01-21',93063576.21,32.00,9),(33,'2007-12-09',18924684.97,33.00,35),(34,'2000-03-19',62823528.57,34.00,84),(35,'2000-12-17',18037784.50,35.00,6),(36,'1985-08-28',77906136.47,36.00,85),(37,'2011-04-09',58126648.12,37.00,10),(38,'1989-10-24',59846888.16,38.00,4),(39,'2017-07-14',27485908.81,39.00,87),(40,'2012-07-07',16417265.92,40.00,95),(41,'2001-11-29',39966316.76,41.00,96),(42,'1982-10-16',97079328.29,42.00,32),(43,'1981-03-15',42260944.72,43.00,28),(44,'1992-04-13',5004990.89,44.00,68),(45,'1994-12-28',50560988.64,45.00,79),(46,'1991-07-29',38502248.85,46.00,15),(47,'2018-04-11',91151648.17,47.00,81),(48,'1990-01-22',42201404.50,48.00,34),(49,'1990-11-02',35687280.94,49.00,6),(50,'2011-09-20',37658232.44,50.00,15),(51,'2010-10-02',16965670.50,51.00,66),(52,'1981-12-28',93757064.59,52.00,79),(53,'1980-12-02',96050080.94,53.00,56),(54,'1982-03-24',98136616.97,54.00,44),(55,'2016-08-30',89161136.77,55.00,29),(56,'1989-03-05',24568158.53,56.00,60),(57,'1981-07-20',92039496.20,57.00,44),(58,'1984-05-03',19606668.83,58.00,56),(59,'2001-05-28',1187527.20,59.00,33),(60,'2015-08-19',80789224.58,60.00,55),(61,'1985-08-30',92817744.35,61.00,62),(62,'1999-12-24',83112600.85,62.00,47),(63,'2011-12-25',317156.17,63.00,17),(64,'2018-03-25',59487920.30,64.00,36),(65,'1994-10-01',24988038.00,65.00,94),(66,'1983-09-10',22410614.45,66.00,74),(67,'2006-08-11',19721300.99,67.00,39),(68,'2011-02-11',60327272.85,68.00,93),(69,'2004-02-27',24632704.95,69.00,35),(70,'2001-10-22',74319936.98,70.00,47),(71,'1996-03-30',82642696.44,71.00,30),(72,'1984-11-09',74014896.47,72.00,61),(73,'1982-05-26',73167936.63,73.00,18),(74,'1993-03-31',375920.32,74.00,48),(75,'2018-05-26',26738744.16,75.00,60),(76,'1991-03-07',25637382.10,76.00,91),(77,'1987-03-26',2247715.84,77.00,70),(78,'2009-12-02',24514956.11,78.00,5),(79,'1999-03-09',53017348.69,79.00,94),(80,'1993-05-08',57502068.17,80.00,92),(81,'1993-11-02',85859592.74,81.00,82),(82,'2005-02-01',37087060.87,82.00,69),(83,'1995-10-13',38260156.78,83.00,81),(84,'2019-06-18',5737316.65,84.00,26),(85,'2014-05-10',98790176.48,85.00,62),(86,'2009-04-02',74183736.51,86.00,22),(87,'1987-05-06',62425388.90,87.00,63),(88,'1995-08-11',97656280.35,88.00,33),(89,'1994-09-20',9798402.29,89.00,62),(90,'1994-10-28',93084792.29,90.00,73),(91,'1985-08-18',93245336.91,91.00,41),(92,'1982-11-06',72768056.50,92.00,29),(93,'2019-03-17',95119688.39,93.00,83),(94,'1983-03-05',29569500.35,94.00,96),(95,'1981-03-27',39081032.83,95.00,40),(96,'2001-10-13',9324074.94,96.00,62),(97,'2003-11-01',3334272.79,97.00,96),(98,'1995-11-30',40903540.47,98.00,22),(99,'2003-04-18',28069520.10,99.00,8),(100,'2016-04-20',20416438.92,100.00,96);
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `nome` varchar(60) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `estado` (`estado`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES ('anim id est laborum.Lorem ipsum dolor sit amet,','do',1),('exer','D',2),('fugiat nulla pariatur. Excepteur','se',3),('elit, se','ci',4),('tempor incididunt ut labore et dolore magna aliqua. Ut ','U',5),('commodo consequat. Duis aut','si',6),('in','e',7),('exercitation ullamco laboris nisi ut aliquip ex ea comm','a',8),('reprehender','co',9),('consequat. Duis aute irure dolor in reprehenderit in v','ul',10),('adipiscing elit, sed do eiusmod tempor in','ea',11),('non proident, sunt in culpa qui officia deserunt m','in',12),('veniam, quis nostrud e','n',13),('id est laborum.Lorem ipsum dolor sit amet, consectetur','i',14),('nulla pariatur. Excepteur sint o','al',15),('cupidatat non proident, sunt in culpa qui offic','ad',16),('irure dolor in r','oc',17),('Ut enim ad minim veniam, quis nostr','ut',18),('laborum.Lorem ip','la',20),('ullamco laboris nisi ','s',22),('esse ','ve',23),('proident, sunt in culpa qui officia deserunt mollit anim ','ir',24),('sunt in culpa qui officia deserunt mollit anim id es','L',25),('irure dolor in reprehenderit in voluptate velit e','am',26),('non proident, sunt in c','ma',27),('labore ','m',28),('qui officia deserunt ','cu',29),('adipis','el',30),('TEST','TE',31),('teste','sp',34),('a','aa',36),('aa','sa',42),('Bebedouro123','lk',45);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `CPF` varchar(20) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `id_cidade` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `CPF` (`CPF`),
  KEY `id_cidade` (`id_cidade`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'laborum.Lorem ipsum dolor','exerc','consequat. D','sint occaecat',1,28),(2,'anim ','elit, sed do ei','eu fugiat ','proident, sunt in c',67,25),(3,'laboris nisi ut aliqui','ve','sint occaecat cupidatat non pro','culpa qui officia ',1,36),(4,'dolore magna aliqua.','minim','in','ullamco laboris ni',123,1),(5,'ullamco la','ut labor','laboris nisi ut aliquip ex ea commodo co','labor',-48,6),(6,'magna aliqua. Ut enim ad minim veniam, quis nos','anim','nisi ut aliquip ex ea commod','in',98,29),(7,'su','eu fugiat n','m','Excepteur sint ',-37,24),(8,'magna aliqua. Ut enim ad minim veniam, qui','incididunt ut labore','sit amet, ','adipis',6,14),(9,'eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim a','do eiusmod tempor i','elit, sed do eiusmod tempor incididun','amet, cons',0,14),(10,'adipiscing elit, sed do eiusmod tempor incididunt ut labore et','velit esse cil','si','vel',-100,22),(11,'ea commodo consequat. Duis aute i','qui offi','nostrud ex','cillum dolore eu',-94,17),(12,'ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehe','sint occaecat c','ea commodo co','deseru',66,24),(13,'con','temp','tempor incididunt ut labore et dolo','ut labore ',8,11),(14,'dolor in reprehenderit in vol','tempor in','id est lab','sunt i',12,10),(15,'fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proi','cupidat','ex ea commodo consequat.','do e',-128,26),(16,'deserunt mollit anim id est laborum.Lorem i','ut aliquip ex','ut labore et dolore ma','consequ',0,10),(17,'Ut enim ad minim veniam,','ex ea c','et dolore ma','a',-107,25),(18,'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu ','exerci','anim id est laborum.Lorem ipsum do','irure dolor',-35,8),(19,'in culpa qui officia deserunt mollit anim id est laborum.Lorem','commodo consequat. ','in voluptate velit esse cillum dolo','cupidata',-66,24),(20,'Ut enim ad minim veniam, quis nos','exercitation u','anim id est laborum.Lorem ipsum','Lorem ip',99,3),(21,'eu fugiat nulla pariatur. Excepteur','tempor','veniam, quis nostrud exercitat','a',-33,17),(22,'in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla','laboris nis','ex ','minim v',-127,26),(23,'dolor sit amet, consectetur adipiscing elit, sed do eiusm','nisi u','Ut enim ad minim veniam, quis nostrud','e',8,16),(24,'quis nostrud exercitation ullamco','deserunt mollit ','magna aliqua. Ut enim ad mi','laboris nisi ut ali',47,30),(25,'exercitation ullamco laboris ','pariatur. Exc','dolor s','velit',104,9),(26,'occaecat','laborum.Lorem','adipiscing','consequat. ',-93,14),(27,'o','Duis aute irure d','Duis aute irure d','quis nostrud ',-41,25),(28,'occaecat cupidata','laboris nisi','cillum','amet, consecte',-113,23),(29,'irure dolor in reprehen','ex ea commodo conseq','r','anim id est laboru',-103,20),(30,'sint occaecat cupidatat non proident, sun','sunt in culp','Duis aute irure dol','deser',-42,12),(31,'adipiscing e','sint o','magna aliqua.','sed do eiusmod',-106,1),(32,'ipsum dolor sit ','el','consectetur adipisci','irure dolo',-32,15),(33,'magna aliqua. Ut enim ad minim veniam, quis nostrud exercitati','nul','consequat. Duis aute irure dolor in r','Excepteu',-22,8),(34,'anim ','veni','irure ','amet',-125,25),(35,'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor','nulla pariatur. ','qui officia deserunt molli','Excepteur sin',105,11),(36,'incididunt ut labore et do','sunt in culpa q','no','in voluptat',-39,22),(37,'anim id est laborum.Lorem ipsum dolor si','Duis ','fugiat nulla pari','ex ea commod',36,14),(38,'ullamco laboris nisi ut aliquip ex ea commodo conseq','Excepteur s','enim','aliquip ex ',98,1),(39,'fugiat nulla pariatur. Excepteur sint occaecat cupidatat no','esse cillum dolore ','ut labore et dolore magna aliqua','et dolore magna ',-40,13),(40,'consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ','sunt i','ad minim veniam, quis nostru','nisi ut al',-63,11),(41,'aute irure dolor in re','et dolore m','non proident, sunt i','in volu',2,29),(42,'exercitation ullamco laboris nisi ut ','labore et','quis nost','do eiusmod tempor ',97,12),(43,'ex ea commodo consequat. Duis aute irure dolor in reprehenderit i','culpa qui of','non proident, sunt in','ame',64,14),(44,'non proident, sunt in culpa qui officia deserunt mo','ad minim veniam, q','anim id est laborum.Lorem ips','cu',-81,28),(45,'adipiscing elit, sed do eiusmod te','laborum','quis nostrud exercitation ullamco labo','in reprehender',59,5),(46,'molli','m','sunt in culpa qui','irure dolor in ',103,20),(47,'qui officia deserunt mollit anim id est laborum.Lorem ipsum','do eiusmod tempor ','in voluptate velit esse cillum dolore','ad',-103,30),(48,'in volupt','e','sint occaecat cupidat','elit, s',-67,7),(49,'exercitation ullamco laboris nisi ut aliquip ex ','eu fugiat','consequat. ','minim veniam, quis n',103,13),(50,'dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ','sunt in culpa','culpa qui officia deserunt mollit an','occaecat cupid',-73,22),(51,'in voluptate velit esse cillum dolore eu fugiat nul','ea commodo ','ullamco laboris ','qui ',-113,30),(52,'cupidatat non proident, sunt in culpa qui officia deserunt mollit a','magna aliqua. Ut ','do','magna aliqua. Ut ',16,26),(53,'elit, sed do eiusmod tempor in','in voluptate ','Duis a','Excepteur sint ',109,13),(54,'ad minim veniam, quis nostrud exercitation ullamco laboris n','mol','nisi ut ali','tempor inc',26,11),(55,'cupidatat non proident, sunt in culpa qui officia deserunt m','dolor','culpa qui officia deserunt molli','ut labore et ',-108,15),(56,'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aut','velit esse c','in voluptate velit esse cillum dol','veniam, ',-84,3),(57,'quis nostrud exercita','nulla pariatu','Excepteur sint o','qui offici',-25,1),(58,'pariatur. Excepteur sint occaecat cupidata','dolore','mo','eu fugia',58,23),(59,'in reprehenderit in voluptate velit esse cillum dolore eu fugiat null','incididunt ut la','nisi ut al','Excepteur sin',-28,30),(60,'ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate vel','magna al','Ut enim ad minim veni','et dolore',-23,6),(61,'rum.Lor','Excepteur sint','Excepteur sint occaecat ','ipsum dolor sit',-40,8),(62,'exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ','in reprehender','et dolore magna aliqua. Ut enim ad min','ut lab',-87,9),(63,'dolor in reprehenderit in voluptate velit esse cill','incididunt u','adipiscing elit, s','sed do eius',53,9),(64,'consequat. Duis aute iru','velit esse cillu','mollit anim i','irure dolor in repre',82,4),(65,'nisi ut aliquip ex ea commodo con','nostrud ex','deserunt mollit a','ex ea commodo ',-64,23),(66,'quis nostrud','incididunt ut l','ut labore et dolore magna aliqua. Ut e','mo',83,4),(67,'sed do eiusmod tempor incididunt ut labore et dolore mag','ipsum dolor sit amet','elit, sed do eiusmod temp','ni',71,10),(68,'qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, cons','Excepteur sint occ','ullamco laboris nisi ut aliquip ex ea ','eiusmod ',98,10),(69,'tempor incididunt ut labore et dolore ma','ex ea commodo conse','n','qui officia ',-19,2),(70,'ali','in repr','aliquip ex ea commod','ut aliquip',-113,29),(71,'eiusmod tempor i','Duis aute ','ut aliquip ex ea commodo consequat. Duis','Duis aute irure',-45,13),(72,'do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad mi','sint occa','occaecat cupidatat non p','ullamco labori',47,23),(73,'exercitation ','quis nostr','Excepteur sint occaeca','dolore eu fug',1,8),(74,'in voluptate velit esse cillum dolore eu fugiat nulla paria','Duis aute irure ','elit, sed do eiusmod tempor incidid','ut aliquip ex ea c',-27,22),(75,'minim veniam, quis nostrud exercitation ullamco ','dolo','ut l','qui officia dese',104,12),(76,'ut labore et dolore magna a','nulla p','anim id est laboru','i',105,23),(77,'velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat ','anim id est laborum','ex ea commodo consequat. ','Excepteur',56,15),(78,'eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non ','eu fu','ipsum dolor sit amet, consectetu','tempor in',-14,9),(79,'anim id ','in ','labore et ','c',70,28),(80,'cupidatat non proident, sunt in cu','ip','dolore','in voluptate velit',-57,13),(81,'et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercit','ad minim veniam,','nostrud exer','ea',-39,30),(82,'dolor in reprehenderit','Ut ','in volupta','exercitation ullam',56,12),(83,'non proident, su','incidid','i','ullamco',-43,22),(84,'minim veniam, quis nos','Duis aut','exercitati','ut labore et',62,14),(85,'cillum dolore eu fugiat nu','Excepte','esse cillum d','ullamco laboris ',65,28),(86,'irure dolor in reprehenderit in voluptate velit esse cillum ','tempor inci','velit esse ','culpa qui',-68,8),(87,'sunt in culpa qui officia deserunt mollit a','id est l','ad m','do ',39,23),(88,'in culpa qui officia deserunt mo','cillum dolore eu fu','sint occaecat cupi','non p',-127,26),(89,'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco ','ru','ut labore et dolore','do ',-123,18),(90,'sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mo','do','ipsum dolor si','pariatur. Excep',107,17),(91,'laborum.','sunt','Ut enim ad minim veniam, quis n','dolor in repreh',-125,26),(92,'ghmghmgh','enim ad minim v','mollit an','anim id est labo',1,8),(93,'Duis aute irure dolor in reprehen','qui officia ','sint ','minim ven',49,13),(94,'laboris nisi ut aliquip ex ea commodo consequat. Duis ','velit esse ci','laboris nisi ut aliquip e','ullamco labor',94,9),(95,'occaecat cupid','laboris','dolor i','Ut enim ad minim ',119,2),(96,'Duis aute ir','et dolo','et dolore magna aliqua. Ut enim ad mini','enim ad m',18,9),(97,'non proident, sunt in culpa qui o','exercitati','sed do e','qui offi',85,8),(98,'tempor inc','fugiat n','consectetur adipiscing eli','sint occaecat',42,3),(99,'cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','labor','nulla pariatur. Excepteur si','ut aliquip',76,30),(100,'dolor in reprehenderit in voluptate velit esse cillum d','et dolore ','nulla par','borum.Lorem ips',120,17);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `funcao` varchar(30) NOT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (23,'Yasser Bazzi','alguma coijsjsjsrttncao',1,'151251254urtj'),(24,'asfasf','asfasf',1,'asfasf'),(25,'agadgadg','asfasffhzdfhzdfhzdf',1,'asfasfsdzzdffdddgfd'),(26,'asfasf','asfasf',1,'asfasf'),(27,'afaf','asasf',0,'afafs'),(28,'a','asasf',1,'afafs'),(29,'afaf','asasf',1,'afafs'),(30,'afaf','asasf',1,'afafs'),(31,'asfasf','b',1,'gffgf'),(32,'capeta','bosta',1,'123'),(33,'asfasf','b',1,'gffgf'),(34,'asd','asf',1,'asf'),(35,'asd','asf',1,'asf'),(36,'asd','asf',1,'asf'),(37,'asd','asf',1,'asf'),(38,'asd','asf',1,'asf'),(39,'asd','asf',1,'asf'),(40,'asd','asf',1,'asf'),(41,'asd','asf',1,'asf'),(42,'asd','asf',1,'asf'),(43,'asd','asf',1,'asf'),(44,'asd','asf',1,'asf');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` tinyint(1) DEFAULT NULL,
  `preco` decimal(10,3) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,-50,4489450.195,1,'ullamco '),(2,-86,8935831.882,2,'sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit ame'),(3,-63,5612287.407,3,'sint occaecat cupidatat '),(4,-27,1284252.739,4,'laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ei'),(5,-121,2933851.522,5,'proident, sunt in culpa qui officia deserunt mollit anim id est'),(6,115,2446319.794,6,'dolor '),(7,97,7143028.155,7,'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt molli'),(8,-101,928182.409,8,'nulla pariatur. Excepteur sint occaecat cupid'),(9,58,8218227.152,9,'veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip e'),(10,0,5997469.176,10,'ex ea commodo conse'),(11,-57,8246643.704,11,'sunt in culpa qui officia deserunt mollit anim id est laborum.'),(12,65,1223540.958,12,'aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris '),(13,-83,6025631.211,13,'laboris nisi ut aliquip ex ea commodo '),(14,-48,1034681.560,14,'ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehende'),(15,-75,7203389.588,15,'consectetur a'),(16,-10,3593063.111,16,'mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmo'),(17,116,5415379.701,17,'elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, qu'),(18,76,9705458.492,18,'nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in'),(19,-105,6969529.262,19,'qui officia deserunt mollit anim id est '),(20,72,1461800.746,20,'anim id e'),(21,117,4646026.172,21,'nostr'),(22,55,2339724.646,22,'quis nostrud exercitation ullamco la'),(23,-85,6886855.417,23,'incididunt ut labore et dolore magna al'),(24,-34,6263867.000,24,'in voluptate velit esse cillum dolore '),(25,-79,6720800.804,25,'ut'),(26,125,6467650.598,26,'commodo consequat. Duis aute i'),(27,49,7647595.130,27,'ipsum dolor sit amet, consectetur adipisci'),(28,99,9322847.650,28,'fugia'),(29,49,3582282.737,29,'eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut en'),(30,7,1394661.591,30,'deserun'),(31,-92,1280078.449,31,'consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillu'),(32,49,6248083.140,32,'commodo consequat. Du'),(33,74,6828493.268,33,'ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in re'),(34,-9,8375903.743,34,'nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt i'),(35,49,9443629.410,35,'aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur'),(36,-90,7784561.533,36,'adip'),(37,18,3175950.329,37,'in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit am'),(38,48,9192606.367,38,'anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do ei'),(39,42,3737815.240,39,'lab'),(40,-37,4935766.593,40,'ullamco laboris nisi ut aliquip ex ea commodo conse'),(41,76,9744708.669,41,'laborum.Lorem ipsum dolor sit amet, consectetur adipisci'),(42,41,6338851.466,42,'ve'),(43,-72,5425963.377,43,'do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,'),(44,34,289540.198,44,'deserunt mollit anim id est laborum.Lorem ipsum dolor'),(45,16,6892351.740,45,'sunt in culpa qui offi'),(46,88,581187.161,46,'non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor'),(47,67,3799349.737,47,'officia deserunt mollit a'),(48,79,302740.515,48,'ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exer'),(49,45,803982.207,49,'laboris nisi'),(50,-46,6950926.842,50,'ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor'),(51,64,189002.778,51,'ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea com'),(52,3,671626.724,52,'Ut enim ad minim veniam, quis nostrud exercitation ullamco'),(53,-94,9597379.635,53,'eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa q'),(54,-61,7459408.314,54,'Excepteur sint occaecat cupidatat non proident, sunt in c'),(55,20,1657515.994,55,'quis nostrud exercitation ullamco la'),(56,3,4505114.410,56,'nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo co'),(57,-49,2289692.985,57,'incididunt ut labore et dolore magna aliqua. Ut enim ad minim venia'),(58,-48,8273090.797,58,'ex e'),(59,63,5933535.938,59,'magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut'),(60,30,8844949.133,60,'esse cillum'),(61,98,775944.640,61,'consect'),(62,87,4625085.990,62,'ullamco laboris nisi ut aliquip ex ea commodo cons'),(63,-73,8915109.170,63,'ull'),(64,-55,2001774.545,64,'consequat. Duis aute irure dolor in reprehenderit '),(65,38,8890509.450,65,'incididunt ut labore et dolore magna'),(66,39,8343336.806,66,'nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. '),(67,-96,2762148.584,67,'nisi ut aliquip ex ea commodo consequat. Duis '),(68,76,2404124.840,68,'adipiscing elit, sed do eiusmod tempor in'),(69,41,6779893.364,69,'pariatur. Excepteur'),(70,-5,9107515.637,70,'nisi ut aliquip ex ea commodo consequat. Duis aute irure'),(71,40,6388888.615,71,'in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur s'),(72,121,9237311.369,72,'occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.L'),(73,-70,2046406.717,73,'laboris nisi ut aliquip ex ea commodo consequat. Duis aut'),(74,-77,1065089.839,74,'incididunt ut labore et do'),(75,59,9189182.811,75,'ut labore et do'),(76,-109,8473678.477,76,'in culpa qui officia deserunt mollit anim id est laborum.Lo'),(77,36,4484242.350,77,'magna aliqua. Ut enim ad minim veniam, quis nostrud'),(78,74,4493859.615,78,'id est laborum.Lorem ipsum dolor sit amet, c'),(79,-46,1684238.903,79,'ut labore et dolore magna aliqua. Ut eni'),(80,-97,2200956.175,80,'non proident, sunt in culpa qui officia deserun'),(81,119,8497635.610,81,'laboris nisi ut aliquip ex e'),(82,-100,7139223.817,82,'deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectet'),(83,-49,5046331.795,83,'velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proide'),(84,-83,9021720.600,84,'mollit anim id est laborum.Lorem ipsum'),(85,52,5459102.191,85,'irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla par'),(86,-100,9619056.590,86,'eli'),(87,-82,2163990.290,87,'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ull'),(88,-47,3147866.776,88,'adipiscing elit, sed do eiu'),(89,-105,8047341.731,89,'magna aliqua'),(90,56,2910469.750,90,'irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. E'),(91,62,1084115.545,91,'sed do eiusmod tempor incididunt ut labore et dolore magna a'),(92,-105,6417343.534,92,'dolor sit amet, consectetur adipiscing e'),(93,-70,4930648.881,93,'non proident, sunt in culpa qui officia deserunt mollit anim id e'),(94,95,6037897.802,94,'eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proid'),(95,67,2772734.381,95,'ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ull'),(96,-50,2427261.576,96,'dolor in reprehenderit in voluptat'),(97,-41,8683286.920,97,'in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum'),(98,-99,7391602.403,98,'sit amet, consectetur adipiscing elit, sed '),(99,64,7626090.619,99,'incididunt ut labore et dolore magna aliqua. Ut e'),(100,-56,4263193.750,100,'fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia'),(101,1,123.200,123,'testeprodutoaaaa'),(102,1,123.200,123,'testeproduto');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vend_prod`
--

DROP TABLE IF EXISTS `vend_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vend_prod` (
  `preco_unitario` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `cod_venda` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  KEY `cod_venda` (`cod_venda`),
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `vend_prod_ibfk_1` FOREIGN KEY (`cod_venda`) REFERENCES `vendas` (`codigo`),
  CONSTRAINT `vend_prod_ibfk_2` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vend_prod`
--

LOCK TABLES `vend_prod` WRITE;
/*!40000 ALTER TABLE `vend_prod` DISABLE KEYS */;
INSERT INTO `vend_prod` VALUES (76168360.90,1,98,21),(9505033.38,2,1,54),(96696488.20,3,54,84),(68898808.70,4,48,49),(31456542.34,5,80,40),(6608909.35,6,32,69),(74150000.60,7,49,88),(38779436.35,8,23,48),(44279380.15,9,5,52),(92690584.44,10,38,92),(30158484.11,11,79,80),(92239744.48,12,6,23),(20200092.98,13,83,55),(61976164.76,14,68,38),(13488942.21,15,8,66),(36060752.65,16,95,78),(40559464.50,17,38,61),(22057950.59,18,38,3),(45878864.18,19,47,42),(18993760.79,20,15,81),(73948144.19,21,2,3),(36450152.27,22,100,55),(51898152.67,23,86,72),(82490248.50,24,41,90),(90260920.66,25,1,78),(47861464.72,26,63,49),(99084864.62,27,37,70),(91210232.43,28,36,88),(84147376.63,29,81,6),(43027348.49,30,8,2),(94671296.12,31,74,77),(76856968.30,32,23,64),(13208795.31,33,1,25),(16136265.40,34,31,16),(35314704.73,35,20,53),(39139812.19,36,82,87),(82951096.42,37,42,28),(37200660.76,38,34,18),(34469288.54,39,80,8),(61046272.24,40,62,68),(65288896.45,41,66,77),(34164740.77,42,68,10),(58384192.27,43,35,13),(12107581.76,44,14,42),(20305944.57,45,98,36),(53107600.33,46,62,34),(86184440.44,47,73,15),(32971764.45,48,49,61),(2012151.80,49,100,28),(69591616.90,50,61,46),(72089816.45,51,32,13),(17351378.80,52,68,5),(67997888.46,53,46,57),(86022448.45,54,54,2),(28548974.80,55,36,80),(33292592.44,56,43,95),(80171072.14,57,62,69),(64828344.94,58,100,44),(67914072.20,59,38,31),(54715772.34,60,24,36),(79173008.26,61,77,64),(15333003.84,62,58,25),(37253564.53,63,1,11),(6306821.22,64,32,13),(38603844.14,65,28,9),(73387400.96,66,73,12),(55998640.67,67,12,80),(60092448.56,68,39,29),(56848824.84,69,54,14),(87142632.60,70,48,21),(19341486.20,71,11,45),(60411392.80,72,97,91),(24081708.67,73,62,27),(49787188.27,74,69,31),(61650936.23,75,44,56),(95497472.22,76,34,40),(9931648.65,77,32,20),(638186.48,78,57,73),(83644088.70,79,55,93),(78519424.96,80,18,5),(53335304.79,81,92,74),(49668120.68,82,11,52),(34649820.57,83,99,37),(48676200.36,84,48,66),(255775.30,85,34,1),(67364328.20,86,6,26),(7023042.33,87,68,78),(53825384.57,88,26,25),(27235490.00,89,66,99),(21760446.17,90,72,64),(43615932.55,91,50,14),(30126380.10,92,88,10),(9159899.20,93,45,15),(37545084.50,94,48,21),(81185240.37,95,18,86),(51308752.71,96,75,41),(38144380.21,97,57,9),(98344584.24,98,44,78),(52712400.95,99,56,46),(80430104.98,100,45,34);
/*!40000 ALTER TABLE `vend_prod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cliente` int(11) NOT NULL,
  `cod_caixa` int(11) NOT NULL,
  `data_venda` date NOT NULL,
  `forma_de_pagamento` varchar(40) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_caixa` (`cod_caixa`),
  CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`cod_caixa`) REFERENCES `caixa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (1,24,80,'2009-08-21','sint occaecat cupidatat non pr'),(2,54,82,'1985-11-22','cupidatat non proi'),(3,71,16,'1992-03-01','id est laborum.L'),(4,90,8,'1981-08-19','dolor sit amet, consectetur a'),(5,49,15,'1998-03-08','dolore eu fugiat nulla pariatur. Excepte'),(6,25,91,'2018-03-19','magna ali'),(7,11,95,'1981-04-21','in reprehenderit in volu'),(8,12,75,'2017-07-04','tempor incididunt ut labor'),(9,93,3,'1996-05-14','pari'),(10,96,27,'1984-02-08','Ut enim ad minim veniam, '),(11,90,39,'2007-07-17','deserunt mollit anim'),(12,17,26,'1983-06-17','in voluptate velit esse cillum dol'),(13,82,44,'1980-09-10','deserunt mollit anim'),(14,68,63,'1998-02-16','Ut enim ad minim veniam, quis nostrud ex'),(15,93,91,'1984-07-07','elit, sed do eiusmod tempor '),(16,48,43,'1990-01-04','nulla pariatur. E'),(17,75,96,'2008-10-14','Excepteur sint occaecat cupi'),(18,89,72,'2017-08-12','in v'),(19,57,96,'1989-09-10','enim ad minim veniam, qu'),(20,73,70,'2014-11-19','dolor in repre'),(21,62,100,'1991-05-17','ex ea com'),(22,37,53,'2006-06-13','sint occaecat cupidatat non'),(23,81,55,'2019-10-06','ut aliquip ex ea commodo co'),(24,25,62,'2003-02-16','exercitation ullamco laboris nisi '),(25,42,32,'2004-05-21','occaecat cupidatat non proident, su'),(26,45,54,'2004-09-26','ve'),(27,65,17,'1988-10-13','nulla pariatur. E'),(28,23,61,'2010-04-01','irure dolor in reprehenderit in '),(29,77,20,'1987-12-20','cupidatat non proid'),(30,61,100,'1991-05-08','cup'),(31,65,84,'2018-01-03','de'),(32,43,80,'1989-12-20','in voluptate velit esse cillum dolore '),(33,94,21,'2005-01-27','quis nostrud exercitation ull'),(34,76,65,'2015-09-29','in reprehenderit in voluptate velit '),(35,3,89,'2011-07-15','ex ea commodo consequat. Duis aute '),(36,27,88,'2018-08-22','nostrud exe'),(37,70,85,'2014-11-05','mollit '),(38,46,28,'2017-03-24','sed do'),(39,42,27,'2005-01-21','sed do eiusmod tempo'),(40,80,29,'1988-01-11','sit am'),(41,24,43,'2019-01-11','adipiscing elit, sed do'),(42,93,90,'1990-12-23','aute irure dolor in reprehen'),(43,23,31,'1989-07-20','sed do eiusm'),(44,39,92,'1984-04-20','et do'),(45,51,95,'2004-06-03','velit esse cillum dolore eu fugia'),(46,87,44,'1998-12-30','laboris nisi ut aliquip ex ea c'),(47,58,79,'2000-02-23','incididunt ut labore et dolore '),(48,53,20,'2018-07-23','nisi ut aliquip ex ea commodo conse'),(49,8,86,'1981-12-23','rum.Lorem ipsum dolor sit amet'),(50,2,93,'1992-08-17','ut labore et dolore magn'),(51,15,20,'1989-10-07','exercitation ullamco laboris nis'),(52,46,10,'2013-07-02','laborum.Lorem ipsum dol'),(53,14,5,'2006-09-03','ex ea '),(54,19,4,'1988-08-15','culpa qui officia deserunt mollit anim'),(55,100,5,'2009-07-06','rum.Lorem ipsum dolor sit amet'),(56,2,7,'1994-04-24','qui officia '),(57,59,57,'2000-08-08','Duis aute irure dolor in reprehenderit'),(58,54,48,'1986-04-28','vel'),(59,28,17,'1989-12-27','in voluptat'),(60,58,32,'1986-01-13','id est laborum.Lorem ipsum dolor si'),(61,56,28,'2019-07-30','sint occae'),(62,94,80,'2012-08-30','adipiscing'),(63,65,77,'1983-04-28','officia deserun'),(64,76,16,'2001-03-15','an'),(65,28,7,'2010-02-04','ipsum dolor sit amet'),(66,5,9,'1997-03-29','in culpa qui offici'),(67,29,48,'1987-10-27','tempor incididunt ut labore et do'),(68,41,5,'1998-12-11','dolor in reprehenderit in volupt'),(69,82,43,'1989-01-28','eu'),(70,78,31,'1987-03-14','dolor '),(71,14,31,'1984-02-06','quis nostrud exercitat'),(72,21,12,'2012-10-30','anim id est laborum.Lorem ipsum dolor'),(73,53,5,'2019-08-13','aliquip ex ea commodo consequat. Du'),(74,26,99,'2013-07-12','sit amet, consectetur adipi'),(75,53,69,'1998-01-16','sint occaecat cupidatat'),(76,87,65,'1980-01-18','dolore eu fugiat nulla'),(77,1,94,'1998-03-09','exercitation ullamco laboris nisi '),(78,43,72,'2014-03-08','ut labore et dolore magna aliqua'),(79,30,20,'1992-10-13','dolore eu fugia'),(80,8,64,'2019-01-01','velit esse cillum dolore'),(81,68,72,'2012-01-29','proident, sunt in'),(82,76,62,'2015-12-08','sit amet, consecte'),(83,23,75,'1998-01-04','incididunt'),(84,78,97,'1988-09-10','ut labore et dol'),(85,47,41,'1996-07-04','dolore eu fugiat nulla pariatur. Except'),(86,99,52,'1983-08-19','adipiscing elit, '),(87,34,93,'2003-09-21','nisi ut aliquip ex ea commodo consequat'),(88,40,71,'1988-10-30','est laborum.Lorem ipsum dolor si'),(89,96,98,'2014-04-08','sint occae'),(90,96,62,'1997-09-03','d'),(91,69,78,'2015-05-04','deserunt mollit'),(92,7,7,'2004-05-30','occaecat cupidatat n'),(93,21,43,'1984-09-22','dolore magna '),(94,58,82,'1990-01-01','dolore magn'),(95,49,60,'2013-07-12','veniam, quis nostrud exercitation u'),(96,1,21,'1989-04-08','Excepteur sint occaecat cupidata'),(97,83,82,'2003-11-19','Duis aute irure dol'),(98,44,52,'2015-04-02','magna al'),(99,23,80,'1985-08-12','ut labor'),(100,35,31,'2006-09-04','velit esse cillum dolore eu ');
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cantagalo'
--

--
-- Dumping routines for database 'cantagalo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-24 11:07:30
