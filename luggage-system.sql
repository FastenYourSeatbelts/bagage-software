-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Machine: 127.0.0.1
-- Gegenereerd op: 28 nov 2014 om 13:01
-- Serverversie: 5.6.21
-- PHP-versie: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `luggage-system`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
`id` int(11) NOT NULL,
  `insurer_id` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `prefix` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `gender` enum('male','female','other') NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `customers`
--

INSERT INTO `customers` (`id`, `insurer_id`, `firstname`, `prefix`, `lastname`, `gender`, `address`, `postalcode`, `residence`, `email`, `telephone`, `mobile`) VALUES
(2, 2, 'Lance', 'Hadley', 'Hall', 'other', '7529 Facilisis. Rd.', '4748SC', 'Buckie', 'ipsum.ac.mi@libero.com', '0031818260402', '0031614060499'),
(3, 3, 'Jordan', 'Zorita', 'Orr', 'male', 'P.O. Box 105, 6868 Neque Avenue', '5032CB', 'Grand-Leez', 'dolor.quam@auguemalesuadamalesuada.co.uk', '0031304748315', '0031601815769'),
(4, 4, 'Ingrid', 'Lamar', 'Klein', 'other', '407-3103 Augue, Street', '9837OG', 'Brussel', 'risus.Nulla@insodaleselit.com', '0031200090700', '0031669465616'),
(5, 5, 'Julian', 'Lani', 'Frazier', 'male', '8421 Eget, Rd.', '1936VP', 'Itzehoe', 'inceptos.hymenaeos@Duiselementum.co.uk', '0031242760109', '0031602866945'),
(6, 6, 'Teegan', 'Vielka', 'Mueller', 'other', 'P.O. Box 479, 620 Pharetra St.', '2259TV', 'Minderhout', 'vitae.sodales.nisi@habitantmorbi.ca', '0031209392901', '0031650566695'),
(7, 1, 'Venus', 'Dante', 'Mcbride', 'male', '523-5505 Phasellus Road', '1943SM', 'Saint-Prime', 'euismod@felis.ca', '0031171973695', '0031685038849'),
(8, 4, 'Deborah', 'Odysseus', 'Patton', 'other', '8922 Erat, Rd.', '5057ER', 'Assiniboia', 'Duis@luctusut.ca', '0031895584712', '0031645230425'),
(9, 1, 'Flynn', 'Timothy', 'Mejia', 'other', '773-1526 Ipsum Rd.', '2761RC', 'Ingraj Bazar', 'Pellentesque.habitant.morbi@auguescelerisquemollis.ca', '0031651343513', '0031661874114'),
(10, 1, 'Mallory', 'Peter', 'Baxter', 'male', '4594 Porttitor Road', '9139XH', 'Harlingen', 'sem@Morbinon.ca', '0031413619116', '0031609225454'),
(11, 1, 'Rogan', 'Mona', 'Castro', 'female', 'Ap #215-4222 In Rd.', '7832TY', 'Cardigan', 'nunc.sed@libero.co.uk', '0031374712468', '0031614609118'),
(12, 1, 'Gail', 'Jackson', 'Hensley', 'female', 'P.O. Box 563, 9912 Facilisis, Rd.', '9329JY', 'Stonehaven', 'interdum.libero.dui@Maurisnulla.edu', '0031622046889', '0031699456602'),
(13, 1, 'Shaeleigh', 'Dorothy', 'Herman', 'other', 'P.O. Box 137, 6370 Eu Road', '7409GG', 'Santa Inês', 'ut.pellentesque@eusemPellentesque.com', '0031669394359', '0031640072016'),
(14, 1, 'Gray', 'Oliver', 'Bowman', 'female', 'Ap #348-6845 Porta Av.', '1546UW', 'Tay', 'Nullam.scelerisque@risusNunc.org', '0031759477111', '0031663701161'),
(15, 1, 'Devin', 'Martin', 'Hopkins', 'female', '7207 Mauris Av.', '9721QC', 'Hope', 'Donec@augueeutellus.ca', '0031766976282', '0031661972287'),
(16, 1, 'Declan', 'Jameson', 'Vazquez', 'other', 'Ap #116-8246 Ornare, Rd.', '5926LN', 'Ilbono', 'ut@egettinciduntdui.edu', '0031293733504', '0031680585004'),
(17, 1, 'Clementine', 'Brent', 'Kennedy', 'female', 'P.O. Box 929, 2751 Vel Street', '1466VX', 'Pettoranello del Molise', 'lacus.Quisque@tempor.net', '0031805328298', '0031639539508'),
(18, 1, 'Inga', 'Carter', 'Lewis', 'male', '1122 Eu Road', '4563KO', 'Morhet', 'non.nisi@necmalesuadaut.co.uk', '0031925952709', '0031630654455'),
(19, 1, 'Demetria', 'Kamal', 'Haney', 'female', 'P.O. Box 417, 5151 Pulvinar Rd.', '1500XT', 'Sennariolo', 'Sed.auctor.odio@tinciduntvehicularisus.edu', '0031833995172', '0031642479403'),
(20, 1, 'Brennan', 'Jeanette', 'Chase', 'male', 'P.O. Box 581, 6255 Arcu Rd.', '8689JE', 'Rio Verde', 'dis@risus.co.uk', '0031909984855', '0031626108620');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `insurers`
--

