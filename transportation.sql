-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2017 at 05:03 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `transportation`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `ID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `TotalPrice` double NOT NULL,
  `Status` varchar(50) NOT NULL,
  `PaymentType` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE IF NOT EXISTS `session` (
  `ID` int(11) NOT NULL,
  `TransporationID` int(11) NOT NULL,
  `Source` varchar(50) NOT NULL,
  `Destination` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `Time` varchar(50) NOT NULL,
  `Price` float NOT NULL,
  `Status` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`ID`, `TransporationID`, `Source`, `Destination`, `Date`, `Time`, `Price`, `Status`) VALUES
(1, 1, 'DHAKA', 'BARISHAL', '27/12/2017', '9 AM - 5 PM', 600, 'Booking'),
(2, 2, 'DHAKA', 'RAJSAHI', '27/12/2017', '9 AM - 5 PM', 600, 'Booking'),
(3, 3, 'DHAKA', 'BARISHAL', '28/12/2017', '9 AM - 5 PM', 600, 'Booking'),
(4, 4, 'DHAKA', 'KHULNA', '29/12/2017', '9AM-5PM', 600, 'Running');

-- --------------------------------------------------------

--
-- Table structure for table `sessionseat`
--

CREATE TABLE IF NOT EXISTS `sessionseat` (
  `ID` int(11) NOT NULL,
  `SessionID` int(11) NOT NULL,
  `SeatNO` varchar(50) NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessionseat`
--

INSERT INTO `sessionseat` (`ID`, `SessionID`, `SeatNO`, `UserID`) VALUES
(6, 2, '4', 4),
(7, 2, '3', 4),
(8, 2, '6', 4),
(9, 1, '1', 4),
(10, 1, '2', 4),
(11, 3, '1', 8);

-- --------------------------------------------------------

--
-- Table structure for table `transport`
--

CREATE TABLE IF NOT EXISTS `transport` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `TransportType` varchar(8) NOT NULL,
  `Registration` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transport`
--

INSERT INTO `transport` (`ID`, `Name`, `TransportType`, `Registration`) VALUES
(1, 'ENAA', 'Bus', 1000000001),
(2, 'SHAMOLY', 'Bus', 542),
(3, 'SHOHAG', 'Bus', 325),
(4, 'ENA', 'Bus', 10000002);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Pass` varchar(8) NOT NULL,
  `UserType` varchar(15) NOT NULL,
  `ContactNo` varchar(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Name`, `UserName`, `Pass`, `UserType`, `ContactNo`, `Status`) VALUES
(1, 'Siam', 'siam99', '123456', 'Admin', '01715036411', 0),
(2, 'Syeed', 'syeed99', '234567', 'Manager', '01552862684', 0),
(3, 'iraz', '258', 'iraz99', 'User', '014125', -1),
(4, 't', 'ty', 't', 'User', '12', 0),
(5, 'iraj', 'iraj99', '258', 'User', '0145', 0),
(6, 're', 're', '87', 'User', '84', 0),
(7, 'Suma', 'suma99', '654321', 'Manager', '01677700845', 0),
(8, 'Rafi', 'rafi99', '987', 'Manager', '987', 0),
(9, 'maisha', 'maisha99', '456', 'Manager', '456', 0),
(10, 'sany', 'sany99', '654', 'User', '654', 0),
(11, 'rifat', 'rifat99', '741', 'Manager', '741', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sessionseat`
--
ALTER TABLE `sessionseat`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `sessionseat`
--
ALTER TABLE `sessionseat`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `transport`
--
ALTER TABLE `transport`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
