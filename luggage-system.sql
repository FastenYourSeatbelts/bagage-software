-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 18, 2014 at 11:54 AM
-- Server version: 5.5.40
-- PHP Version: 5.3.10-1ubuntu3.15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `insurer_id` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `middlename` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `gender` enum('male','female','other') NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `insurer_id` (`insurer_id`),
  KEY `insurer_id_2` (`insurer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `insurer_id`, `firstname`, `middlename`, `lastname`, `gender`, `address`, `postalcode`, `residence`, `email`, `telephone`, `mobile`) VALUES
(1, 1, 'Tijme', '', 'Gommers', 'male', '', '', '', '', '', ''),(2, 1, 'Jasper', '', 'Borgstede', 'male', '', '', '', '', '', ''),
(3,1,"Jordan","Zorita","Orr","male","P.O. Box 105, 6868 Neque Avenue","5032CB","Grand-Leez","dolor.quam@auguemalesuadamalesuada.co.uk","0031304748315","0031601815769"),(4,1,"Ingrid","Lamar","Klein","other","407-3103 Augue, Street","9837OG","Brussel","risus.Nulla@insodaleselit.com","0031200090700","0031669465616"),(5,1,"Julian","Lani","Frazier","male","8421 Eget, Rd.","1936VP","Itzehoe","inceptos.hymenaeos@Duiselementum.co.uk","0031242760109","0031602866945"),(6,1,"Teegan","Vielka","Mueller","other","P.O. Box 479, 620 Pharetra St.","2259TV","Minderhout","vitae.sodales.nisi@habitantmorbi.ca","0031209392901","0031650566695"),(7,1,"Venus","Dante","Mcbride","male","523-5505 Phasellus Road","1943SM","Saint-Prime","euismod@felis.ca","0031171973695","0031685038849"),(8,1,"Deborah","Odysseus","Patton","other","8922 Erat, Rd.","5057ER","Assiniboia","Duis@luctusut.ca","0031895584712","0031645230425"),(9,1,"Flynn","Timothy","Mejia","other","773-1526 Ipsum Rd.","2761RC","Ingraj Bazar","Pellentesque.habitant.morbi@auguescelerisquemollis.ca","0031651343513","0031661874114"),(10,1,"Mallory","Peter","Baxter","male","4594 Porttitor Road","9139XH","Harlingen","sem@Morbinon.ca","0031413619116","0031609225454"),(11,1,"Rogan","Mona","Castro","female","Ap #215-4222 In Rd.","7832TY","Cardigan","nunc.sed@libero.co.uk","0031374712468","0031614609118"),(12,1,"Gail","Jackson","Hensley","female","P.O. Box 563, 9912 Facilisis, Rd.","9329JY","Stonehaven","interdum.libero.dui@Maurisnulla.edu","0031622046889","0031699456602"),(13,1,"Shaeleigh","Dorothy","Herman","other","P.O. Box 137, 6370 Eu Road","7409GG","Santa Inês","ut.pellentesque@eusemPellentesque.com","0031669394359","0031640072016"),(14,1,"Gray","Oliver","Bowman","female","Ap #348-6845 Porta Av.","1546UW","Tay","Nullam.scelerisque@risusNunc.org","0031759477111","0031663701161"),(15,1,"Devin","Martin","Hopkins","female","7207 Mauris Av.","9721QC","Hope","Donec@augueeutellus.ca","0031766976282","0031661972287"),(16,1,"Declan","Jameson","Vazquez","other","Ap #116-8246 Ornare, Rd.","5926LN","Ilbono","ut@egettinciduntdui.edu","0031293733504","0031680585004"),(17,1,"Clementine","Brent","Kennedy","female","P.O. Box 929, 2751 Vel Street","1466VX","Pettoranello del Molise","lacus.Quisque@tempor.net","0031805328298","0031639539508"),(18,1,"Inga","Carter","Lewis","male","1122 Eu Road","4563KO","Morhet","non.nisi@necmalesuadaut.co.uk","0031925952709","0031630654455"),(19,1,"Demetria","Kamal","Haney","female","P.O. Box 417, 5151 Pulvinar Rd.","1500XT","Sennariolo","Sed.auctor.odio@tinciduntvehicularisus.edu","0031833995172","0031642479403"),(20,1,"Brennan","Jeanette","Chase","male","P.O. Box 581, 6255 Arcu Rd.","8689JE","Rio Verde","dis@risus.co.uk","0031909984855","0031626108620"),(21,1,"Victor","Yeo","Joyner","other","770-7828 Auctor St.","9830KJ","Malahide","magna.Duis@elit.com","0031482892187","0031649994065"),(22,1,"Rina","Rama","Perry","male","P.O. Box 749, 9623 Metus Av.","0383MD","Fort Worth","Curabitur.ut.odio@etmagnis.ca","0031912929716","0031660841106"),(23,1,"Jillian","Karleigh","Grant","other","P.O. Box 428, 225 Amet Rd.","6458IE","Oostkerke","et@eratvelpede.net","0031274711276","0031645890169"),(24,1,"Noah","Camille","Mayo","female","8524 Cras St.","4251EB","Tarzo","Aenean@uteros.net","0031139895953","0031676019320"),(25,1,"Jenette","Raymond","Vinson","male","678-1879 Mauris Rd.","7096KU","Sefro","erat.neque.non@malesuadamalesuada.net","0031947320546","0031635382055"),(26,1,"Deirdre","Kibo","Black","other","Ap #397-7395 Nisi. Rd.","4320IL","Orilla","metus@dui.com","0031933952771","0031692400363"),(27,1,"Kimberley","Josephine","Glover","female","8357 Donec Rd.","6784DZ","Saint-Oyen","Sed.congue@Nunc.com","0031564836271","0031643942115"),(28,1,"Guinevere","Shafira","Small","male","Ap #393-9280 Consectetuer Street","2491DI","Offida","consectetuer@Aliquam.org","0031144575248","0031696096041"),(29,1,"Leslie","Rosalyn","Mcclain","male","Ap #272-9377 Adipiscing, Street","0228MV","Piegaro","porttitor.tellus.non@orciDonecnibh.co.uk","0031259335258","0031663714041"),(30,1,"Aretha","Hayley","Lyons","other","3866 Proin Ave","0119VU","Northallerton","Nulla@sitamet.edu","0031117523827","0031625585843"),(31,1,"Allegra","Thane","Boyer","other","Ap #480-449 Dolor. Rd.","4384IY","Miraj","Fusce.mi.lorem@semelitpharetra.ca","0031459156892","0031614077750"),(32,1,"Chanda","Remedios","Hines","female","2243 Eros Ave","4587TS","Soissons","Donec@egetmetus.com","0031198514608","0031689003520"),(33,1,"Dean","Oren","Hester","female","P.O. Box 568, 1653 Tempus Av.","3699XR","Waitara","sit.amet@hendrerit.edu","0031555985472","0031602767703"),(34,1,"Malcolm","Prescott","Woods","male","P.O. Box 502, 8614 Sed Street","0580AQ","Ville de Maniwaki","massa.Vestibulum@mattisornare.org","0031462953466","0031607990753"),(35,1,"Kareem","Patrick","Schroeder","other","6957 Et, Avenue","1969FI","Mulheim","a.ultricies@Suspendissenonleo.co.uk","0031297232935","0031601980390"),(36,1,"Brent","Yetta","Robinson","male","816 Phasellus Avenue","7313ZF","Port Blair","risus.at.fringilla@famesac.net","0031274444039","0031638142635"),(37,1,"Ryan","Asher","Patel","other","8560 Purus. Rd.","0755DM","Notre-Dame-du-Nord","consequat@quamelementum.co.uk","0031833981269","0031639316633"),(38,1,"Quinn","Kitra","Diaz","male","P.O. Box 482, 2656 Luctus Rd.","7105IX","Innsbruck","sem.egestas@aliquetlobortisnisi.co.uk","0031442835029","0031630911132"),(39,1,"Jordan","Ora","Petersen","male","924-9815 Quis Road","1573JO","Great Yarmouth","mollis.nec@Maurisvel.org","0031468366295","0031660040805"),(40,1,"Carissa","Kylee","Bryant","female","P.O. Box 464, 3601 In Rd.","5260WC","Loy","Nullam@risus.net","0031264643997","0031650071744"),(41,1,"Justin","Fritz","Cherry","other","Ap #232-1010 Tempus Street","2615CW","Dusseldorf","molestie@lectuspede.net","0031865958025","0031661289522"),(42,1,"Nissim","Zia","Sweeney","male","121-9005 Est. Rd.","1778KD","Arsiè","morbi@orciUtsagittis.com","0031156347789","0031651928234"),(43,1,"Linda","Yeo","Adkins","male","P.O. Box 730, 1620 Urna, Street","6819TF","Edmundston","orci.Donec.nibh@aliquetmagna.co.uk","0031130763717","0031662509896"),(44,1,"Carter","Travis","Fleming","female","6388 Commodo Road","1101AV","Grand-Leez","fringilla.ornare@anteVivamus.ca","0031670437398","0031617891392"),(45,1,"Scott","Marsden","Clemons","other","P.O. Box 408, 8178 Dolor Road","0702EJ","Lier","eu@Nulla.edu","0031406411570","0031606959202"),(46,1,"Tanner","Florence","Everett","male","Ap #314-6519 Sapien Rd.","9762YQ","Alandur","malesuada@eleifendnecmalesuada.org","0031831173339","0031641470520"),(47,1,"Hope","MacKenzie","Nichols","male","P.O. Box 619, 2536 Iaculis Ave","3361VC","Ulm","sed@Integereu.co.uk","0031720613337","0031695149487"),(48,1,"Colby","Oren","Morse","male","2407 Neque. St.","1245PW","Dillingen","vitae@Vivamuseuismodurna.co.uk","0031531497022","0031683840532"),(49,1,"Ariel","Tamekah","Cobb","male","P.O. Box 938, 4188 Tellus Ave","4262UR","Whitehorse","quis.tristique@eratvolutpat.co.uk","0031606993004","0031601628225"),(50,1,"Cullen","Emi","Odom","female","3223 Orci Ave","2952EL","Montigny-le-Tilleul","ipsum@diam.ca","0031928275317","0031658056070"),(51,1,"Fay","Willa","Moran","other","1349 Nec Ave","1778UD","Colico","imperdiet@rhoncus.org","0031365595614","0031697692429"),(52,1,"Harrison","Zephania","Turner","male","7470 Tortor Road","9145JM","Campofelice di Fitalia","Cras@orcitinciduntadipiscing.ca","0031296255890","0031698225491"),(53,1,"James","Baker","Ewing","other","P.O. Box 785, 4852 Tristique Street","0818LH","Pontey","neque.vitae@lectussit.net","0031427160292","0031678547561"),(54,1,"Keegan","Sylvia","Blackburn","female","Ap #814-6472 Lectus Avenue","0157YV","Galmaarden","tellus.eu.augue@Donecatarcu.com","0031941590119","0031622801845"),(55,1,"Magee","Cynthia","Ford","male","841-2589 Quis Rd.","0151TS","Bad Oldesloe","elit.Nulla@aultricies.co.uk","0031123996581","0031606102486"),(56,1,"Evan","Hedda","Buchanan","female","793-9544 Morbi Av.","9378NA","Zellik","odio@dictummagna.ca","0031180024160","0031664567588"),(57,1,"Leila","Emerson","Monroe","other","P.O. Box 655, 6229 Orci, Av.","0366OD","San José","et@ametultriciessem.co.uk","0031305682549","0031645785306"),(58,1,"Griffin","Camden","Castro","other","P.O. Box 413, 5293 Vitae, Ave","1630EB","Manfredonia","nisl.Quisque.fringilla@parturientmontesnascetur.org","0031900349402","0031604944995"),(59,1,"Laura","Nelle","Henderson","other","P.O. Box 750, 816 Non, St.","3354VL","Sluis","dui@sitametrisus.net","0031229228258","0031649508816"),(60,1,"Pandora","Odysseus","Zimmerman","male","840-4931 Est Av.","5127CR","East Linton","ut@auctorvelit.ca","0031468347057","0031658402393"),(61,1,"Kelsey","Asher","Coffey","female","Ap #706-8307 Metus. Street","7879EC","Strasbourg","dolor@idliberoDonec.org","0031933823807","0031681143676"),(62,1,"Colt","Ross","Green","male","929-8104 Gravida. St.","3902HA","Giurdignano","nec.imperdiet@dolorquamelementum.com","0031799338467","0031639400116"),(63,1,"Pamela","Kirestin","Raymond","female","753-1653 Arcu. St.","0543FO","Ruisbroek","dictum.placerat.augue@tellusjustosit.edu","0031404639815","0031627858601"),(64,1,"Joseph","Fulton","Montgomery","male","2920 Mauris. Road","6876JG","Strue","urna.Vivamus.molestie@adlitora.net","0031643109012","0031604535144"),(65,1,"Abraham","Blaine","Mckenzie","other","Ap #785-8668 Duis Street","8969AU","Fraser Lake","eget.magna.Suspendisse@natoquepenatibuset.org","0031689417602","0031637079296"),(66,1,"Jordan","Quamar","Berry","male","3103 Vel Rd.","8390ZE","Ramsey","nisi@loremluctusut.org","0031191777752","0031634977234"),(67,1,"Keith","Fulton","Graham","female","Ap #147-5076 Vitae St.","5692XS","Coleville Lake","fringilla@Nullainterdum.com","0031294837296","0031637482318"),(68,1,"Clayton","Jana","Castaneda","female","P.O. Box 306, 9598 Nulla. Av.","3829KZ","Hulst","sed@aliquameuaccumsan.com","0031101401880","0031642441128"),(69,1,"Scarlett","Clementine","Vinson","other","8799 Quis Avenue","8892BU","Loy","auctor.velit@ridiculusmusAenean.co.uk","0031119965963","0031638607039"),(70,1,"Echo","Claire","Holden","male","401-5225 Cras St.","2681VM","Grandrieu","tellus@elementumategestas.edu","0031792037875","0031652552846"),(71,1,"Piper","Ora","Strickland","female","Ap #458-3201 Lacus. Avenue","2495JO","Orangeville","Vestibulum.ante.ipsum@magnaDuis.com","0031792793136","0031629725894"),(72,1,"Gil","Kelly","Gardner","female","P.O. Box 384, 3531 Nunc Road","7370HQ","Madison","venenatis@elitAliquam.com","0031971394580","0031627776087"),(73,1,"Shea","Kitra","Fleming","male","789-9146 Tellus. Ave","8841GG","Cannock","amet.ornare@velpede.edu","0031700317704","0031610167507"),(74,1,"Beau","Ignatius","Stanton","female","8089 Accumsan Street","2968AC","Leers-et-Fosteau","consectetuer.cursus@scelerisquenequeNullam.net","0031959896274","0031611422806"),(75,1,"Sonya","Emi","Frank","female","138-6324 Lobortis St.","2985IU","Portree","semper.dui@semper.edu","0031547545920","0031679551755"),(76,1,"Asher","Gannon","Rogers","male","P.O. Box 691, 9039 Sollicitudin Rd.","6058DQ","Rueglio","Aliquam.tincidunt.nunc@dui.co.uk","0031117449886","0031653602824"),(77,1,"Catherine","Alana","Osborn","female","P.O. Box 964, 6674 Vel Avenue","0589VN","Leduc","pellentesque@maurisut.ca","0031807257103","0031623622868"),(78,1,"Kylie","Amelia","Ball","other","8209 Nulla St.","6879AK","Vreren","accumsan@ante.org","0031982038524","0031677707528"),(79,1,"Hu","Kevyn","Hartman","male","538-387 Augue St.","2189MR","Brecon","tristique.pharetra.Quisque@lectus.org","0031930470057","0031648865311"),(80,1,"Tatyana","Halla","Rowland","female","675-7075 Ligula Rd.","5223OY","Melville","blandit@Aliquam.co.uk","0031285770068","0031655661714"),(81,1,"Yoko","Moses","Dale","other","P.O. Box 348, 9524 Arcu. Road","2958RN","Saint-Pierre","sed.sapien@tempor.co.uk","0031748492947","0031604560551"),(82,1,"Ezra","Keith","Langley","other","706-7832 Ante Road","0687AW","Leke","Nulla.tempor@velitPellentesqueultricies.com","0031671352639","0031614285639"),(83,1,"Wynne","Buckminster","Lowe","male","6863 Natoque Avenue","5675DU","Surrey","rutrum.eu.ultrices@sem.net","0031801603158","0031634145751"),(84,1,"Evangeline","Sandra","Mccullough","female","Ap #671-8038 Dolor. St.","5008HZ","Riviure","placerat.velit@Crasloremlorem.com","0031770765061","0031646017406"),(85,1,"Bruce","Pamela","Avery","male","Ap #556-4169 Dolor Road","0173QT","High Level","Nam@anteMaecenasmi.net","0031400680200","0031623757863"),(86,1,"Jesse","Amber","Thompson","female","2312 Neque. St.","4416OW","Aylesbury","eu.elit@vel.edu","0031177555924","0031699820589"),(87,1,"Edward","Sheila","Macias","other","272-8700 Libero. St.","1256HE","Oamaru","adipiscing.enim@aliquetmetusurna.com","0031939699988","0031675061821"),(88,1,"Sonya","Shellie","Hooper","other","181-178 Pharetra. Rd.","3765FN","Moxhe","neque@diam.ca","0031615867672","0031628160420"),(89,1,"Ryder","Xaviera","Marks","male","9457 Nibh St.","5261BW","Vorselaar","Mauris.vel@hendreritconsectetuer.edu","0031898418333","0031667277703"),(90,1,"Louis","Chester","Mercer","other","6570 Fringilla, Av.","0689MA","Vandoies/Vintl","diam.lorem@Inat.net","0031440306930","0031659671455"),(91,1,"Nyssa","Edward","Elliott","female","P.O. Box 450, 6535 Laoreet St.","7480GG","Macon","vel@elitpharetra.net","0031515820641","0031645493941"),(92,1,"Nolan","Quinn","Mack","female","P.O. Box 518, 4292 Orci St.","9549GC","Mechelen-aan-de-Maas","odio@Duisa.edu","0031176747969","0031682332410"),(93,1,"Heather","Doris","Rollins","female","P.O. Box 136, 7389 Dictum St.","2335FC","North Cowichan","sapien.molestie.orci@scelerisquelorem.org","0031932068092","0031602231595"),(94,1,"Igor","Cain","Huff","other","315-1660 Penatibus Road","4016NF","Jabbeke","auctor.velit.eget@dictum.edu","0031153646356","0031628330854"),(95,1,"Bertha","Zenaida","Miller","other","Ap #318-6273 Non St.","2082DH","Viddalba","dolor@parturientmontesnascetur.edu","0031386326303","0031644585337"),(96,1,"Libby","Abraham","Howard","male","P.O. Box 343, 3521 Risus St.","9044CI","Krems an der Donau","tellus@magnaNam.ca","0031718664792","0031645092173"),(97,1,"Brianna","Fuller","Parks","female","8964 Venenatis Street","2068CI","Reading","imperdiet.non.vestibulum@disparturient.com","0031242690740","0031684172026"),(98,1,"September","Nadine","Johnson","other","Ap #445-536 Mollis Ave","8101RA","Birmingham","at.sem.molestie@egetnisi.ca","0031569404502","0031609649371"),(99,1,"Maile","Uriah","Fields","female","529-7672 Enim. Rd.","3374EZ","Faizabad","eu@duinecurna.net","0031649845375","0031685761280"),(100,1,"Tamara","Sharon","Zamora","male","Ap #912-3396 Ridiculus Avenue","8053AN","Wiekevorst","ipsum.nunc@uterat.com","0031306528246","0031617620344"),(101,1,"Willow","Xaviera","Pace","male","9302 Nulla Avenue","6652IP","Eastbourne","non.bibendum@dolor.edu","0031575187561","0031665361640"),(102,1,"Lance","Hadley","Hall","other","7529 Facilisis. Rd.","4748SC","Buckie","ipsum.ac.mi@libero.com","0031818260402","0031614060499");

-- --------------------------------------------------------

--
-- Table structure for table `insurers`
--

CREATE TABLE IF NOT EXISTS `insurers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `insurers`
--

INSERT INTO `insurers` (`id`, `name`) VALUES
(1, 'SNS');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('info','warning','error') NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `luggage`
--

CREATE TABLE IF NOT EXISTS `luggage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `status` enum('lost','found','resolved') NOT NULL DEFAULT 'lost',
  `tags` varchar(500) NOT NULL,
  `notes` longtext NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`,`location_id`),
  KEY `location_id` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `middlename` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `role` enum('manager','employee','moderator','') NOT NULL DEFAULT 'employee',
  `gender` enum('male','female','other') NOT NULL,
  `address` varchar(255) NOT NULL,
  `postalcode` varchar(255) NOT NULL,
  `residence` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `middlename`, `lastname`, `role`, `gender`, `address`, `postalcode`, `residence`, `telephone`, `mobile`) VALUES
(1, 'admin', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Tijme', 'de', 'Admin', 'moderator', 'male', '', '', '', '', ''),
(2, 'moderator', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Moderator', '', 'Nick', 'moderator', 'male', '', '', '', '', ''),
(3, 'manager', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Manager', '', '', 'manager', 'male', '', '', '', '', ''),
(4, 'employee', '7a8d0e81b9e73f3e39cbaccfac16097a8c88e6bddf068160946115bfc31ff59e81533b292f5cafd3be42f6980aced7b8791f0738a91717f62f3f542a535c2e51', 'Employee', '', '', 'employee', 'male', '', '', '', '', '');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`insurer_id`) REFERENCES `insurers` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `luggage`
--
ALTER TABLE `luggage`
  ADD CONSTRAINT `luggage_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `luggage_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
