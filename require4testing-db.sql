-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2026 at 12:34 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `require4testing-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `anforderung`
--

CREATE TABLE `anforderung` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `anforderung`
--

INSERT INTO `anforderung` (`id`, `name`) VALUES
(1, 'Bedienbarkeit'),
(53, 'Benutzbarkeit');

-- --------------------------------------------------------

--
-- Table structure for table `anforderung_seq`
--

CREATE TABLE `anforderung_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `anforderung_seq`
--

INSERT INTO `anforderung_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `tester`
--

CREATE TABLE `tester` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tester`
--

INSERT INTO `tester` (`id`, `name`) VALUES
(1, 'Tester 1'),
(2, 'Tester 2');

-- --------------------------------------------------------

--
-- Table structure for table `testergebnis`
--

CREATE TABLE `testergebnis` (
  `ergebnis` bit(1) NOT NULL,
  `id` int(11) NOT NULL,
  `testfall_id` int(11) DEFAULT NULL,
  `testlauf_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testergebnis`
--

INSERT INTO `testergebnis` (`ergebnis`, `id`, `testfall_id`, `testlauf_id`) VALUES
(b'0', 208, 1, 1),
(b'1', 209, 1, 52),
(b'1', 210, 52, 52),
(b'0', 211, 53, 52),
(b'0', 212, 1, 152);

-- --------------------------------------------------------

--
-- Table structure for table `testergebnis_seq`
--

CREATE TABLE `testergebnis_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testergebnis_seq`
--

INSERT INTO `testergebnis_seq` (`next_val`) VALUES
(301);

-- --------------------------------------------------------

--
-- Table structure for table `tester_seq`
--

CREATE TABLE `tester_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tester_seq`
--

INSERT INTO `tester_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `testfall`
--

CREATE TABLE `testfall` (
  `anforderung_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testfall`
--

INSERT INTO `testfall` (`anforderung_id`, `id`, `name`) VALUES
(1, 1, 'Warenkorb'),
(1, 52, 'Anmeldung'),
(1, 53, 'Registrierung'),
(53, 103, 'Artikel suchen');

-- --------------------------------------------------------

--
-- Table structure for table `testfall_seq`
--

CREATE TABLE `testfall_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testfall_seq`
--

INSERT INTO `testfall_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Table structure for table `testlauf`
--

CREATE TABLE `testlauf` (
  `id` int(11) NOT NULL,
  `tester_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testlauf`
--

INSERT INTO `testlauf` (`id`, `tester_id`, `name`) VALUES
(1, 1, 'Neuer Käufer'),
(52, 1, 'Neuer Verkäufer'),
(152, 2, 'Angemeldeter Käufer');

-- --------------------------------------------------------

--
-- Table structure for table `testlauf_seq`
--

CREATE TABLE `testlauf_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `testlauf_seq`
--

INSERT INTO `testlauf_seq` (`next_val`) VALUES
(251);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anforderung`
--
ALTER TABLE `anforderung`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tester`
--
ALTER TABLE `tester`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `testergebnis`
--
ALTER TABLE `testergebnis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf48sui5gdj8btxiw6kubm144l` (`testfall_id`),
  ADD KEY `FK6ivgu77xpmkl7w8yf8709w6h8` (`testlauf_id`);

--
-- Indexes for table `testfall`
--
ALTER TABLE `testfall`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm2hpfbry132mkd0ftyqwfyiw0` (`anforderung_id`);

--
-- Indexes for table `testlauf`
--
ALTER TABLE `testlauf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoipdf4r811ywikug3bgwb1ieo` (`tester_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `testergebnis`
--
ALTER TABLE `testergebnis`
  ADD CONSTRAINT `FK6ivgu77xpmkl7w8yf8709w6h8` FOREIGN KEY (`testlauf_id`) REFERENCES `testlauf` (`id`),
  ADD CONSTRAINT `FKf48sui5gdj8btxiw6kubm144l` FOREIGN KEY (`testfall_id`) REFERENCES `testfall` (`id`);

--
-- Constraints for table `testfall`
--
ALTER TABLE `testfall`
  ADD CONSTRAINT `FKm2hpfbry132mkd0ftyqwfyiw0` FOREIGN KEY (`anforderung_id`) REFERENCES `anforderung` (`id`);

--
-- Constraints for table `testlauf`
--
ALTER TABLE `testlauf`
  ADD CONSTRAINT `FKoipdf4r811ywikug3bgwb1ieo` FOREIGN KEY (`tester_id`) REFERENCES `tester` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
