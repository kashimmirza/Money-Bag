-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2016 at 12:38 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moneybag`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `admin_id` varchar(50) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `pin` int(5) DEFAULT NULL,
  `security_question_1` varchar(500) DEFAULT NULL,
  `security_question_answer_1` varchar(500) DEFAULT NULL,
  `security_question_2` varchar(500) DEFAULT NULL,
  `security_question_answer_2` varchar(500) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `last_login` varchar(20) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `admin_id`, `name`, `password`, `pin`, `security_question_1`, `security_question_answer_1`, `security_question_2`, `security_question_answer_2`, `email`, `last_login`, `time`) VALUES
(1, 'admin_10', 'Amlan', '12345', 121, 'What is my favourite color?', 'black', 'What is your favourite emotion?', ';)', 'amlanbarman@gmail.com', NULL, NULL),
(2, 'admin_20', 'Nipa', '6789', 121, 'What is your favourite color?', 'red', 'What is your favourite emotion?', ':P', 'nipa @gmail.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `admin_activity`
--

CREATE TABLE `admin_activity` (
  `id` int(11) NOT NULL,
  `admin_id` varchar(50) NOT NULL,
  `last_admin_activity` varchar(300) DEFAULT NULL,
  `last_edit` varchar(300) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tradepayment`
--

