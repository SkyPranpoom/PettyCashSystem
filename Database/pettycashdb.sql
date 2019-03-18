-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 15, 2019 at 09:06 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pettycashdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Id` varchar(10) NOT NULL,
  `Employee_code` varchar(10) NOT NULL,
  `Firstname` text NOT NULL,
  `Surname` text NOT NULL,
  `Telephone` varchar(10) NOT NULL,
  `Job_level` int(11) NOT NULL,
  `Department` text NOT NULL,
  `Status` int(11) NOT NULL,
  `Manager_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pettycash`
--

CREATE TABLE `pettycash` (
  `Id` int(1) NOT NULL,
  `Code` text NOT NULL,
  `Emp_id` varchar(10) NOT NULL,
  `Description` text NOT NULL,
  `Amount` text NOT NULL,
  `Status` text NOT NULL,
  `Create_datetime` text NOT NULL,
  `Update_datetime` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pettycash`
--

INSERT INTO `pettycash` (`Id`, `Code`, `Emp_id`, `Description`, `Amount`, `Status`, `Create_datetime`, `Update_datetime`) VALUES
(1, 'PC-2019-0001', '562115030', 'New mac book pro for dev', '80000', 'Approve', '2019/03/14', '2019/03/15'),
(2, 'PC-2019-0002', '562115031', 'Mac Pro for edit', '1000000', 'Approve', '2019/03/15', '2019/03/15'),
(3, 'PC-2019-0003', '562115031', 'Fuel to CM', '20000', 'Not Approve', '2019/03/15', '2019/03/15'),
(4, 'PC-2019-0004', '562115033', 'Computer rental', '30000', 'Request', '2019/03/15', '2019/03/15'),
(5, 'PC-2019-0005', '56222225', 'Dinner with customer', '2800', 'Request', '2019/03/15', '2019/03/15'),
(6, 'PC-2019-0006', '562115030', 'Computer rental', '1000', 'Request', '2019/03/15', '2019/03/15'),
(7, 'PC-2019-0007', '562115031', 'FinalTest-Edited', '111111', 'Request', '2019/03/15', '2019/03/15'),
(8, 'PC-2019-0008', '562115030', 'FinaTest-Approve', '999999', 'Approve', '2019/03/15', '2019/03/15'),
(9, 'PC-2019-0009', '562115030', 'Test-Not Approve', '12345.75', 'Not Approve', '2019/03/15', '2019/03/15'),
(10, 'PC-2019-0010', '562115030', 'Test-Cancel', '888888.88', 'Cancel', '2019/03/15', '2019/03/15'),
(11, 'PC-2019-0011', '562115030', 'Test-Cancel2', '123456.55', 'Cancel', '2019/03/15', '2019/03/15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pettycash`
--
ALTER TABLE `pettycash`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pettycash`
--
ALTER TABLE `pettycash`
  MODIFY `Id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
