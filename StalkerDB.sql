CREATE DATABASE  IF NOT EXISTS `StalkerDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `StalkerDB`;
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: StalkerDB
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

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
-- Table structure for table `Car`
--

DROP TABLE IF EXISTS `Car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Car` (
  `idCar` int(11) NOT NULL AUTO_INCREMENT,
  `Brand` varchar(10) DEFAULT NULL,
  `RegistryNumber` varchar(10) NOT NULL,
  `Type` varchar(10) DEFAULT NULL,
  `Consumption` double DEFAULT NULL,
  `Username` varchar(47) DEFAULT NULL,
  PRIMARY KEY (`idCar`,`RegistryNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Car`
--

LOCK TABLES `Car` WRITE;
/*!40000 ALTER TABLE `Car` DISABLE KEYS */;
INSERT INTO `Car` VALUES (1,'Volvo','KRE024','95',0.7,'janip'),(2,'VW','UQE355','95',0.7,'janip');
/*!40000 ALTER TABLE `Car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExtraCostTypes`
--

DROP TABLE IF EXISTS `ExtraCostTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExtraCostTypes` (
  `idExtraCostTypes` int(11) NOT NULL AUTO_INCREMENT,
  `ExtraCostTypes` varchar(45) NOT NULL,
  PRIMARY KEY (`idExtraCostTypes`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExtraCostTypes`
--

LOCK TABLES `ExtraCostTypes` WRITE;
/*!40000 ALTER TABLE `ExtraCostTypes` DISABLE KEYS */;
INSERT INTO `ExtraCostTypes` VALUES (1,'Food'),(2,'Parking');
/*!40000 ALTER TABLE `ExtraCostTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExtraCosts`
--

DROP TABLE IF EXISTS `ExtraCosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExtraCosts` (
  `idExtraCosts` int(11) NOT NULL AUTO_INCREMENT,
  `TypeOfCost` varchar(45) NOT NULL,
  `Cost` double NOT NULL,
  `File` mediumblob,
  `Timestamp` timestamp NULL DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`idExtraCosts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExtraCosts`
--

LOCK TABLES `ExtraCosts` WRITE;
/*!40000 ALTER TABLE `ExtraCosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `ExtraCosts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Locations`
--

DROP TABLE IF EXISTS `Locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Locations` (
  `idLocations` int(11) NOT NULL AUTO_INCREMENT,
  `City` varchar(20) NOT NULL,
  `Street` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idLocations`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Locations`
--

LOCK TABLES `Locations` WRITE;
/*!40000 ALTER TABLE `Locations` DISABLE KEYS */;
INSERT INTO `Locations` VALUES (1,'Göteborg',''),(2,'Stockholm',''),(3,'Malmö',''),(4,'Örnsköldsvik',''),(5,'Paris',''),(6,'Brussels',''),(7,'London',''),(8,'Hallstahammar',''),(9,'Helsingborg',NULL);
/*!40000 ALTER TABLE `Locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReasonForTravel`
--

DROP TABLE IF EXISTS `ReasonForTravel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReasonForTravel` (
  `idReason` int(11) NOT NULL AUTO_INCREMENT,
  `TypeOfReason` varchar(45) NOT NULL,
  PRIMARY KEY (`idReason`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReasonForTravel`
--

LOCK TABLES `ReasonForTravel` WRITE;
/*!40000 ALTER TABLE `ReasonForTravel` DISABLE KEYS */;
INSERT INTO `ReasonForTravel` VALUES (1,'Customer visit'),(2,'Excebition'),(3,'Project meeting'),(4,'Sales meeting'),(5,'Conference'),(6,'Board meeting'),(7,'');
/*!40000 ALTER TABLE `ReasonForTravel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TripData`
--

DROP TABLE IF EXISTS `TripData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TripData` (
  `idTripData` int(11) NOT NULL AUTO_INCREMENT,
  `StartingKm` int(11) NOT NULL,
  `EndingKm` int(11) NOT NULL,
  `From` varchar(20) NOT NULL,
  `To` varchar(20) NOT NULL,
  `ReasonOfTrip` varchar(45) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Name` varchar(47) DEFAULT NULL,
  `RegistryNumber` varchar(10) DEFAULT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Date` date NOT NULL,
  PRIMARY KEY (`idTripData`,`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripData`
--

LOCK TABLES `TripData` WRITE;
/*!40000 ALTER TABLE `TripData` DISABLE KEYS */;
INSERT INTO `TripData` VALUES (1,100000,101000,'Göteborg','Stockholm','Conference','janip','Jani Pasanen','KRT022','2013-10-31 14:34:00','2012-03-12');
/*!40000 ALTER TABLE `TripData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(8) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Password` varchar(15) DEFAULT NULL,
  `Name` varchar(47) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'janip','Jani','Pasanen','1234pasanen','Jani Pasanen');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

GRANT SELECT on StalkerDB.* TO 'Dyrnwyn'@'%' IDENTIFIED BY 'Dyrnwyn!';
GRANT INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW on StalkerDB.* TO 'Dyrnwyn'@'%';
GRANT LOCK TABLES on StalkerDB.* TO 'Dyrnwyn'@'%';

GRANT SELECT on StalkerDB.* TO 'Dyrnwyn'@'localhost' IDENTIFIED BY 'Dyrnwyn!';
GRANT INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW on StalkerDB.* TO 'Dyrnwyn'@'localhost';
GRANT LOCK TABLES on StalkerDB.* TO 'Dyrnwyn'@'localhost';
-- Dump completed on 2013-10-31 14:10:11


