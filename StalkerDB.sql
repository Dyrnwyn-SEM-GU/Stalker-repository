SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `StalkerDB` ;
CREATE SCHEMA IF NOT EXISTS `StalkerDB` DEFAULT CHARACTER SET utf8 ;
USE `StalkerDB` ;

-- -----------------------------------------------------
-- Table `StalkerDB`.`Car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`Car` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`Car` (
  `Brand` VARCHAR(32) NULL DEFAULT NULL,
  `RegistryNumber` VARCHAR(10) NOT NULL,
  `Type` VARCHAR(10) NULL DEFAULT NULL,
  `Consumption` DECIMAL(2,2) NULL DEFAULT NULL,
  `Username` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`RegistryNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `StalkerDB`.`ExtraCostTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`ExtraCostTypes` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`ExtraCostTypes` (
  `ExtraCostTypes` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ExtraCostTypes`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `StalkerDB`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`User` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`User` (
  `Username` VARCHAR(30) NOT NULL,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(25) NOT NULL,
  `Password` VARCHAR(15) NULL DEFAULT NULL,
  `Name` VARCHAR(47) NULL DEFAULT NULL,
  `Picture` MEDIUMBLOB NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `StalkerDB`.`TripData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`TripData` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`TripData` (
  `idTripData` INT(11) NOT NULL AUTO_INCREMENT,
  `StartingKm` INT(11) NOT NULL,
  `EndingKm` INT(11) NOT NULL,
  `From` VARCHAR(20) NOT NULL,
  `To` VARCHAR(20) NOT NULL,
  `ReasonOfTrip` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Name` VARCHAR(47) NULL DEFAULT NULL,
  `RegistryNumber` VARCHAR(10) NOT NULL,
  `Timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Date` DATE NOT NULL,
  PRIMARY KEY (`idTripData`, `Username`, `RegistryNumber`),
  CONSTRAINT `fk_TripData_User`
    FOREIGN KEY (`Username`)
    REFERENCES `StalkerDB`.`User` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TripData_Car1`
    FOREIGN KEY (`RegistryNumber`)
    REFERENCES `StalkerDB`.`Car` (`RegistryNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_TripData_User_idx` ON `StalkerDB`.`TripData` (`Username` ASC);

CREATE INDEX `fk_TripData_Car1_idx` ON `StalkerDB`.`TripData` (`RegistryNumber` ASC);


-- -----------------------------------------------------
-- Table `StalkerDB`.`ExtraCosts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`ExtraCosts` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`ExtraCosts` (
  `idExtraCosts` INT(11) NOT NULL AUTO_INCREMENT,
  `TypeOfCost` VARCHAR(45) NOT NULL,
  `Cost` DECIMAL(5,2) NOT NULL,
  `File` MEDIUMBLOB NULL DEFAULT NULL,
  `Timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Date` DATE NULL DEFAULT NULL,
  `idTripData` INT(11) NOT NULL,
  PRIMARY KEY (`idExtraCosts`, `idTripData`),
  CONSTRAINT `fk_ExtraCosts_TripData1`
    FOREIGN KEY (`idTripData`)
    REFERENCES `StalkerDB`.`TripData` (`idTripData`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_ExtraCosts_TripData1_idx` ON `StalkerDB`.`ExtraCosts` (`idTripData` ASC);


-- -----------------------------------------------------
-- Table `StalkerDB`.`Locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`Locations` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`Locations` (
  `City` VARCHAR(30) NOT NULL,
  `Street` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`City`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `StalkerDB`.`ReasonForTravel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `StalkerDB`.`ReasonForTravel` ;

CREATE TABLE IF NOT EXISTS `StalkerDB`.`ReasonForTravel` (
  `TypeOfReason` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TypeOfReason`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

GRANT SELECT on StalkerDB.* TO 'Dyrnwyn'@'%' IDENTIFIED BY 'Dyrnwyn!';
GRANT INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW on StalkerDB.* TO 'Dyrnwyn'@'%';
GRANT LOCK TABLES on StalkerDB.* TO 'Dyrnwyn'@'%';

GRANT SELECT on StalkerDB.* TO 'Dyrnwyn'@'localhost' IDENTIFIED BY 'Dyrnwyn!';
GRANT INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW on StalkerDB.* TO 'Dyrnwyn'@'localhost';
GRANT LOCK TABLES on StalkerDB.* TO 'Dyrnwyn'@'localhost';