CREATE TABLE IF NOT EXISTS `insurers` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `insurers`
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
-- Tabelstructuur voor tabel `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `locations`
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
-- Tabelstructuur voor tabel `log`
--

CREATE TABLE IF NOT EXISTS `log` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` enum('info','warning','error') NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `log`
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
(16, 1, 'info', '2014-11-28 11:23:44', 'User succesfully logged in'),
(17, 1, 'info', '2014-11-28 11:24:17', 'User succesfully logged in'),
(18, 1, 'info', '2014-11-28 11:26:33', 'User succesfully logged in'),
(19, 1, 'info', '2014-11-28 11:29:03', 'User succesfully logged in'),
(20, 1, 'info', '2014-11-28 11:30:19', 'User succesfully logged in'),
(21, 1, 'info', '2014-11-28 11:31:28', 'User deleted row from users, id: 7.'),
(22, 1, 'info', '2014-11-28 11:34:00', 'User updated row from users, id: 2.'),
(23, 2, 'info', '2014-11-28 11:34:04', 'User succesfully logged in'),
(24, 2, 'info', '2014-11-28 11:34:18', 'User updated row from users, id: 3.'),
(25, 3, 'info', '2014-11-28 11:34:21', 'User succesfully logged in'),
(26, 1, 'info', '2014-11-28 11:34:31', 'User succesfully logged in'),
(27, 1, 'info', '2014-11-28 11:34:42', 'User updated row from users, id: 4.'),
(28, 4, 'info', '2014-11-28 11:34:45', 'User succesfully logged in'),
(29, 1, 'info', '2014-11-28 11:35:17', 'User succesfully logged in'),
(30, 1, 'info', '2014-11-28 11:45:42', 'User succesfully logged in'),
(31, 1, 'info', '2014-11-28 11:53:51', 'User succesfully logged in'),
(32, 1, 'info', '2014-11-28 11:56:01', 'User succesfully logged in'),
(33, 1, 'info', '2014-11-28 11:57:09', 'User succesfully logged in'),
(34, 6, 'info', '2014-12-09 01:18:46', 'User succesfully logged in'),
(35, 6, 'info', '2014-12-09 01:19:07', 'User succesfully logged in'),
(36, 6, 'info', '2014-12-09 01:20:40', 'User succesfully logged in'),
(37, 3, 'info', '2014-12-09 01:21:25', 'User succesfully logged in'),
(38, 6, 'info', '2014-12-09 01:26:19', 'User succesfully logged in'),
(39, 6, 'info', '2014-12-09 01:27:59', 'User succesfully logged in'),
(40, 6, 'info', '2014-12-09 01:28:26', 'User succesfully logged in'),
(41, 6, 'info', '2014-12-09 01:29:31', 'User succesfully logged in'),
(42, 3, 'info', '2014-12-09 01:34:13', 'User succesfully logged in'),
(43, 6, 'info', '2014-12-09 01:35:57', 'User succesfully logged in'),
(44, 6, 'info', '2014-12-09 01:39:47', 'User succesfully logged in'),
(45, 6, 'info', '2014-12-09 01:43:27', 'User succesfully logged in'),
(46, 6, 'info', '2014-12-09 01:55:04', 'User succesfully logged in'),
(47, 6, 'info', '2014-12-09 01:56:32', 'User succesfully logged in'),
(48, 6, 'info', '2014-12-09 01:57:27', 'User succesfully logged in'),
(49, 6, 'info', '2014-12-09 02:02:01', 'User succesfully logged in'),
(50, 6, 'info', '2014-12-09 02:02:14', 'User succesfully logged in'),
(51, 6, 'info', '2014-12-09 02:02:32', 'User succesfully logged in'),
(52, 6, 'info', '2014-12-09 02:42:36', 'User succesfully logged in'),
(53, 6, 'info', '2014-12-09 02:46:03', 'User succesfully logged in'),
(54, 6, 'info', '2014-12-09 02:47:00', 'User succesfully logged in'),
(55, 6, 'info', '2014-12-09 02:47:16', 'User succesfully logged in'),
(56, 6, 'info', '2014-12-09 02:49:51', 'User succesfully logged in'),
(57, 6, 'info', '2014-12-10 14:03:33', 'User succesfully logged in'),
(58, 6, 'info', '2014-12-10 14:08:39', 'User succesfully logged in'),
(59, 3, 'info', '2014-12-10 14:08:58', 'User succesfully logged in'),
(60, 3, 'info', '2014-12-10 14:11:39', 'User succesfully logged in'),
(61, 3, 'info', '2014-12-11 18:33:06', 'User succesfully logged in'),
(62, 6, 'info', '2014-12-11 18:35:22', 'User succesfully logged in'),
(63, 3, 'info', '2014-12-11 18:35:42', 'User succesfully logged in'),
(64, 6, 'info', '2014-12-11 18:36:12', 'User succesfully logged in'),
(65, 6, 'info', '2014-12-11 18:41:30', 'User succesfully logged in'),
(66, 3, 'info', '2014-12-11 18:44:07', 'User succesfully logged in'),
(67, 6, 'info', '2014-12-15 10:27:50', 'User succesfully logged in'),
(68, 6, 'info', '2014-12-15 11:16:50', 'User succesfully logged in'),
(69, 6, 'info', '2014-12-15 11:17:12', 'User succesfully logged in'),
(70, 6, 'info', '2014-12-15 11:17:39', 'User succesfully logged in'),
(71, 4, 'info', '2014-12-15 11:20:10', 'User succesfully logged in'),
(72, 4, 'info', '2014-12-15 11:20:31', 'User succesfully logged in'),
(73, 4, 'info', '2014-12-15 11:21:46', 'User added row in luggage.'),
(74, 6, 'info', '2014-12-15 12:18:29', 'User succesfully logged in'),
(75, 6, 'info', '2014-12-15 12:21:15', 'User succesfully logged in'),
(76, 6, 'info', '2014-12-15 12:21:42', 'User succesfully logged in'),
(77, 6, 'info', '2014-12-15 12:22:40', 'User succesfully logged in'),
(78, 6, 'info', '2014-12-15 12:23:00', 'User succesfully logged in'),
(79, 6, 'info', '2014-12-15 12:23:55', 'User succesfully logged in'),
(80, 6, 'info', '2014-12-15 12:34:51', 'User succesfully logged in'),
(81, 6, 'info', '2014-12-15 12:47:50', 'User succesfully logged in'),
(82, 6, 'info', '2014-12-15 12:48:03', 'User succesfully logged in'),
(83, 6, 'info', '2014-12-15 12:51:57', 'User succesfully logged in'),
(84, 6, 'info', '2014-12-15 12:52:07', 'User succesfully logged in'),
(85, 3, 'info', '2014-12-15 12:52:22', 'User succesfully logged in'),
(86, 6, 'info', '2014-12-15 12:55:31', 'User succesfully logged in'),
(87, 6, 'info', '2014-12-15 12:56:44', 'User succesfully logged in'),
(88, 6, 'info', '2014-12-15 12:58:28', 'User succesfully logged in'),
(89, 6, 'info', '2014-12-15 12:59:17', 'User succesfully logged in'),
(90, 6, 'info', '2014-12-15 13:00:43', 'User succesfully logged in'),
(91, 6, 'info', '2014-12-15 13:08:11', 'User succesfully logged in'),
(92, 6, 'info', '2014-12-15 13:13:44', 'User succesfully logged in'),
(93, 6, 'info', '2014-12-15 13:15:02', 'User succesfully logged in'),
(94, 6, 'info', '2014-12-15 13:16:04', 'User succesfully logged in'),
(95, 6, 'info', '2014-12-15 13:17:04', 'User succesfully logged in'),
(96, 6, 'info', '2014-12-15 13:21:09', 'User succesfully logged in'),
(97, 6, 'info', '2014-12-15 13:26:12', 'User succesfully logged in'),
(98, 6, 'info', '2014-12-15 13:26:44', 'User succesfully logged in'),
(99, 6, 'info', '2014-12-18 12:45:26', 'User succesfully logged in'),
(100, 6, 'info', '2014-12-18 12:46:58', 'User succesfully logged in'),
(101, 6, 'info', '2014-12-18 12:47:14', 'User succesfully logged in'),
(102, 3, 'info', '2014-12-18 12:47:42', 'User succesfully logged in'),
(103, 4, 'info', '2014-12-18 12:47:53', 'User succesfully logged in'),
(104, 1, 'info', '2014-12-18 12:48:29', 'User succesfully logged in'),
(105, 2, 'info', '2014-12-18 12:48:55', 'User succesfully logged in'),
(106, 6, 'info', '2014-12-18 12:49:51', 'User succesfully logged in'),
(107, 2, 'info', '2014-12-18 15:30:37', 'User succesfully logged in'),
(108, 2, 'info', '2014-12-18 15:31:38', 'User added row in users.'),
(109, 2, 'info', '2014-12-18 15:32:39', 'User added row in users.'),
(110, 2, 'info', '2014-12-18 15:36:28', 'User added row in users.'),
(111, 2, 'info', '2014-12-18 15:37:08', 'User added row in users.'),
(112, 2, 'info', '2014-12-18 15:43:06', 'User added row in users.'),
(113, 2, 'info', '2014-12-18 15:43:15', 'User updated row from users, id: 5.'),
(114, 2, 'info', '2014-12-18 15:44:11', 'User added row in users.'),
(115, 2, 'info', '2014-12-18 15:44:53', 'User added row in users.'),
(116, 13, 'info', '2014-12-18 15:44:59', 'User succesfully logged in'),
(117, 11, 'info', '2014-12-18 15:45:14', 'User succesfully logged in'),
(118, 13, 'info', '2014-12-18 15:45:41', 'User succesfully logged in'),
(119, 12, 'info', '2014-12-18 15:45:45', 'User succesfully logged in'),
(120, 8, 'info', '2014-12-18 15:45:52', 'User succesfully logged in'),
(121, 6, 'info', '2014-12-18 15:46:04', 'User succesfully logged in'),
(122, 7, 'info', '2014-12-18 15:46:09', 'User succesfully logged in'),
(123, 10, 'info', '2014-12-18 15:46:16', 'User succesfully logged in'),
(124, 4, 'info', '2014-12-18 15:46:25', 'User succesfully logged in'),
(125, 6, 'info', '2014-12-18 15:48:14', 'User succesfully logged in'),
(126, 4, 'info', '2014-12-18 16:02:52', 'User succesfully logged in'),
(127, 6, 'info', '2014-12-18 16:08:58', 'User succesfully logged in'),
(128, 6, 'info', '2014-12-18 16:10:04', 'User succesfully logged in'),
(129, 6, 'info', '2014-12-18 16:18:07', 'User succesfully logged in'),
(130, 6, 'info', '2014-12-18 16:18:32', 'User succesfully logged in'),
(131, 6, 'info', '2014-12-18 16:18:43', 'User succesfully logged in'),
(132, 6, 'info', '2014-12-18 16:18:58', 'User succesfully logged in'),
(133, 6, 'info', '2014-12-18 16:19:36', 'User added row in luggage.'),
(134, 6, 'info', '2014-12-18 16:20:43', 'User added row in luggage.'),
(135, 6, 'info', '2014-12-18 16:21:31', 'User added row in luggage.'),
(136, 6, 'info', '2014-12-18 16:22:24', 'User added row in luggage.'),
(137, 5, 'info', '2014-12-18 16:22:53', 'User succesfully logged in'),
(138, 5, 'info', '2014-12-18 16:24:02', 'User added row in luggage.');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `luggage`
--

