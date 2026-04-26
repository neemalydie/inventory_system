-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2026 at 04:43 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ims`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignments`
--

CREATE TABLE `assignments` (
  `id` bigint(20) NOT NULL,
  `condition_at_issue` varchar(255) DEFAULT NULL,
  `condition_at_return` varchar(255) DEFAULT NULL,
  `is_returned` bit(1) NOT NULL,
  `issue_date` datetime(6) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `return_date` datetime(6) DEFAULT NULL,
  `device_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assignments`
--

INSERT INTO `assignments` (`id`, `condition_at_issue`, `condition_at_return`, `is_returned`, `issue_date`, `notes`, `purpose`, `return_date`, `device_id`, `employee_id`) VALUES
(1, 'GOOD', NULL, b'0', '2026-04-23 16:23:42.000000', 'For daily data processing and entry tasks', 'Data Entry', NULL, 2, 1),
(2, 'UNDER_REPAIR', NULL, b'0', '2026-04-23 16:25:49.000000', 'Daily accounting tasks and bookkeeping', 'Accounting Operations', NULL, 3, 2),
(3, 'NEW', 'DAMAGED', b'1', '2026-04-23 16:27:13.000000', 'Receiving service reminders', 'Vehicle Maintenance Alerts', '2026-04-23 16:27:34.000000', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `device_condition` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `device_type` varchar(255) NOT NULL,
  `is_available` bit(1) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `operating_system` varchar(255) DEFAULT NULL,
  `processor` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `serial_number` varchar(255) NOT NULL,
  `storage` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`id`, `brand`, `device_condition`, `created_at`, `device_type`, `is_available`, `model`, `notes`, `operating_system`, `processor`, `ram`, `serial_number`, `storage`, `updated_at`) VALUES
(1, 'Google Pixel', 'DAMAGED', '2026-04-23 14:41:17.000000', 'MOBILE', b'1', 'Pixel 6', 'This is a new Google Pixel 6 that will be used for our employees for work only!!', NULL, NULL, '16GB', '1B123WE3354', '128GB', '2026-04-23 16:03:53.000000'),
(2, 'HP', 'GOOD', '2026-04-23 16:18:56.000000', 'LAPTOP', b'0', 'HPLite ', 'THIS IS THE EXISTING LAPTOP WHICH IS IN GOOD CONDITION.', NULL, NULL, '16GB', '5SB123WE3354', '500 SSD', '2026-04-23 16:18:56.000000'),
(3, 'DEV', 'UNDER_REPAIR', '2026-04-23 16:20:44.000000', 'DESKTOP', b'0', 'Dev desktop', 'THIS IS THE DESKTOP WHICH IS STILLL UNDER REPAIR!!', NULL, NULL, '8GB', '5SB123Wyt3354', '1 TB', '2026-04-23 16:20:44.000000');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `created_at`, `department`, `email`, `employee_id`, `full_name`, `phone_number`) VALUES
(1, '2026-04-23 15:15:15.000000', 'IT', 'mn@gmail.com', 'ATLRW00132', 'MUNYANEZA Nelson', '0786801680'),
(2, '2026-04-23 16:08:39.000000', 'FINANCE', 'neema@gmail.com', 'ATLRW00123', 'NEEMA Lydie', '0781837281'),
(3, '2026-04-23 16:10:06.000000', 'Driver Services', 'isaiesabina@gmail.com', 'ATLRW001123', 'IMANIREBAHOSE Isaie', '0781837284');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `enabled`, `full_name`, `password`, `role`, `updated_at`, `username`) VALUES
(1, '2026-04-23 18:04:59.000000', 'nelson@airtel.com', b'1', 'MUNYANEZA Nelson', '$2a$10$NkMwpUoXgQKJXk5sLtQ4QOjYxKpLqWrEtyUiOpAsDfGhJkLzXcVb', 'ADMIN', NULL, '24RP00844'),
(2, '2026-04-23 18:05:08.000000', 'lydie@airtel.com', b'1', 'NEEMA Lydie', '$2a$10$ZmRzUkVxWXlKuYnJqYrVpOqLpLkIjUhYtFrDeSxAzSwQeRtYuIoP', 'STAFF', NULL, '24RP06514');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignments`
--
ALTER TABLE `assignments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr69edfcloedr3t1ylmiesklif` (`employee_id`),
  ADD KEY `FKf8wta2ky3x6chyvmdfuyw7w31` (`device_id`);

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6ju48hv6y1f2kn982hyxd0wep` (`serial_number`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ovvvp79dq21byf7svnuekb6iw` (`employee_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignments`
--
ALTER TABLE `assignments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignments`
--
ALTER TABLE `assignments`
  ADD CONSTRAINT `FKf8wta2ky3x6chyvmdfuyw7w31` FOREIGN KEY (`device_id`) REFERENCES `devices` (`id`),
  ADD CONSTRAINT `FKr69edfcloedr3t1ylmiesklif` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
