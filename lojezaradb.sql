-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2020 at 07:40 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lojezaradb`
--

-- --------------------------------------------------------

--
-- Table structure for table `lojtaret`
--

CREATE TABLE `lojtaret` (
  `username` varchar(15) NOT NULL,
  `emri` varchar(20) NOT NULL,
  `mbiemri` varchar(20) NOT NULL,
  `mosha` int(3) NOT NULL,
  `piket_max` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lojtaret`
--

INSERT INTO `lojtaret` (`username`, `emri`, `mbiemri`, `mosha`, `piket_max`) VALUES
('ersi19', 'ersi', 'bajrami', 20, 54),
('lojtar1', 'e1', 'm1', 21, 52),
('lojtar2', 'e2', 'm2', 32, 38);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lojtaret`
--
ALTER TABLE `lojtaret`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