CREATE TABLE IF NOT EXISTS `luggage` (
`id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  `removed` tinyint(1) NOT NULL DEFAULT '0',
  `status` enum('Missing','Found','Resolved') NOT NULL DEFAULT 'Missing',
  `tags` varchar(500) NOT NULL,
  `notes` longtext NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3994 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `luggage`
--

INSERT INTO `luggage` (`id`, `customer_id`, `location_id`, `removed`, `status`, `tags`, `notes`, `datetime`) VALUES
(1, 18, 28, 0, 'Resolved', 'aliquam adipiscing lacus. Ut nec urna', 'rutrum eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis', '2014-08-13 08:19:43'),
(2, 12, 3, 0, 'Found', 'Nullam nisl. Maecenas malesuada fringilla est. Mauris eu turpis.', 'arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at, velit. Cras lorem lorem, luctus ut, pellentesque eget, dictum placerat, augue. Sed molestie. Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In faucibus. Morbi', '2013-11-22 02:12:40'),
(3, 18, 21, 0, 'Missing', 'Quisque imperdiet, erat nonummy ultricies', 'pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra sed, hendrerit a, arcu. Sed et', '2012-01-15 08:44:50'),
(4, 10, 22, 0, 'Resolved', 'Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu.', 'conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In faucibus. Morbi vehicula. Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing. Mauris molestie pharetra nibh. Aliquam ornare, libero at auctor ullamcorper, nisl arcu iaculis enim, sit amet', '2012-07-29 19:42:48'),
(5, 20, 10, 1, 'Resolved', 'non quam. Pellentesque habitant morbi tristique senectus et', 'penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus vestibulum lorem, sit amet ultricies sem magna nec quam. Curabitur', '2013-03-18 22:45:54'),
(6, NULL, 4, 0, 'Missing', 'Brown suitcase, missing tag', 'Contains laptop', '2014-12-10 00:00:00'),
(7, NULL, 12, 0, 'Found', 'Blue', '', '2014-12-17 00:00:00'),
(8, NULL, 23, 0, 'Missing', 'Pink Helly Kitty bag', '', '2013-12-04 00:00:00'),
(9, NULL, 13, 0, 'Found', 'Black large suitcase, Samsonite', '', '2014-06-19 00:00:00'),
(10, NULL, 9, 0, 'Resolved', 'Wheelchair, brand: Mark', 'Foldable', '2011-10-08 00:00:00'),
(11, NULL, 23, 0, 'Missing', 'Pink backpack, locks on the zippers, many stickers', 'Contains glass object', '2011-12-02 00:00:00');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `prefix` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `role` enum('super','manager','employee','moderator','') NOT NULL DEFAULT 'employee',
  `gender` enum('male','female','other') NOT NULL,
  `location_id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `prefix`, `lastname`, `role`, `gender`, `location_id`, `address`, `postalcode`, `residence`, `telephone`, `mobile`) VALUES
