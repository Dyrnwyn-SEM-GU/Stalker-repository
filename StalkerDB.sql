CREATE DATABASE  IF NOT EXISTS `StalkerDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `StalkerDB`;

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
  `Brand` varchar(32) DEFAULT NULL,
  `RegistryNumber` varchar(10) NOT NULL,
  `Type` varchar(25) DEFAULT NULL,
  `Consumption` decimal(4,2) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  PRIMARY KEY (`RegistryNumber`,`Username`),
  KEY `fk_Car_User1_idx` (`Username`),
  CONSTRAINT `fk_Car_User1` FOREIGN KEY (`Username`) REFERENCES `User` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Car`
--

LOCK TABLES `Car` WRITE;
/*!40000 ALTER TABLE `Car` DISABLE KEYS */;
INSERT INTO `Car` VALUES ('Volvo','ABC123','Petrol',0.70,'dyrnwyn@dyrnwyn.com'),('VW','CBA321','Petrol',0.70,'jani.m.pasanen@gmail.com'), ('VW','CBI321','Petrol',0.70,'morgan.ericsson@gu.se');
/*!40000 ALTER TABLE `Car` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `CarTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarTypes` (
  `CarTypes` varchar(25) NOT NULL,
  PRIMARY KEY (`CarTypes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarTypes`
--

LOCK TABLES `CarTypes` WRITE;
/*!40000 ALTER TABLE `CarTypes` DISABLE KEYS */;
INSERT INTO `CarTypes` VALUES ('Petrol'),('Diesel'),('Electric'),('Ethanol'),('Gas'),('Hybrid - Petrol/Electric'),('Hybrid - Diesel/Electric'),('Other');
/*!40000 ALTER TABLE `CarTypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Table structure for table `ExtraCostTypes`
--

DROP TABLE IF EXISTS `ExtraCostTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExtraCostTypes` (
  `ExtraCostTypes` varchar(45) NOT NULL,
  PRIMARY KEY (`ExtraCostTypes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExtraCostTypes`
--

LOCK TABLES `ExtraCostTypes` WRITE;
/*!40000 ALTER TABLE `ExtraCostTypes` DISABLE KEYS */;
INSERT INTO `ExtraCostTypes` VALUES ('Ferry'),('Food'),('Parking'),('Various road fees');
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
  `Cost` decimal(7,2) NOT NULL,
  `File` mediumblob,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Date` date DEFAULT NULL,
  `idTripData` int(11) NOT NULL,
  PRIMARY KEY (`idExtraCosts`,`idTripData`),
  KEY `fk_ExtraCosts_TripData1_idx` (`idTripData`),
  CONSTRAINT `fk_ExtraCosts_TripData1` FOREIGN KEY (`idTripData`) REFERENCES `TripData` (`idTripData`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExtraCosts`
--

