-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 19, 2014 at 11:56 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `luggage-system`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
`id` int(11) NOT NULL,
  `insurer_id` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `prefix` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `address2` varchar(255),
  `postalcode2` varchar(255),
  `residence2` varchar(255),
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `insurer_id`, `firstname`, `prefix`, `lastname`, `gender`, `address`, `postalcode`, `residence`, `address2`, `postalcode2`, `residence2`, `email`, `telephone`, `mobile`) VALUES
(1, 4, 'Debbie', '', 'Patton', 'Other', '8922 Erat, Rd.', '5057ER', 'Assiniboia', '', '', '', 'Duis@luctusut.ca', '0031895584712', '0031645230425'),
(2, 2, 'Lance', 'Hadley', 'Hall', 'Other', '7529 Facilisis. Rd.', '4748SC', 'Buckie', '', '', '', 'ipsum.ac.mi@libero.com', '0031818260402', '0031614060499'),
(3, 3, 'Jordan', 'Zorita', 'Orr', 'Male', 'P.O. Box 105, 6868 Neque Avenue', '5032CB', 'Grand-Leez', '', '', '', 'dolor.quam@auguemalesuadamalesuada.co.uk', '0031304748315', '0031601815769'),
(4, 4, 'Ingrid', 'Lamar', 'Klein', 'Other', '407-3103 Augue, Street', '9837OG', 'Brussel', '', '', '', 'risus.Nulla@insodaleselit.com', '0031200090700', '0031669465616'),
(5, 5, 'Julian', 'Lani', 'Frazier', 'Male', '8421 Eget, Rd.', '1936VP', 'Itzehoe', '', '', '', 'inceptos.hymenaeos@Duiselementum.co.uk', '0031242760109', '0031602866945'),
(6, 6, 'Teegan', 'Vielka', 'Mueller', 'Other', 'P.O. Box 479, 620 Pharetra St.', '2259TV', 'Minderhout', '', '', '', 'vitae.sodales.nisi@habitantmorbi.ca', '0031209392901', '0031650566695'),
(7, 1, 'Venus', 'Dante', 'Mcbride', 'Male', '523-5505 Phasellus Road', '1943SM', 'Saint-Prime', '', '', '', 'euismod@felis.ca', '0031171973695', '0031685038849'),
(8, 4, 'Deborah', 'Odysseus', 'Patton', 'Other', '8922 Erat, Rd.', '5057ER', 'Assiniboia', '', '', '', 'Duis@luctusut.ca', '0031895584712', '0031645230425'),
(9, 1, 'Flynn', 'Timothy', 'Mejia', 'Other', '773-1526 Ipsum Rd.', '2761RC', 'Ingraj Bazar', '', '', '', 'Pellentesque.habitant.morbi@auguescelerisquemollis.ca', '0031651343513', '0031661874114'),
(10, 1, 'Mallory', 'Peter', 'Baxter', 'Male', '4594 Porttitor Road', '9139XH', 'Harlingen', '', '', '', 'sem@Morbinon.ca', '0031413619116', '0031609225454'),
(11, 1, 'Rogan', 'Mona', 'Castro', 'Female', 'Ap #215-4222 In Rd.', '7832TY', 'Cardigan', '', '', '', 'nunc.sed@libero.co.uk', '0031374712468', '0031614609118'),
(12, 1, 'Gail', 'Jackson', 'Hensley', 'Female', 'P.O. Box 563, 9912 Facilisis, Rd.', '9329JY', 'Stonehaven', '', '', '', 'interdum.libero.dui@Maurisnulla.edu', '0031622046889', '0031699456602'),
(13, 1, 'Shaeleigh', 'Dorothy', 'Herman', 'Other', 'P.O. Box 137, 6370 Eu Road', '7409GG', 'Santa Inês', '', '', '', 'ut.pellentesque@eusemPellentesque.com', '0031669394359', '0031640072016'),
(14, 1, 'Gray', 'Oliver', 'Bowman', 'Female', 'Ap #348-6845 Porta Av.', '1546UW', 'Tay', '', '', '', 'Nullam.scelerisque@risusNunc.org', '0031759477111', '0031663701161'),
(15, 1, 'Devin', 'Martin', 'Hopkins', 'Female', '7207 Mauris Av.', '9721QC', 'Hope', '', '', '', 'Donec@augueeutellus.ca', '0031766976282', '0031661972287'),
(16, 1, 'Declan', 'Jameson', 'Vazquez', 'Other', 'Ap #116-8246 Ornare, Rd.', '5926LN', 'Ilbono', '', '', '', 'ut@egettinciduntdui.edu', '0031293733504', '0031680585004'),
(17, 1, 'Clementine', 'Brent', 'Kennedy', 'Female', 'P.O. Box 929, 2751 Vel Street', '1466VX', 'Pettoranello del Molise', '', '', '', 'lacus.Quisque@tempor.net', '0031805328298', '0031639539508'),
(18, 1, 'Inga', 'Carter', 'Lewis', 'Male', '1122 Eu Road', '4563KO', 'Morhet', '', '', '', 'non.nisi@necmalesuadaut.co.uk', '0031925952709', '0031630654455'),
(19, 1, 'Demetria', 'Kamal', 'Haney', 'Female', 'P.O. Box 417, 5151 Pulvinar Rd.', '1500XT', 'Sennariolo', '', '', '', 'Sed.auctor.odio@tinciduntvehicularisus.edu', '0031833995172', '0031642479403'),
(20, 1, 'Brennan', 'Jeanette', 'Chase', 'Male', 'P.O. Box 581, 6255 Arcu Rd.', '8689JE', 'Rio Verde', '', '', '', 'dis@risus.co.uk', '0031909984855', '0031626108620');

