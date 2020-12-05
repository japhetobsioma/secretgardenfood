-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2020 at 04:51 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sgf_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `applymembership`
--

CREATE TABLE `applymembership` (
  `ApplyMembershipID` varchar(255) NOT NULL,
  `PremiumID` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `Cost` double(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustomerID` varchar(255) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `FoodID` varchar(255) NOT NULL,
  `FoodName` varchar(255) DEFAULT NULL,
  `UnitPrice` double(6,2) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `OrderFoodID` varchar(255) DEFAULT NULL,
  `FoodID` varchar(255) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `SubTotal` double(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orderfood`
--

CREATE TABLE `orderfood` (
  `OrderFoodID` varchar(255) NOT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `Total` double(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `premium`
--

CREATE TABLE `premium` (
  `PremiumID` varchar(255) NOT NULL,
  `CustomerID` varchar(255) DEFAULT NULL,
  `CardNumber` varchar(255) DEFAULT NULL,
  `RegistrationDate` varchar(255) DEFAULT NULL,
  `ExpirationDate` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `ReceiptNo` varchar(255) NOT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `TotalCost` double(6,2) DEFAULT NULL,
  `ApplyMembershipID` varchar(255) DEFAULT NULL,
  `ReservationID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `ReservationID` varchar(255) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `Time` varchar(255) NOT NULL,
  `Info` varchar(255) DEFAULT NULL,
  `NoPersons` int(11) DEFAULT NULL,
  `RoomTableID` varchar(255) DEFAULT NULL,
  `CustomerID` varchar(255) DEFAULT NULL,
  `Cost` double(6,2) DEFAULT NULL,
  `OrderFoodID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roomtable`
--

CREATE TABLE `roomtable` (
  `RoomTableID` varchar(255) NOT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `Time` varchar(255) DEFAULT NULL,
  `RoomName` varchar(255) DEFAULT NULL,
  `TableName` varchar(255) DEFAULT NULL,
  `Status` varchar(255) NOT NULL DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applymembership`
--
ALTER TABLE `applymembership`
  ADD PRIMARY KEY (`ApplyMembershipID`),
  ADD KEY `PremiumID` (`PremiumID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`FoodID`);

--
-- Indexes for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD KEY `OrderFoodID` (`OrderFoodID`),
  ADD KEY `FoodID` (`FoodID`);

--
-- Indexes for table `orderfood`
--
ALTER TABLE `orderfood`
  ADD PRIMARY KEY (`OrderFoodID`);

--
-- Indexes for table `premium`
--
ALTER TABLE `premium`
  ADD PRIMARY KEY (`PremiumID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`ReceiptNo`),
  ADD KEY `ApplyMembershipID` (`ApplyMembershipID`),
  ADD KEY `ReservationID` (`ReservationID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`ReservationID`),
  ADD KEY `RoomTableID` (`RoomTableID`),
  ADD KEY `CustomerID` (`CustomerID`),
  ADD KEY `OrderFoodID` (`OrderFoodID`);

--
-- Indexes for table `roomtable`
--
ALTER TABLE `roomtable`
  ADD PRIMARY KEY (`RoomTableID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applymembership`
--
ALTER TABLE `applymembership`
  ADD CONSTRAINT `applymembership_ibfk_1` FOREIGN KEY (`PremiumID`) REFERENCES `premium` (`PremiumID`);

--
-- Constraints for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`OrderFoodID`) REFERENCES `orderfood` (`OrderFoodID`),
  ADD CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`FoodID`) REFERENCES `food` (`FoodID`);

--
-- Constraints for table `premium`
--
ALTER TABLE `premium`
  ADD CONSTRAINT `premium_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`ApplyMembershipID`) REFERENCES `applymembership` (`ApplyMembershipID`),
  ADD CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`RoomTableID`) REFERENCES `roomtable` (`RoomTableID`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`OrderFoodID`) REFERENCES `orderfood` (`OrderFoodID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