LOCK TABLES `ExtraCosts` WRITE;
/*!40000 ALTER TABLE `ExtraCosts` DISABLE KEYS */;
INSERT INTO `ExtraCosts` VALUES (1,'Ferry',120.00,NULL,'2013-12-27 17:41:36','2013-12-27',1),(2,'Food',240.00,NULL,'2013-12-27 17:41:36','2013-12-27',1),(3,'Various road fees',80.00,NULL,'2013-12-27 17:41:36','2013-12-27',1),(4,'Food',65.00,NULL,'2013-12-27 17:40:46','2013-12-27',1);
/*!40000 ALTER TABLE `ExtraCosts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Locations`
--

DROP TABLE IF EXISTS `Locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Locations` (
  `City` varchar(30) NOT NULL,
  `Street` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`City`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Locations`
--

LOCK TABLES `Locations` WRITE;
/*!40000 ALTER TABLE `Locations` DISABLE KEYS */;
INSERT INTO `Locations` VALUES ('Amsterdam',NULL),('Arboga',''),('Bergen',''),('Berlin',NULL),('Brussels',NULL),('Copenhagen',''),('Drammen',''),('Eskilstuna',''),('Frankfurt',NULL),('Gothenburg',''),('Hallstahammar',''),('Helsingborg',''),('Helsinki',NULL),('Kalmar',''),('London',NULL),('Malmö',''),('Oslo',''),('Oulu',NULL),('Paris',NULL),('Rotterdam',NULL),('Stockholm',''),('Turku',NULL),('Vaasa',NULL),('Wien',NULL);
/*!40000 ALTER TABLE `Locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReasonForTravel`
--

DROP TABLE IF EXISTS `ReasonForTravel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReasonForTravel` (
  `TypeOfReason` varchar(45) NOT NULL,
  PRIMARY KEY (`TypeOfReason`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReasonForTravel`
--

LOCK TABLES `ReasonForTravel` WRITE;
/*!40000 ALTER TABLE `ReasonForTravel` DISABLE KEYS */;
INSERT INTO `ReasonForTravel` VALUES ('Conference'),('Exhibition'),('Salesmeeting');
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
  `RegistryNumber` varchar(10) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Date` date NOT NULL,
  PRIMARY KEY (`idTripData`,`Username`,`RegistryNumber`),
  KEY `fk_TripData_User_idx` (`Username`),
  KEY `fk_TripData_Car1_idx` (`RegistryNumber`),
  CONSTRAINT `fk_TripData_User` FOREIGN KEY (`Username`) REFERENCES `User` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TripData_Car1` FOREIGN KEY (`RegistryNumber`) REFERENCES `Car` (`RegistryNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripData`
--

LOCK TABLES `TripData` WRITE;
/*!40000 ALTER TABLE `TripData` DISABLE KEYS */;
INSERT INTO `TripData` VALUES (1,100110,100560,'Gothenburg','Stockholm','Conference','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-11-23 17:25:31','2013-11-22'),(2,100589,100985,'Stockholm','Oslo','Exhibition','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-11-25 17:25:31','2013-11-24'),(3,101000,101101,'Oslo','Oslo','Conference','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-11-27 17:25:31','2013-11-26'),(4,101101,101455,'Oslo','Gothenburg','Meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-11-27 17:25:31','2013-12-24'),(5,101455,101455,'Gothenburg','Gothenburg','Meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-11-27 17:25:31','2013-11-24'),(6,101455,101455,'Gothenburg','Gothenburg','Meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(7,101455,101455,'Gothenburg','Gothenburg','Meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(8,101455,101455,'Gothenburg','Gothenburg','Conference','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(9,101455,101455,'Gothenburg','Gothenburg','Conference','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(10,101455,101455,'Gothenburg','Gothenburg','Conference','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(11,101455,101455,'Gothenburg','Gothenburg','Meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:25:31','2013-11-24'),(12,101455,102345,'Gothenburg','Paris','Sales meeting','dyrnwyn@dyrnwyn.com','Dyrnwyn','ABC123','2013-12-27 17:43:00','2013-12-18'),
(13,100000,101000,'Gothenburg','Stockholm','Meeting','jani.m.pasanen@gmail.com', 'Jani Pasanen', 'CBA321', '2013-12-27 17:43:00','2013-12-18'),
(14,100000,101000,'Gothenburg','Stockholm','Meeting','morgan.ericsson@gu.se', 'Morgan Ericsson', 'CBI321', '2013-12-27 17:43:00','2013-12-18');
/*!40000 ALTER TABLE `TripData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Username` varchar(45) NOT NULL,
  `FirstName` varchar(20) DEFAULT NULL,
  `LastName` varchar(25) DEFAULT NULL,
  `Password` varchar(15) DEFAULT NULL,
  `Name` varchar(47) DEFAULT NULL,
  `Picture` mediumblob,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('dyrnwyn@dyrnwyn.com',NULL,NULL,'dyrnwyn','Dyrnwyn',NULL),('jani.m.pasanen@gmail.com','','','jani','Jani Pasanen',NULL),('morgan.ericsson@gu.se','','','morgan123','Morgan Ericsson',NULL);
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