(1, 'Admin', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Super', '', 'Admin', 'super', 'male', 8, '', '', '', '', ''),
(2, 'moderator', 'f4931e96e3376e0351ad57693085bd98543a1760bda7e553321ddb3fc8c374ad5e0d0d7feca20f163fd523c3f7d4978285ff2b49bc4d7913af20e068ece633ba', 'Moderator', '', '', 'moderator', 'male', 16, '', '', '', '', ''),
(3, 'manager', 'b1d921f725e7f1696e31a09e86ec52debdbe1f826a77d60705a1682cfd70227200ecb5aee3f8d19cd2061be54ea612b4c713a0e17eb68dd88406a4bc9bd270c3', 'Manager', '', '', 'manager', 'male', 1, '', '', '', '', ''),
(4, 'employee', '3c13b13c0390035b1d4aa3f456cf3fde8ab950056532ce2932581fada8c05fa114ff05c9b9541b73c9dcffa1b5ff76db3c22233975e323565c0af4962988a6af', 'Employee', '', '', 'employee', 'female', 25, '', '', '', '', ''),
(5, 'Gerrit', '27d10da9b265520f727e13105b08e1adcb36277c3d0a1aaf4d98e21430979aa51616c9070ca37778170b8cdaf5866df7e6065d24e9c87850753fbb7567ca2553', 'Gerrit', 'van de', 'Fabriek', 'employee', 'other', 12, 'Rietveldstraat 12', '1012AB', 'Amstelveen', '0031201234567', '0031612345678'),
(6, '', '9c1dd9c1e1684575051616be7814f15374bb73a9f7685ebe30969de4975976fac5fa449519fbc54ddcdc43fcf08eabb292523548349b5ce7fbb86b6fa30c1278', 'Super', '', 'User', 'super', 'other', 4, '', '', '', '', ''),
(7, 'Nick', 'b4f52d6d30415aa25e545b15bdd81a93785581c1fd226b0bfaf36043cf76d7d17f8f5dd43b74e5eb5137586fddfc36535b266bf27bd4eb90b798b033b78dfd09', 'Nick', '', 'Onbekend', 'employee', 'male', 4, 'Abc Straat 21', '1050AB', 'Amsterdam', '003167123456', '003167123456'),
(8, 'Tijme', 'd824e9f681ab297e7a1cb42260c6241d3a0dcb9ede2a1daa5c8b853b223d9274acf969d84a08d3912ffeb0d46a25325d4a3af862d0ce6a5d84f84033621c6537', 'Tijme', '', 'Daar', 'manager', 'male', 16, 'Blabla 12', '6592SD', 'Dordrecht', '0049201234466', '0049201234466'),
(9, 'Gerda', 'b9ac1d13d07c61ed07baec88b5aa7193635ca83d9a31b1bf2bd8cc8d96a915f6649bcb3644c3290a123620f5da55731348618ed293aa08463d94ec2ba136f534', 'Gerda', '', 'Balal', 'moderator', 'female', 14, 'Amsterdamseweg 20', '1132AD', 'Hoofddorp', '001221324567', '001221324567'),
(10, 'Jasper', 'e515b7af26b997c94375a4775f864a0119d99dc9c06f49c5893de471b5ad3328cfd00a49b36544ca966c340957a493b20c447adb08ed058186e079e861d63bfd', 'Jasper', '', 'Wkdlsflkwe', 'manager', 'male', 26, 'Hierdaar 3', '5465DF', 'Belgrado', '0012345678974', '0012345678974'),
(11, 'Lars', 'c2b659794a5c1d72ee02d95220fb2e3e0508b2b6f50def959bd9eee69a24ccfa5c4ddd2d059943eade0b9d6b4e346bc867c2dc14d48dd62fc32827f4aee04862', 'Lars', 'de', 'Vries', 'moderator', 'male', 18, 'Blabalbal 132', '1023DF', 'Amsterdam', '003145678943', '003145678943'),
(12, 'Ali', '02e03ddba23ecb5d872ff5784fe5383c44a171cd371087d0cf80c6cc3c1a182d68d22a75f62615bf39d4edcabffdd808c57f928e4fb82a49ddafc2485bd350ea', 'Ali', 'de', 'Geeuw', 'manager', 'male', 37, 'Amsterdamstraat 124', '8822VD', 'Groningen', '003198374252', '003198374252'),
(13, 'Derp', '38da7d98bc0e173b0767b5baa8880738e0b62603a7c2e08f729adac3f6f0796dd95d9d66925bbd3976410fc9104a894a20f7c633bf1be3b497ca2363976e8ede', 'Derp', '', '', 'employee', 'other', 17, 'Derp 1', '1000DP', 'Derp', '003121318232', '003121318232');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `customers`
--
ALTER TABLE `customers`
 ADD PRIMARY KEY (`id`), ADD KEY `insurer_id` (`insurer_id`), ADD KEY `insurer_id_2` (`insurer_id`);

--
-- Indexen voor tabel `insurers`
--
ALTER TABLE `insurers`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexen voor tabel `locations`
--
ALTER TABLE `locations`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexen voor tabel `log`
--
ALTER TABLE `log`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);

--
-- Indexen voor tabel `luggage`
--
ALTER TABLE `luggage`
 ADD PRIMARY KEY (`id`), ADD KEY `customer_id` (`customer_id`,`location_id`), ADD KEY `location_id` (`location_id`);

--
-- Indexen voor tabel `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`), ADD KEY `location_id` (`location_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `customers`
--
ALTER TABLE `customers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=502;
--
-- AUTO_INCREMENT voor een tabel `insurers`
--
ALTER TABLE `insurers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT voor een tabel `locations`
--
ALTER TABLE `locations`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT voor een tabel `log`
--
ALTER TABLE `log`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT voor een tabel `luggage`
--
ALTER TABLE `luggage`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3994;
--
-- AUTO_INCREMENT voor een tabel `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=508;
--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `customers`
--
ALTER TABLE `customers`
ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`insurer_id`) REFERENCES `insurers` (`id`) ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `log`
--
ALTER TABLE `log`
ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `luggage`
--
ALTER TABLE `luggage`
ADD CONSTRAINT `luggage_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `luggage_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `users`
--
ALTER TABLE `users`
ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
