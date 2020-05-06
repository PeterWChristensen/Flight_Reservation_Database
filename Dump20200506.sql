-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: mysql4.cs.stonybrook.edu    Database: pwchristense
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `AdvPurchaseDiscount`
--

DROP TABLE IF EXISTS `AdvPurchaseDiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AdvPurchaseDiscount` (
  `AirlineID` char(2) NOT NULL,
  `Days` int(11) NOT NULL,
  `DiscountRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AirlineID`,`Days`),
  CONSTRAINT `AirlineID` FOREIGN KEY (`AirlineID`) REFERENCES `Airline` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AdvPurchaseDiscount`
--

LOCK TABLES `AdvPurchaseDiscount` WRITE;
/*!40000 ALTER TABLE `AdvPurchaseDiscount` DISABLE KEYS */;
/*!40000 ALTER TABLE `AdvPurchaseDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Airline`
--

DROP TABLE IF EXISTS `Airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Airline` (
  `Id` char(2) NOT NULL,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Airline`
--

LOCK TABLES `Airline` WRITE;
/*!40000 ALTER TABLE `Airline` DISABLE KEYS */;
INSERT INTO `Airline` VALUES ('AA','American Airlines'),('AB','Air Berlin'),('AJ','Air Japan'),('AM','Air Madagascar'),('BA','British Airways'),('DA','Delta Airlines'),('JA','JetBlue Airways'),('LH','Lufthansa'),('SA','Southwest Airlines'),('UA','United Airlines');
/*!40000 ALTER TABLE `Airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Airport`
--

DROP TABLE IF EXISTS `Airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Airport` (
  `Id` char(3) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Country` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Airport`
--

LOCK TABLES `Airport` WRITE;
/*!40000 ALTER TABLE `Airport` DISABLE KEYS */;
INSERT INTO `Airport` VALUES ('ATL','Hartsfield-Jackson Atlanta Int','Atlanta','United States of America'),('BOS','Logan International','Boston','United States of America'),('HND','Tokyo International','Tokyo','Japan'),('JFK','John F. Kennedy International','New York','United States of America'),('LAX','Los Angeles International','Los Angeles','United States of America'),('LGA','LaGuardia','New York','United States of America'),('LHR','London Heathrow','London','United Kingdom'),('ORD','Chicago O\'Hare International','Chicago','United States of America'),('SFO','San Francisco International','San Francisco','United States of America'),('TNR','Ivato International','Antananarivo','Madagascar'),('TXL','Berlin Tegel','Berlin','Germany');
/*!40000 ALTER TABLE `Airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Auctions`
--

DROP TABLE IF EXISTS `Auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Auctions` (
  `AccountNo` int(11) NOT NULL,
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Date` datetime NOT NULL,
  `NYOP` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AccountNo`,`AirlineID`,`FlightNo`,`Class`,`Date`),
  KEY `AirlineID` (`AirlineID`,`FlightNo`),
  CONSTRAINT `auctions_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `Customer` (`AccountNo`),
  CONSTRAINT `auctions_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `Flight` (`AirlineID`, `FlightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auctions`
--

LOCK TABLES `Auctions` WRITE;
/*!40000 ALTER TABLE `Auctions` DISABLE KEYS */;
INSERT INTO `Auctions` VALUES (1111,'AA',111,'First','2011-01-05 00:00:00',500.00),(2222,'AA',111,'Economy','2011-01-05 11:00:00',400.00);
/*!40000 ALTER TABLE `Auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  `CreditCardNo` char(16) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `CreationDate` datetime NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  KEY `Id` (`Id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `Person` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (111,1111,'11111','awesomejane@ftw.com','2011-01-05 17:00:00',5),(222,2222,'22222','jdoe@woot.com','2011-01-05 19:00:00',4),(333,3333,'33333','rickroller@rolld.com','2011-01-06 19:30:00',1);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerPreferences`
--

DROP TABLE IF EXISTS `CustomerPreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomerPreferences` (
  `AccountNo` int(11) NOT NULL,
  `Preference` varchar(50) NOT NULL,
  PRIMARY KEY (`AccountNo`,`Preference`),
  CONSTRAINT `customerpreferences_ibfk_1` FOREIGN KEY (`AccountNo`) REFERENCES `Customer` (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerPreferences`
--

LOCK TABLES `CustomerPreferences` WRITE;
/*!40000 ALTER TABLE `CustomerPreferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `CustomerPreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `Id` int(11) NOT NULL,
  `SSN` int(11) NOT NULL,
  `IsManager` tinyint(1) NOT NULL,
  `StartDate` date NOT NULL,
  `HourlyRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `Id` (`Id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `Person` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (444,444444444,1,'2010-01-05',28.50);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fare`
--

DROP TABLE IF EXISTS `Fare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fare` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `FareType` varchar(20) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Fare` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`,`FareType`,`Class`),
  CONSTRAINT `fare_ibfk_1` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `Flight` (`AirlineID`, `FlightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fare`
--

LOCK TABLES `Fare` WRITE;
/*!40000 ALTER TABLE `Fare` DISABLE KEYS */;
/*!40000 ALTER TABLE `Fare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Flight`
--

DROP TABLE IF EXISTS `Flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Flight` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `NoOfSeats` int(11) NOT NULL,
  `DaysOperating` char(7) NOT NULL,
  `MinLengthOfStay` int(11) NOT NULL,
  `MaxLengthOfStay` int(11) NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`),
  CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`AirlineID`) REFERENCES `Airline` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Flight`
--

LOCK TABLES `Flight` WRITE;
/*!40000 ALTER TABLE `Flight` DISABLE KEYS */;
INSERT INTO `Flight` VALUES ('AA',111,100,'1010100',0,10000),('AM',1337,33,'11',0,10000),('JA',111,150,'1111111',0,10000);
/*!40000 ALTER TABLE `Flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Includes`
--

DROP TABLE IF EXISTS `Includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Includes` (
  `ResrNo` int(11) NOT NULL,
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `LegNo` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ResrNo`,`AirlineID`,`FlightNo`,`LegNo`),
  KEY `AirlineID` (`AirlineID`,`FlightNo`,`LegNo`),
  CONSTRAINT `includes_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `Reservation` (`ResrNo`),
  CONSTRAINT `includes_ibfk_2` FOREIGN KEY (`AirlineID`, `FlightNo`, `LegNo`) REFERENCES `Leg` (`AirlineID`, `FlightNo`, `LegNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Includes`
--

LOCK TABLES `Includes` WRITE;
/*!40000 ALTER TABLE `Includes` DISABLE KEYS */;
INSERT INTO `Includes` VALUES (111,'AA',111,1,'2011-01-05'),(111,'AA',111,2,'2011-01-05'),(222,'JA',111,1,'2011-01-14'),(333,'AM',1337,1,'2011-01-13'),(337,'AA',111,1,'2000-01-01'),(338,'AA',111,1,'2000-01-01'),(339,'AA',111,1,'2000-01-01');
/*!40000 ALTER TABLE `Includes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Leg`
--

DROP TABLE IF EXISTS `Leg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Leg` (
  `AirlineID` char(2) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `LegNo` int(11) NOT NULL,
  `DepAirportID` char(3) NOT NULL,
  `ArrAirportID` char(3) NOT NULL,
  `ArrTime` datetime NOT NULL,
  `DepTime` datetime NOT NULL,
  PRIMARY KEY (`AirlineID`,`FlightNo`,`LegNo`),
  UNIQUE KEY `AirlineID` (`AirlineID`,`FlightNo`,`DepAirportID`),
  KEY `DepAirportID` (`DepAirportID`),
  KEY `ArrAirportID` (`ArrAirportID`),
  CONSTRAINT `leg_ibfk_1` FOREIGN KEY (`AirlineID`, `FlightNo`) REFERENCES `Flight` (`AirlineID`, `FlightNo`),
  CONSTRAINT `leg_ibfk_2` FOREIGN KEY (`DepAirportID`) REFERENCES `Airport` (`Id`),
  CONSTRAINT `leg_ibfk_3` FOREIGN KEY (`ArrAirportID`) REFERENCES `Airport` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Leg`
--

LOCK TABLES `Leg` WRITE;
/*!40000 ALTER TABLE `Leg` DISABLE KEYS */;
INSERT INTO `Leg` VALUES ('AA',111,1,'LGA','LAX','2011-01-05 17:00:00','2011-01-05 11:00:00'),('AA',111,2,'LAX','HND','2011-01-05 19:00:00','2011-01-06 07:30:00'),('AM',1337,1,'JFK','TNR','2011-01-13 07:00:00','2011-01-13 23:00:00'),('JA',111,1,'SFO','BOS','2011-01-10 14:00:00','2011-01-10 19:30:00'),('JA',111,2,'BOS','LHR','2011-01-10 22:30:00','2011-01-11 05:00:00');
/*!40000 ALTER TABLE `Leg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Passenger`
--

DROP TABLE IF EXISTS `Passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Passenger` (
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  PRIMARY KEY (`Id`,`AccountNo`),
  KEY `AccountNo` (`AccountNo`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `Person` (`Id`),
  CONSTRAINT `passenger_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `Customer` (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Passenger`
--

LOCK TABLES `Passenger` WRITE;
/*!40000 ALTER TABLE `Passenger` DISABLE KEYS */;
INSERT INTO `Passenger` VALUES (111,1111),(222,2222),(333,3333);
/*!40000 ALTER TABLE `Passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `Id` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `City` varchar(50) NOT NULL,
  `State` varchar(50) NOT NULL,
  `ZipCode` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (111,'Jane','Smith','100 Nicolls Rd','Stony Brook','New York',17790),(222,'John','Doe','123 Fake Street','New York','New York',10001),(333,'Rick','Astley','1337 Internet Lane','Los Angeles','California',90001),(444,'Random','Guy','159 Random Street','Kansas City','Kansas',11111);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `ResrNo` int(11) NOT NULL,
  `ResrDate` datetime NOT NULL,
  `BookingFee` decimal(10,2) NOT NULL,
  `TotalFare` decimal(10,2) NOT NULL,
  `RepSSN` int(11) DEFAULT NULL,
  `AccountNo` int(11) NOT NULL,
  PRIMARY KEY (`ResrNo`),
  KEY `RepSSN` (`RepSSN`),
  KEY `AccountNo` (`AccountNo`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`RepSSN`) REFERENCES `Employee` (`SSN`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`AccountNo`) REFERENCES `Customer` (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (111,'2013-01-05 17:00:00',300.00,1200.00,444444444,2222),(222,'2013-02-05 17:00:00',300.00,500.00,444444444,1111),(333,'2013-02-06 17:00:00',400.00,3333.33,444444444,3333),(334,'2020-01-04 09:01:04',400.00,1000.00,NULL,1111),(335,'2020-02-04 09:02:24',400.00,1000.00,NULL,1111),(336,'2020-03-04 09:03:54',400.00,1000.00,NULL,1111),(337,'2020-07-04 09:07:49',400.00,1000.00,NULL,1111),(338,'2020-10-04 09:10:08',400.00,1000.00,NULL,1111),(339,'2020-10-04 09:10:21',400.00,1000.00,NULL,1111);
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReservationPassenger`
--

DROP TABLE IF EXISTS `ReservationPassenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReservationPassenger` (
  `ResrNo` int(11) NOT NULL,
  `Id` int(11) NOT NULL,
  `AccountNo` int(11) NOT NULL,
  `SeatNo` char(5) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Meal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ResrNo`,`Id`,`AccountNo`),
  KEY `Id` (`Id`,`AccountNo`),
  CONSTRAINT `reservationpassenger_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `Reservation` (`ResrNo`),
  CONSTRAINT `reservationpassenger_ibfk_2` FOREIGN KEY (`Id`, `AccountNo`) REFERENCES `Passenger` (`Id`, `AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReservationPassenger`
--

LOCK TABLES `ReservationPassenger` WRITE;
/*!40000 ALTER TABLE `ReservationPassenger` DISABLE KEYS */;
INSERT INTO `ReservationPassenger` VALUES (111,222,2222,'33F','Economy','Chips'),(222,111,1111,'13A','First','Fish and Chips'),(333,333,3333,'1A','First','Sushi'),(339,111,1111,'10B','First','Steak');
/*!40000 ALTER TABLE `ReservationPassenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `customerrevenue`
--

DROP TABLE IF EXISTS `customerrevenue`;
/*!50001 DROP VIEW IF EXISTS `customerrevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customerrevenue` AS SELECT 
 1 AS `AccountNo`,
 1 AS `TotalRevenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `totalrevpercust`
--

DROP TABLE IF EXISTS `totalrevpercust`;
/*!50001 DROP VIEW IF EXISTS `totalrevpercust`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `totalrevpercust` AS SELECT 
 1 AS `Id`,
 1 AS `FirstName`,
 1 AS `LastName`,
 1 AS `TotalRev`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `totalrevperrep`
--

DROP TABLE IF EXISTS `totalrevperrep`;
/*!50001 DROP VIEW IF EXISTS `totalrevperrep`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `totalrevperrep` AS SELECT 
 1 AS `id`,
 1 AS `FirstName`,
 1 AS `LastName`,
 1 AS `TotalRev`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `totalrevperrep1`
--

DROP TABLE IF EXISTS `totalrevperrep1`;
/*!50001 DROP VIEW IF EXISTS `totalrevperrep1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `totalrevperrep1` AS SELECT 
 1 AS `Id`,
 1 AS `FirstName`,
 1 AS `LastName`,
 1 AS `TotalRev`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `customerrevenue`
--

/*!50001 DROP VIEW IF EXISTS `customerrevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`pwchristense`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `customerrevenue` AS select `reservation`.`AccountNo` AS `AccountNo`,sum((`reservation`.`TotalFare` * 0.1)) AS `TotalRevenue` from `reservation` group by `reservation`.`AccountNo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `totalrevpercust`
--

/*!50001 DROP VIEW IF EXISTS `totalrevpercust`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`pwchristense`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `totalrevpercust` AS select `C`.`Id` AS `Id`,`P`.`FirstName` AS `FirstName`,`P`.`LastName` AS `LastName`,sum(`R`.`TotalFare`) AS `TotalRev` from ((`reservation` `R` join `customer` `C`) join `person` `P`) where ((`C`.`AccountNo` = `R`.`AccountNo`) and (`P`.`Id` = `C`.`Id`)) group by `C`.`Id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `totalrevperrep`
--

/*!50001 DROP VIEW IF EXISTS `totalrevperrep`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`pwchristense`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `totalrevperrep` AS select `E`.`Id` AS `id`,`P`.`FirstName` AS `FirstName`,`P`.`LastName` AS `LastName`,sum(`R`.`TotalFare`) AS `TotalRev` from ((`reservation` `R` join `employee` `E`) join `person` `P`) where ((`R`.`RepSSN` = `E`.`SSN`) and (`P`.`Id` = `E`.`Id`)) group by `R`.`RepSSN` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `totalrevperrep1`
--

/*!50001 DROP VIEW IF EXISTS `totalrevperrep1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`pwchristense`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `totalrevperrep1` AS select `E`.`Id` AS `Id`,`P`.`FirstName` AS `FirstName`,`P`.`LastName` AS `LastName`,sum(`R`.`TotalFare`) AS `TotalRev` from ((`reservation` `R` join `employee` `E`) join `person` `P`) where ((`R`.`RepSSN` = `E`.`SSN`) and (`P`.`Id` = `E`.`Id`)) group by `R`.`RepSSN` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-06 15:36:33
