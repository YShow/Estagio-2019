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
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `preco_total` decimal(10,2) NOT NULL,
  `saida` decimal(10,2) DEFAULT NULL,
  `codigo_funcionario` mediumint(8) unsigned NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_funcionario` (`codigo_funcionario`),
  CONSTRAINT `caixa_ibfk_1` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `nome` varchar(60) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`codigo`),
  FULLTEXT KEY `fts_cidade` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `CPF` varchar(20) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `id_cidade` mediumint(8) unsigned NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `id_cidade` (`id_cidade`),
  FULLTEXT KEY `fts_nome` (`nome`),
  FULLTEXT KEY `fts_cliente` (`nome`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3227123 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `funcao` varchar(30) NOT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  `senhahash` varchar(1024) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `usuario` (`usuario`),
  FULLTEXT KEY `fts_funcionario` (`nome`,`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `ativo` tinyint(1) DEFAULT NULL,
  `preco` decimal(10,2) NOT NULL,
  `quantidade` mediumint(8) unsigned NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`),
  FULLTEXT KEY `fts_produto` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=1204 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vend_prod`
--

DROP TABLE IF EXISTS `vend_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vend_prod` (
  `preco_unitario` decimal(10,2) NOT NULL,
  `quantidade` mediumint(8) unsigned NOT NULL,
  `cod_venda` mediumint(8) unsigned NOT NULL,
  `cod_produto` mediumint(8) unsigned NOT NULL,
  KEY `cod_venda` (`cod_venda`),
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `vend_prod_ibfk_1` FOREIGN KEY (`cod_venda`) REFERENCES `vendas` (`codigo`),
  CONSTRAINT `vend_prod_ibfk_2` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `codigo` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `cod_cliente` mediumint(8) unsigned NOT NULL,
  `cod_caixa` mediumint(8) unsigned NOT NULL,
  `data_venda` date NOT NULL,
  `forma_de_pagamento` varchar(40) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_caixa` (`cod_caixa`),
  CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`cod_caixa`) REFERENCES `caixa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'cantagalo'
--
/*!50003 DROP PROCEDURE IF EXISTS `pegacliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE  PROCEDURE `pegacliente`(in a varchar(50))
BEGIN
	SELECT * from cantagalo.cliente where cliente.nome like concat('%',a,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pegaclientee` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE  PROCEDURE `pegaclientee`(in a varchar(50))
BEGIN
	SELECT * from cantagalo.cliente where cliente.nome like concat('%',a,'%')
GROUP by codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-20 16:53:24