-- --------------------------------------------------------

--
-- Table structure for table `insurers`
--

CREATE TABLE IF NOT EXISTS `insurers` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `insurers`
--

INSERT INTO `insurers` (`id`, `name`) VALUES
(6, 'ANWB'),
(3, 'Ditzo Reisverzekeringen'),
(5, 'Europeesche verzekeringen'),
(2, 'FBTO Verzekeringen'),
(4, 'OHRA Reizen'),
(1, 'SNS Verzekeringen');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `name`) VALUES
(1, 'ACE-Lanzarote'),
(2, 'ADB-Izmir'),
(3, 'AGP-Costa Del Sol'),
(4, 'AMS-Amsterdam'),
(5, 'AQJ-Aqaba'),
(6, 'BCN-Barcelona'),
(7, 'BJL-Banjul'),
(8, 'BJV-Bodrum'),
(9, 'BOJ-Burgas'),
(10, 'BR-Bremen'),
(11, 'BRU-Brussel'),
(12, 'CFU-Corfu'),
(13, 'CUR-Curaçao'),
(14, 'DLM-Dalaman'),
(15, 'DTM-Dortmund'),
(16, 'DUS-Düsseldorf'),
(17, 'DXB-Dubai'),
(18, 'ECN-Nicosia'),
(19, 'EIN-Eindhoven'),
(20, 'ETH-Eilat'),
(21, 'FAO-Faro'),
(22, 'FUE-Fuerteventura'),
(23, 'GRQ-Groningen'),
(24, 'GZP-Gazipasa'),
(25, 'HRG-Hurghada'),
(26, 'JSH-Crete Island'),
(27, 'KGS-Kos Island'),
(28, 'LPA-Gran Canaria'),
(29, 'MST-Maastricht'),
(30, 'NBE-Enfidha'),
(31, 'PMI-Mallorca'),
(32, 'RAK-Marrakech'),
(33, 'RHO-Rodes Island'),
(34, 'RTM-Rotterdam'),
(35, 'SAW-Istanbul'),
(36, 'TFS-Tenerife'),
(37, 'TUN-Tunis'),
(38, 'ZTH-Zakynthos');

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` enum('info','warning','error') NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(500) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=125 ;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`id`, `user_id`, `type`, `datetime`, `message`) VALUES
(1, 1, 'info', '2014-11-27 10:07:17', 'Logged in succesfully'),
(2, 1, 'info', '2014-11-28 11:16:14', 'User succesfully logged in'),
(3, 1, 'info', '2014-11-28 11:16:26', 'User updated row from customers, id: 1.'),
(4, 1, 'info', '2014-11-28 11:16:31', 'User updated row from customers, id: 1.'),
(5, 1, 'info', '2014-11-28 11:16:37', 'User deleted row from customers, id: 1.'),
(6, 1, 'info', '2014-11-28 11:18:51', 'User succesfully logged in'),
(7, 1, 'info', '2014-11-28 11:19:11', 'User added row in customers.'),
(8, 1, 'info', '2014-11-28 11:19:16', 'User deleted row from customers, id: 501.'),
(9, 1, 'info', '2014-11-28 11:20:00', 'User updated row from luggage, id: 2.'),
(10, 1, 'info', '2014-11-28 11:20:28', 'User deleted row from luggage, id: 2.'),
(11, 1, 'info', '2014-11-28 11:20:56', 'User succesfully logged in'),
(12, 1, 'info', '2014-11-28 11:22:10', 'User updated row from users, id: 6.'),
(13, 1, 'info', '2014-11-28 11:22:18', 'User deleted row from users, id: 6.'),
(14, 1, 'info', '2014-11-28 11:22:38', 'User added row in users.'),
(15, 1, 'info', '2014-11-28 11:23:44', 'User succesfully logged in'),
(16, 1, 'info', '2014-11-28 11:24:17', 'User succesfully logged in'),
(17, 1, 'info', '2014-11-28 11:26:33', 'User succesfully logged in'),
(18, 1, 'info', '2014-11-28 11:29:03', 'User succesfully logged in'),
(19, 1, 'info', '2014-11-28 11:30:19', 'User succesfully logged in'),
(20, 1, 'info', '2014-11-28 11:31:28', 'User deleted row from users, id: 7.'),
(21, 1, 'info', '2014-11-28 11:34:00', 'User updated row from users, id: 2.'),
(22, 2, 'info', '2014-11-28 11:34:04', 'User succesfully logged in'),
(23, 2, 'info', '2014-11-28 11:34:18', 'User updated row from users, id: 3.'),
(24, 3, 'info', '2014-11-28 11:34:21', 'User succesfully logged in'),
(25, 1, 'info', '2014-11-28 11:34:31', 'User succesfully logged in'),
(26, 1, 'info', '2014-11-28 11:34:42', 'User updated row from users, id: 4.'),
(27, 4, 'info', '2014-11-28 11:34:45', 'User succesfully logged in'),
(28, 1, 'info', '2014-11-28 11:35:17', 'User succesfully logged in'),
(29, 1, 'info', '2014-11-28 11:45:42', 'User succesfully logged in'),
(30, 1, 'info', '2014-11-28 11:53:51', 'User succesfully logged in'),
(31, 1, 'info', '2014-11-28 11:56:01', 'User succesfully logged in'),
(32, 1, 'info', '2014-11-28 11:57:09', 'User succesfully logged in'),
(33, 6, 'info', '2014-12-09 01:18:46', 'User succesfully logged in'),
(34, 6, 'info', '2014-12-09 01:19:07', 'User succesfully logged in'),
(35, 6, 'info', '2014-12-09 01:20:40', 'User succesfully logged in'),
(36, 3, 'info', '2014-12-09 01:21:25', 'User succesfully logged in'),
(37, 6, 'info', '2014-12-09 01:26:19', 'User succesfully logged in'),
(38, 6, 'info', '2014-12-09 01:27:59', 'User succesfully logged in'),
(39, 6, 'info', '2014-12-09 01:28:26', 'User succesfully logged in'),
(40, 6, 'info', '2014-12-09 01:29:31', 'User succesfully logged in'),
(41, 3, 'info', '2014-12-09 01:34:13', 'User succesfully logged in'),
(42, 6, 'info', '2014-12-09 01:35:57', 'User succesfully logged in'),
(43, 6, 'info', '2014-12-09 01:39:47', 'User succesfully logged in'),
(44, 6, 'info', '2014-12-09 01:43:27', 'User succesfully logged in'),
(45, 6, 'info', '2014-12-09 01:55:04', 'User succesfully logged in'),
(46, 3, 'info', '2014-12-10 14:08:58', 'User succesfully logged in'),
(47, 3, 'info', '2014-12-10 14:11:39', 'User succesfully logged in'),
(48, 3, 'info', '2014-12-11 18:33:06', 'User succesfully logged in'),
(49, 6, 'info', '2014-12-11 18:35:22', 'User succesfully logged in'),
(50, 3, 'info', '2014-12-11 18:35:42', 'User succesfully logged in'),
(51, 6, 'info', '2014-12-11 18:36:12', 'User succesfully logged in'),
(52, 6, 'info', '2014-12-11 18:41:30', 'User succesfully logged in'),
(53, 3, 'info', '2014-12-11 18:44:07', 'User succesfully logged in'),
(54, 6, 'info', '2014-12-15 10:27:50', 'User succesfully logged in'),
(55, 6, 'info', '2014-12-15 11:16:50', 'User succesfully logged in'),
(56, 6, 'info', '2014-12-15 11:17:12', 'User succesfully logged in'),
(57, 6, 'info', '2014-12-15 11:17:39', 'User succesfully logged in'),
(58, 4, 'info', '2014-12-15 11:20:10', 'User succesfully logged in'),
(59, 4, 'info', '2014-12-15 11:20:31', 'User succesfully logged in'),
(60, 4, 'info', '2014-12-15 11:21:46', 'User added row in luggage.'),
(61, 6, 'info', '2014-12-15 12:18:29', 'User succesfully logged in'),
(62, 6, 'info', '2014-12-15 12:21:15', 'User succesfully logged in'),
(63, 6, 'info', '2014-12-15 12:21:42', 'User succesfully logged in'),
(64, 6, 'info', '2014-12-15 12:22:40', 'User succesfully logged in'),
(65, 6, 'info', '2014-12-15 12:23:00', 'User succesfully logged in'),
(66, 6, 'info', '2014-12-15 12:23:55', 'User succesfully logged in'),
(67, 6, 'info', '2014-12-15 12:34:51', 'User succesfully logged in'),
(68, 6, 'info', '2014-12-15 12:47:50', 'User succesfully logged in'),
(69, 6, 'info', '2014-12-15 12:48:03', 'User succesfully logged in'),
(70, 6, 'info', '2014-12-15 12:51:57', 'User succesfully logged in'),
(71, 6, 'info', '2014-12-15 13:15:02', 'User succesfully logged in'),
(72, 6, 'info', '2014-12-15 13:16:04', 'User succesfully logged in'),
(73, 6, 'info', '2014-12-15 13:17:04', 'User succesfully logged in'),
(74, 6, 'info', '2014-12-15 13:21:09', 'User succesfully logged in'),
(75, 6, 'info', '2014-12-15 13:26:12', 'User succesfully logged in'),
(76, 6, 'info', '2014-12-15 13:26:44', 'User succesfully logged in'),
(77, 6, 'info', '2014-12-18 12:45:26', 'User succesfully logged in'),
(78, 6, 'info', '2014-12-18 12:46:58', 'User succesfully logged in'),
(79, 6, 'info', '2014-12-18 12:47:14', 'User succesfully logged in'),
(80, 9, 'info', '2014-12-18 12:47:42', 'User succesfully logged in'),
(81, 3, 'info', '2014-12-18 12:47:42', 'User succesfully logged in'),
(82, 4, 'info', '2014-12-18 12:47:53', 'User succesfully logged in'),
(83, 1, 'info', '2014-12-18 12:48:29', 'User succesfully logged in'),
(84, 2, 'info', '2014-12-18 12:48:55', 'User succesfully logged in'),
(85, 6, 'info', '2014-12-18 12:49:51', 'User succesfully logged in'),
(86, 2, 'info', '2014-12-18 15:30:37', 'User succesfully logged in'),
(87, 2, 'info', '2014-12-18 15:31:38', 'User added row in users.'),
(88, 2, 'info', '2014-12-18 15:32:39', 'User added row in users.'),
(89, 2, 'info', '2014-12-18 15:36:28', 'User added row in users.'),
(90, 2, 'info', '2014-12-18 15:37:08', 'User added row in users.'),
(91, 2, 'info', '2014-12-18 15:43:06', 'User added row in users.'),
(92, 2, 'info', '2014-12-18 15:43:15', 'User updated row from users, id: 5.'),
(93, 2, 'info', '2014-12-18 15:44:11', 'User added row in users.'),
(94, 2, 'info', '2014-12-18 15:44:53', 'User added row in users.'),
(95, 13, 'info', '2014-12-18 15:44:59', 'User succesfully logged in'),
(96, 11, 'info', '2014-12-18 15:45:14', 'User succesfully logged in'),
(97, 13, 'info', '2014-12-18 15:45:41', 'User succesfully logged in'),
(98, 12, 'info', '2014-12-18 15:45:45', 'User succesfully logged in'),
(99, 8, 'info', '2014-12-18 15:45:52', 'User succesfully logged in'),
(100, 6, 'info', '2014-12-18 15:46:04', 'User succesfully logged in'),
(101, 7, 'info', '2014-12-18 15:46:09', 'User succesfully logged in'),
(102, 10, 'info', '2014-12-18 15:46:16', 'User succesfully logged in'),
(103, 4, 'info', '2014-12-18 15:46:25', 'User succesfully logged in'),
(104, 6, 'info', '2014-12-18 15:48:14', 'User succesfully logged in'),
(105, 4, 'info', '2014-12-18 16:02:52', 'User succesfully logged in'),
(106, 6, 'info', '2014-12-18 16:18:43', 'User succesfully logged in'),
(107, 6, 'info', '2014-12-18 16:18:58', 'User succesfully logged in'),
(108, 6, 'info', '2014-12-18 16:19:36', 'User added row in luggage.'),
(109, 6, 'info', '2014-12-18 16:20:43', 'User added row in luggage.'),
(110, 6, 'info', '2014-12-18 16:21:31', 'User added row in luggage.'),
(111, 6, 'info', '2014-12-18 16:22:24', 'User added row in luggage.'),
(112, 5, 'info', '2014-12-18 16:22:53', 'User succesfully logged in'),
(113, 5, 'info', '2014-12-18 16:24:02', 'User added row in luggage.'),
(114, 14, 'info', '2014-12-18 23:33:25', 'Login failed. User: "moderator"'),
(115, 14, 'info', '2014-12-18 23:33:27', 'Login failed. User: "moderator"'),
(116, 7, 'info', '2014-12-18 23:47:17', 'User succesfully logged in'),
(117, 14, 'info', '2014-12-19 00:23:10', 'Login failed. User: ""'),
(118, 14, 'info', '2014-12-19 00:23:29', 'Login failed. User: ""'),
(119, 14, 'info', '2014-12-19 00:23:32', 'Login failed. User: ""'),
(120, 14, 'info', '2014-12-19 00:23:34', 'Login failed. User: ""'),
(121, 14, 'info', '2014-12-19 00:23:35', 'Login failed. User: ""'),
(122, 9, 'info', '2014-12-19 00:23:38', 'User succesfully logged in'),
(123, 14, 'info', '2014-12-19 10:42:10', 'Login failed. User: "Gerrit"'),
(124, 6, 'info', '2014-12-19 10:42:14', 'User succesfully logged in');

-- --------------------------------------------------------

--
-- Table structure for table `luggage`
--

CREATE TABLE IF NOT EXISTS `luggage` (
`id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  `removed` tinyint(1) NOT NULL DEFAULT '0',
  `status` enum('Missing','Found','Resolved') NOT NULL,
  `tags` varchar(500) NOT NULL,
  `notes` longtext NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `luggage`