CREATE TABLE `tradepayment` (
  `id` int(11) NOT NULL,
  `user_id` varchar(13) NOT NULL,
  `month` varchar(10) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  `charge` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `transaction_id` varchar(50) NOT NULL,
  `sender_id` varchar(13) DEFAULT NULL,
  `receiver_id` varchar(13) DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deposit` varchar(10) DEFAULT NULL,
  `cashout` varchar(10) DEFAULT NULL,
  `notification_check` int(2) DEFAULT NULL,
  `promise_to_pay` int(2) DEFAULT NULL,
  `promise_sender_id` varchar(13) DEFAULT NULL,
  `promise_receiver_id` varchar(13) DEFAULT NULL,
  `receiver_comments` varchar(500) DEFAULT NULL,
  `sender_comments` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `transaction_id`, `sender_id`, `receiver_id`, `amount`, `time`, `deposit`, `cashout`, `notification_check`, `promise_to_pay`, `promise_sender_id`, `promise_receiver_id`, `receiver_comments`, `sender_comments`) VALUES
(19, '137870344', '201612442038', '201632891340', '20.0', '2016-04-17 08:11:51', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, '1129972802', '201612442038', '201632891340', '100.0', '2016-04-17 08:31:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, '745672231', '201612442038', '201632891340', '100.05', '2016-04-17 09:37:16', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, '148555666', '201612442038', '201632891340', '1000.0', '2016-04-17 09:41:28', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '101065346', '201612442038', '201632891340', '1000.0', '2016-04-17 10:23:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, '21333552', '201612442038', '201632891340', '1000.0', '2016-04-17 10:30:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(43, '1156129579', '201612442038', '201632891340', '100.0', '2016-04-17 11:33:01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(44, '930736070', '201612442038', '201632891340', '100.0', '2016-04-17 11:40:54', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(45, '734875604', '201612442038', '201632891340', '100.0', '2016-04-17 11:44:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(54, '8125306', '201612442038', '201632891340', '10.0', '2016-04-19 07:38:34', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(55, '492044243', '201612442038', '201632891340', '10.0', '2016-04-19 08:50:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(60, '13616375', '201612442038', '201632891340', '10.0', '2016-04-20 10:46:31', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(61, '447818883', '201631873028', '201612442038', '15600.0', '2016-04-28 18:00:52', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(62, '149223498', '201631873028', '201612442038', '10056.0', '2016-04-28 18:03:33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(63, '804627011', '201612442038', '201632891340', '86.0', '2016-04-28 18:11:52', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(64, '1206142822', '201612442038', '201632891340', '10.0', '2016-04-28 19:28:25', NULL, '10.0', NULL, NULL, NULL, NULL, NULL, NULL),
(65, '577007393', '201612442038', '201632891340', '10.0', '2016-04-28 19:34:57', NULL, '10.0', NULL, NULL, NULL, NULL, NULL, NULL),
(66, '949954809', '201612442038', '201632891340', '10.0', '2016-04-28 19:43:07', NULL, '10.0', NULL, NULL, NULL, NULL, NULL, NULL),
(67, '941755308', '201612442038', '201632891340', '10.0', '2016-04-28 22:18:34', NULL, '10.0', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_id` varchar(13) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `date_of_birth` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `profession` varchar(20) DEFAULT NULL,
  `n_id` varchar(30) DEFAULT NULL,
  `d_id` varchar(30) DEFAULT NULL,
  `p_id` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `pin` int(5) DEFAULT NULL,
  `pre_address` varchar(30) DEFAULT NULL,
  `pre_division` varchar(20) DEFAULT NULL,
  `pre_district` varchar(20) DEFAULT NULL,
  `pre_thana` varchar(20) DEFAULT NULL,
  `per_address` varchar(30) DEFAULT NULL,
  `per_division` varchar(20) DEFAULT NULL,
  `per_district` varchar(20) DEFAULT NULL,
  `per_thana` varchar(20) DEFAULT NULL,
  `balance` varchar(10) DEFAULT NULL,
  `personal` int(2) DEFAULT NULL,
  `trade` int(2) DEFAULT NULL,
  `change_maker` int(2) DEFAULT NULL,
  `freeze` int(2) DEFAULT NULL,
  `last_login` varchar(20) DEFAULT NULL,
  `forget_password` int(2) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user_id`, `name`, `email`, `phone`, `date_of_birth`, `gender`, `profession`, `n_id`, `d_id`, `p_id`, `password`, `pin`, `pre_address`, `pre_division`, `pre_district`, `pre_thana`, `per_address`, `per_division`, `per_district`, `per_thana`, `balance`, `personal`, `trade`, `change_maker`, `freeze`, `last_login`, `forget_password`, `time`) VALUES
(1, '201617654321', 'Amlan', 'amlanbarman@gmail.com', '01674276102', '31-1-1991', 'Male', 'Student', '1234567890', '12hdhg38', '', '31121', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '8400.0', 1, 0, 0, 0, NULL, 0, '2016-03-21 06:29:30'),
(3, '201611235678', 'Nipa', 'nipa@gmail.com', '01674.....', '12-12-1992', 'Female', 'Student', '123546789', NULL, NULL, '123456', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '10000000', 1, 0, 0, 0, NULL, 0, '2016-03-21 06:42:05'),
(4, '201632891340', 'Tanzila', 'tanzila@gmail.com', '01674.....', '11-12-1992', 'Female', 'Student', '1236547890', '', '', '1234567', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '39608.05', 0, 0, 1, 0, NULL, 0, '2016-03-21 06:46:06'),
(5, '201616352782', 'Nuzmul', 'nuzmul@gmail.com', '0164', '11-11-1992', 'Male', 'Student', '142536789', NULL, NULL, '12345', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '10000000', 1, 0, 0, 0, NULL, 0, '2016-03-21 06:54:08'),
(6, '201629873452', 'Ruhul', 'ruhul@gmail.com', '01674.....', '1-1-1980', 'Male', 'Android Business Man', '1235678904', NULL, '987283070123', '12345', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '10000000', 0, 1, 0, 0, NULL, 0, '2016-03-21 07:01:10'),
(7, '201614352032', 'Kowsar Mridha', 'kowsar@gmail.com', '01865324816', '12-12-1993', 'Male', 'Job', '12345678', '', '', '23451', 1234, 'Mohakhali', 'Dhaka', 'Dhaka', 'Dhaka', 'Ishapasha', 'Dhaka', 'Sariatpur', 'Noriya', '15000', 1, 0, 0, 1, NULL, 0, '2016-03-23 09:01:29'),
(8, '201613381680', 'Shuvo Mondal', 'shuvomanol7@gmail.com', '01521338168', '16-11-1997', 'Male', 'Student', '2341293049', NULL, NULL, '12345', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Rangpur', 'Rangpur', 'Rangpur', 'Rangpur', '20000000', 1, 0, 0, 0, NULL, 0, '2016-03-28 21:12:19'),
(9, '201620177890', 'Sadek', 'sadek@gmai.com', '0177', '1-1-1990', 'Male', 'Business man', '1291803', '092183', '930481', '12345', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '500000000', 0, 1, 0, 0, NULL, 0, '2016-03-30 05:39:13'),
(10, '201631873028', 'Babu', 'babu@gmail.com', '016999', '1-1-1989', 'Male', 'Politician', '982he18902', '3081h0', '0132e01', '12345', 1234, 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', 'Dhaka', '9974344', 0, 0, 1, 1, NULL, 0, '2016-03-30 05:49:58'),
(15, '201612442038', 'Fariha', 'farihamahmud12@gmail.com', '01680194503', '12/04/2016', 'Female', 'aaa', 'n 12345678910', NULL, NULL, '123456 ', 1234, 'qqq', 'Barisal', 'Barguna', 'Adabor', 'qqq', 'Barisal', 'Barguna', 'Adabor', '25759', NULL, NULL, NULL, NULL, NULL, NULL, '2016-04-12 07:33:49');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`,`admin_id`);

--
-- Indexes for table `admin_activity`
--
ALTER TABLE `admin_activity`
  ADD PRIMARY KEY (`id`,`admin_id`);

--
-- Indexes for table `tradepayment`
--
ALTER TABLE `tradepayment`
  ADD PRIMARY KEY (`id`,`user_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`,`transaction_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`,`user_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_id_2` (`user_id`),
  ADD KEY `user_id_3` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `admin_activity`
--
ALTER TABLE `admin_activity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tradepayment`
--
ALTER TABLE `tradepayment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
