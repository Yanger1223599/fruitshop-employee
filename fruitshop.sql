CREATE DATABASE  IF NOT EXISTS `fruitshop99` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fruitshop99`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: fruitshop99
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerid` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customerid` (`customerid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (3,'C003','CustomerId','CustomerId','CustomerId','CustomerId','CustomerId'),(4,'C004','fruitshop99','fruitshop99','fruitshop99','fruitshop99','fruitshop99'),(5,'C005','yang','yang','yang','yang','yang'),(6,'C006','123','123','123','123','123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeNo` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'E001','yan','yan','yan'),(5,'E002','yan','123','yan'),(6,'E003','789','798','789'),(7,'E004','456','456','456'),(8,'E005','963','963','963');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porder`
--

DROP TABLE IF EXISTS `porder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(50) NOT NULL,
  `customerId` varchar(50) NOT NULL,
  `productno` varchar(20) NOT NULL,
  `amount` int NOT NULL,
  `orderDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `customerId` (`customerId`),
  KEY `productno` (`productno`),
  CONSTRAINT `porder_ibfk_2` FOREIGN KEY (`productno`) REFERENCES `product` (`productno`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porder`
--

LOCK TABLES `porder` WRITE;
/*!40000 ALTER TABLE `porder` DISABLE KEYS */;
INSERT INTO `porder` VALUES (5,'O0001','C001','P003',14,'2025-08-21 04:03:30'),(6,'O0002','C001','P003',14,'2025-08-21 04:08:01'),(8,'O0004','C001','P003',4,'2025-08-21 05:54:49'),(9,'O0005','C001','P003',14,'2025-08-21 06:03:30'),(10,'O0006','C004','P002',4,'2025-08-21 06:19:39'),(11,'O0007','C004','P002',41,'2025-08-21 06:39:49'),(12,'O0008','C004','P001',4,'2025-08-21 06:51:28'),(14,'O0010','C005','P001',1,'2025-08-22 01:16:26'),(15,'O0011','C005','P001',1,'2025-08-22 01:16:27'),(16,'O0012','C005','P001',1,'2025-08-22 01:16:29'),(17,'O0013','C005','P001',1,'2025-08-22 01:16:30'),(18,'O0014','C005','P001',1,'2025-08-22 01:16:33'),(19,'O0015','C005','P001',7,'2025-08-22 01:17:04'),(20,'O0016','C005','P001',7,'2025-08-22 01:17:08'),(22,'O0018','C005','P001',7,'2025-08-22 01:17:10'),(24,'O0019','C005','P007',4,'2025-08-22 06:38:03'),(25,'O0020','C005','P004',7,'2025-08-22 07:21:41'),(26,'O0021','C005','P005',7,'2025-08-22 07:22:11'),(27,'O0022','C003','P006',7,'2025-08-22 07:35:54'),(28,'O0023','C003','P003',7,'2025-08-22 07:55:40'),(29,'O0024','C003','P003',4,'2025-08-22 07:57:11'),(31,'O0025','C005','P006',4,'2025-08-22 08:02:39');
/*!40000 ALTER TABLE `porder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productno` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productno` (`productno`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'P001','蘋果',30),(2,'P002','香蕉',20),(3,'P003','西瓜',100),(5,'P004','梨子',45),(6,'P005','鳳梨',120),(7,'P006','草莓',200),(8,'P007','橘子',80);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-25  9:36:11