--

INSERT INTO `luggage` (`id`, `customer_id`, `location_id`, `removed`, `status`, `tags`, `notes`, `datetime`) VALUES
(1, 18, 28, 0, 'Resolved', 'aliquam adipiscing lacus. Ut nec urna', 'rutrum eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis', '2014-08-13 08:19:43'),
(2, 12, 3, 0, 'Found', 'Nullam nisl. Maecenas malesuada fringilla est. Mauris eu turpis.', 'arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at, velit. Cras lorem lorem, luctus ut, pellentesque eget, dictum placerat, augue. Sed molestie. Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In faucibus. Morbi', '2013-11-22 02:12:40'),
(3, 18, 21, 0, 'Missing', 'Quisque imperdiet, erat nonummy ultricies', 'pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra sed, hendrerit a, arcu. Sed et', '2012-01-15 08:44:50'),
(4, 10, 22, 0, 'Resolved', 'Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu.', 'conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In faucibus. Morbi vehicula. Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing. Mauris molestie pharetra nibh. Aliquam ornare, libero at auctor ullamcorper, nisl arcu iaculis enim, sit amet', '2012-07-29 19:42:48'),
(5, 20, 10, 1, 'Resolved', 'non quam. Pellentesque habitant morbi tristique senectus et', 'penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus vestibulum lorem, sit amet ultricies sem magna nec quam. Curabitur', '2013-03-18 22:45:54'),
(6, 15, 4, 0, 'Missing', 'Brown suitcase, missing tag', 'Contains laptop', '2014-12-10 00:00:00'),
(7, 16, 12, 0, 'Found', 'Blue', '', '2014-12-17 00:00:00'),
(8, 14, 23, 0, 'Missing', 'Pink Helly Kitty bag', '', '2013-12-04 00:00:00'),
(9, 13, 13, 0, 'Found', 'Black large suitcase, Samsonite', '', '2014-06-19 00:00:00'),
(10, 12, 9, 0, 'Resolved', 'Wheelchair, brand: Mark', 'Foldable', '2011-10-08 00:00:00'),
(11, 11, 23, 0, 'Missing', 'Pink backpack, locks on the zippers, many stickers', 'Contains glass object', '2011-12-02 00:00:00'),
(12, 1, 9, 0, 'Resolved', 'Plastic backpack on wheels', '', '2011-10-08 00:00:00'),
(13, 3, 9, 0, 'Resolved', 'Luggage', '', '2011-10-08 00:00:00'),
(14, 2, 9, 0, 'Resolved', 'Gray suitcase', '', '2011-10-08 00:00:00'),
(15, 4, 9, 0, 'Resolved', 'Blue backpack', '', '2011-10-08 00:00:00'),
(16, 5, 9, 0, 'Resolved', 'Suitcase, tiny, pink', '', '2011-10-08 00:00:00'),
(17, 6, 9, 0, 'Resolved', 'Gray suitcase', '', '2012-10-08 00:00:00'),
(18, 7, 9, 0, 'Resolved', 'Green suitcase', '', '2012-10-08 00:00:00'),
(19, 8, 9, 0, 'Resolved', 'Laptop suitcase', '', '2012-10-08 00:00:00'),
(20, 9, 9, 0, 'Resolved', 'Blue coat, brand: Odin', '', '2011-10-08 00:00:00'),
(21, 11, 9, 0, 'Resolved', 'Gray wheelchair', '', '2010-10-08 00:00:00'),
(22, 10, 9, 0, 'Resolved', 'Purple backpack', '', '2010-10-08 00:00:00'),
(23, 12, 9, 0, 'Resolved', 'Black backpack', '', '2010-10-08 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `prefix` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `role` enum('Super','Manager','Employee','Moderator') NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `location_id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `prefix`, `lastname`, `role`, `gender`, `location_id`, `address`, `postalcode`, `residence`, `telephone`, `mobile`) VALUES
(1, 'Admin', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Super', '', 'Admin', 'Super', 'Male', 8, 'Rootlane', '1000AB', 'Root', '0', '0'),
(2, 'moderator', 'f4931e96e3376e0351ad57693085bd98543a1760bda7e553321ddb3fc8c374ad5e0d0d7feca20f163fd523c3f7d4978285ff2b49bc4d7913af20e068ece633ba', 'Moderator', '', '', 'Moderator', 'Male', 16, 'Lahn 1', '1000AB', 'Dubai', '0', '0'),
(3, 'Manager', 'b1d921f725e7f1696e31a09e86ec52debdbe1f826a77d60705a1682cfd70227200ecb5aee3f8d19cd2061be54ea612b4c713a0e17eb68dd88406a4bc9bd270c3', 'Manager', '', '', 'Manager', 'Male', 1, '21 Street', '1000AB', 'Dubai', '0', '0'),
(4, 'Employee', '3c13b13c0390035b1d4aa3f456cf3fde8ab950056532ce2932581fada8c05fa114ff05c9b9541b73c9dcffa1b5ff76db3c22233975e323565c0af4962988a6af', 'Employee', '', '', 'Employee', 'Female', 25, 'Straat 1', '1000AB', 'Brussels', '0', '0'),
(5, 'Gerrit', '27d10da9b265520f727e13105b08e1adcb36277c3d0a1aaf4d98e21430979aa51616c9070ca37778170b8cdaf5866df7e6065d24e9c87850753fbb7567ca2553', 'Gerrit', 'van de', 'Fabriek', 'Employee', 'Other', 12, 'Rietveldstraat 12', '1012AB', 'Amstelveen', '0031201234567', '0031612345678'),
(6, 'Test', '9c1dd9c1e1684575051616be7814f15374bb73a9f7685ebe30969de4975976fac5fa449519fbc54ddcdc43fcf08eabb292523548349b5ce7fbb86b6fa30c1278', 'Super', '', 'Test', 'Super', 'Other', 4, 'Rootstrasse', '1000AB', 'Root', '0', '0'),
(7, 'Nick', 'b4f52d6d30415aa25e545b15bdd81a93785581c1fd226b0bfaf36043cf76d7d17f8f5dd43b74e5eb5137586fddfc36535b266bf27bd4eb90b798b033b78dfd09', 'Nick', '', 'Onbekend', 'Super', 'Male', 4, 'Abc Straat 21', '1050AB', 'Amsterdam', '003167123456', '003167123456'),
(8, 'Tijme', 'd824e9f681ab297e7a1cb42260c6241d3a0dcb9ede2a1daa5c8b853b223d9274acf969d84a08d3912ffeb0d46a25325d4a3af862d0ce6a5d84f84033621c6537', 'Tijme', '', 'Daar', 'Manager', 'Male', 16, 'Blabla 12', '6592SD', 'Dordrecht', '0049201234466', '0049201234466'),
(9, 'Gerda', 'b9ac1d13d07c61ed07baec88b5aa7193635ca83d9a31b1bf2bd8cc8d96a915f6649bcb3644c3290a123620f5da55731348618ed293aa08463d94ec2ba136f534', 'Gerda', '', 'Balal', 'Moderator', 'Female', 14, 'Amsterdamseweg 20', '1132AD', 'Hoofddorp', '001221324567', '001221324567'),
(10, 'Jasper', 'e515b7af26b997c94375a4775f864a0119d99dc9c06f49c5893de471b5ad3328cfd00a49b36544ca966c340957a493b20c447adb08ed058186e079e861d63bfd', 'Jasper', '', 'Hier', 'Manager', 'Male', 26, 'Hierdaar 3', '5465DF', 'Belgrado', '0012345678974', '0012345678974'),
(11, 'Lars', 'c2b659794a5c1d72ee02d95220fb2e3e0508b2b6f50def959bd9eee69a24ccfa5c4ddd2d059943eade0b9d6b4e346bc867c2dc14d48dd62fc32827f4aee04862', 'Lars', 'de', 'Vries', 'Moderator', 'Male', 18, 'Blabalbal 132', '1023DF', 'Amsterdam', '003145678943', '003145678943'),
(12, 'Ali', '02e03ddba23ecb5d872ff5784fe5383c44a171cd371087d0cf80c6cc3c1a182d68d22a75f62615bf39d4edcabffdd808c57f928e4fb82a49ddafc2485bd350ea', 'Ali', 'de', 'Geeuw', 'Manager', 'Male', 37, 'Amsterdamstraat 124', '8822VD', 'Groningen', '003198374252', '003198374252'),
(13, 'Derp', '38da7d98bc0e173b0767b5baa8880738e0b62603a7c2e08f729adac3f6f0796dd95d9d66925bbd3976410fc9104a894a20f7c633bf1be3b497ca2363976e8ede', 'Derp', '', '', 'Employee', 'Other', 17, 'Derp 1', '1000DP', 'Derp', '003121318232', '003121318232'),
(14, 'At login screen', '38da1d98bc0e173b0767b5baa8880738e0b62603a7c2e08f729adac3f6f0796dd95d8d66925bbd397641dfc9104a894a20f7c6338f1be3b497cb24139b6f8d4e', 'Derp', '', '', 'Employee', 'Other', 17, 'Derp 1', '1000DP', 'Derp', '003121318232', '003121318232'),
(15, 'Customers', '6c39e5abfce7ea660088e1ef27e39018b9e988101272f78a3bf67e191e5e1ccebbe1aa9fcf8f8364906721cba98236d47e4659d54235d0da0770feec58df6acf', 'Customers', '', 'Tab', 'customers', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(16, 'Luggage', 'a39a63ca1707eccec220a918b6d323f7b9a7c2fba61a9ed7d0542c51f8cef338698ba4e5467d1e157b36c9b467afd9d92b6243485a620b8bad6b6b084fc0cfc4', 'Luggage', '', 'Tab', 'luggage', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(17, 'Total', '5b24606b6be78003d1710dffc81bcbc1af83009ae0399498c52381e23213e9f6981b0390b1cdcd8192382f433479a589a088827278c4047bee1a8efcafde54f4', 'Total', '', 'Tab', 'total', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(18, 'Graph', '8ea0fe3563813fe307271854876e8d41cd2bb284d4ded5bb575705fa6620421ce51aa889f77326cf21c14dbab8b9d9ea5f874993b54f09b7cec615457ead25d0', 'Graph', '', 'Tab', 'graph', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(19, 'Missing', 'bad25144cb38c415203971ce9d918872a7719edd2caeb1b07e3502ae1c57d77085b0df9dd63fbe20b16405b6a88172a3b3fa49844c6de60b5712b8bc34fe2ae8', 'Missing', '', 'Tab', 'Found', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(20, 'Found', '2d38f8b439d3eb80ddb8df39069b6be69fde62a3087c819bf732c5ab1a88f0faa290b651bc13778ad84180a87533f665a0fa07f577edfc67c5055492a7e0d6ea', 'Found', '', 'Tab', 'Missing', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(21, 'Resolved', '316cd929689aab3d5c914689e6d62a7ae5b3b64d6da021c5331bf5d10828d2c9b68dadf9abf24bdfc137fcd6611d6288e0d92667cd1db8e478439eac7769c561', 'Resolved', '', 'Tab', 'Resolved', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(22, 'Users', '657628fb8363438074dd5ccc9f19bc14bccae292ba339b91acd7527c47227413689e2634fa2e1dd675de07b6c3fc0e074ccc75f86d3241bdd48c9be531d21ee9', 'Users', '', 'Tab', 'users', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567'),
(23, 'Log', 'ba474872a81e359dc5a82b85e8558c7ce591c5140f31d5283062d709907ba11e155d828be23bba1ea868fcdfb3736ab262ea641ed5d9c56f5c7e77cee6b3d2fd', 'Log', '', 'Tab', 'log', 'Other', 14, 'A', '1000AB', 'Dubai', '001221324567', '001221324567');


--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
 ADD PRIMARY KEY (`id`), ADD KEY `insurer_id` (`insurer_id`), ADD KEY `insurer_id_2` (`insurer_id`);

--
-- Indexes for table `insurers`
--
ALTER TABLE `insurers`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `luggage`
--
ALTER TABLE `luggage`
 ADD PRIMARY KEY (`id`), ADD KEY `customer_id` (`customer_id`,`location_id`), ADD KEY `location_id` (`location_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`), ADD KEY `location_id` (`location_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `insurers`
--
ALTER TABLE `insurers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=125;
--
-- AUTO_INCREMENT for table `luggage`
--
ALTER TABLE `luggage`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`insurer_id`) REFERENCES `insurers` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `log`
--
ALTER TABLE `log`
ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `luggage`
--
ALTER TABLE `luggage`
ADD CONSTRAINT `luggage_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `luggage_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
