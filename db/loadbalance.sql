-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 08, 2018 at 11:16 AM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `loadbalance`
--

-- --------------------------------------------------------

--
-- Table structure for table `authentication`
--

CREATE TABLE IF NOT EXISTS `authentication` (
  `uname` varchar(50) NOT NULL,
  `img` longblob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authentication`
--


-- --------------------------------------------------------

--
-- Table structure for table `service_cap`
--

CREATE TABLE IF NOT EXISTS `service_cap` (
  `fog_id` int(10) NOT NULL AUTO_INCREMENT,
  `agentName` varchar(25) NOT NULL,
  `Services` varchar(25) NOT NULL,
  `Key` varchar(10) NOT NULL,
  PRIMARY KEY (`fog_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=210 ;

--
-- Dumping data for table `service_cap`
--

INSERT INTO `service_cap` (`fog_id`, `agentName`, `Services`, `Key`) VALUES
(155, 'new6', 'Textmining', '5289'),
(154, 'new6', 'Bigdata', '5289'),
(153, 'new6', 'Webmining Services', '5289'),
(152, 'new6', 'Mapreduce', '5289'),
(151, 'new6', 'Datamining', '5289'),
(150, 'sen', 'Textmining', '6016'),
(149, 'sen', 'Bigdata', '6016'),
(148, 'sen', 'Webmining Services', '6016'),
(147, 'sen', 'Mapreduce', '6016'),
(146, 'sen', 'Datamining', '6016'),
(145, '', 'Textmining', '0'),
(144, '', 'Bigdata', '0'),
(143, '', 'Webmining Services', '0'),
(142, '', 'Mapreduce', '0'),
(141, '', 'Datamining', '0'),
(140, '', 'Textmining', '0'),
(139, '', 'Bigdata', '0'),
(138, '', 'Webmining Services', '0'),
(137, '', 'Mapreduce', '0'),
(136, '', 'Datamining', '0'),
(135, '', 'Bigdata', '0'),
(134, '', 'Webmining Services', '0'),
(133, 'sen', 'Bigdata', '6016'),
(132, 'sen', 'Webmining Services', '6016'),
(131, 'sen', 'Mapreduce', '6016'),
(130, 'check', 'Textmining', '1269'),
(129, 'check', 'Bigdata', '1269'),
(128, 'check', 'Webmining Services', '1269'),
(127, 'check', 'Mapreduce', '1269'),
(126, 'check', 'Textmining', '1269'),
(125, 'check', 'Bigdata', '1269'),
(124, 'check', 'Webmining Services', '1269'),
(123, 'check', 'Mapreduce', '1269'),
(122, 'check', 'Bigdata', '1269'),
(121, 'check', 'Webmining Services', '1269'),
(120, 'check', 'Mapreduce', '1269'),
(119, 'check', 'Datamining', '1269'),
(118, 'check', 'Textmining', '1269'),
(117, 'check', 'Bigdata', '1269'),
(116, 'check', 'Webmining Services', '1269'),
(115, 'check', 'Mapreduce', '1269'),
(114, 'xyz', 'Bigdata', '3365'),
(113, 'xyz', 'Webmining Services', '3365'),
(112, 'xyz', 'Mapreduce', '3365'),
(111, 'xyz', 'Datamining', '3365'),
(110, 'abc', 'Textmining', '9162'),
(109, 'abc', 'Bigdata', '9162'),
(108, 'abc', 'Webmining Services', '9162'),
(107, 'abc', 'Mapreduce', '9162'),
(106, 'abc', 'Datamining', '9162'),
(93, 'main', 'Webmining Services', '4007'),
(92, 'main', 'Mapreduce', '4007'),
(91, 'main', 'Datamining', '4007'),
(86, 'main', 'Datamining', '4007'),
(87, 'main', 'Mapreduce', '4007'),
(88, 'main', 'Webmining Services', '4007'),
(89, 'main', 'Bigdata', '4007'),
(90, 'main', 'Textmining', '4007'),
(94, 'main', 'Bigdata', '4007'),
(95, 'main', 'Textmining', '4007'),
(96, 'agent1', 'Datamining', '2064'),
(97, 'agent1', 'Mapreduce', '2064'),
(98, 'agent1', 'Webmining Services', '2064'),
(99, 'agent1', 'Bigdata', '2064'),
(100, 'agent1', 'Textmining', '2064'),
(101, 'agentname', 'Datamining', '2447'),
(102, 'agentname', 'Mapreduce', '2447'),
(103, 'agentname', 'Webmining Services', '2447'),
(104, 'agentname', 'Bigdata', '2447'),
(105, 'agentname', 'Textmining', '2447'),
(156, 'new6', 'Datamining', '5289'),
(157, 'new6', 'Mapreduce', '5289'),
(158, 'new6', 'Webmining Services', '5289'),
(159, 'new6', 'Bigdata', '5289'),
(160, 'new6', 'Textmining', '5289'),
(161, 'new7', 'Datamining', '4812'),
(162, 'new7', 'Mapreduce', '4812'),
(163, 'new7', 'Webmining Services', '4812'),
(164, 'new7', 'Bigdata', '4812'),
(165, 'new7', 'Textmining', '4812'),
(166, 'new7', 'Datamining', '4812'),
(167, 'new7', 'Mapreduce', '4812'),
(168, 'new7', 'Webmining Services', '4812'),
(169, 'new7', 'Bigdata', '4812'),
(170, 'new7', 'Textmining', '4812'),
(171, 'new7', 'Datamining', '4812'),
(172, 'new7', 'Mapreduce', '4812'),
(173, 'new7', 'Webmining Services', '4812'),
(174, 'new7', 'Bigdata', '4812'),
(175, 'new7', 'Textmining', '4812'),
(176, 'new8', 'Datamining', '447'),
(177, 'new8', 'Mapreduce', '447'),
(178, 'new8', 'Webmining Services', '447'),
(179, 'new8', 'Bigdata', '447'),
(180, 'new8', 'Textmining', '447'),
(181, 'new8', 'Datamining', '447'),
(182, 'new8', 'Mapreduce', '447'),
(183, 'new8', 'Webmining Services', '447'),
(184, 'new8', 'Textmining', '447'),
(185, 'new8', 'Datamining', '447'),
(186, 'new8', 'Mapreduce', '447'),
(187, 'new8', 'Webmining Services', '447'),
(188, 'new8', 'Bigdata', '447'),
(189, 'new8', 'Textmining', '447'),
(190, 'new8', 'Datamining', '447'),
(191, 'new8', 'Mapreduce', '447'),
(192, 'new8', 'Webmining Services', '447'),
(193, 'new8', 'Bigdata', '447'),
(194, 'new8', 'Textmining', '447'),
(195, 'new8', 'Datamining', '447'),
(196, 'new8', 'Mapreduce', '447'),
(197, 'new8', 'Webmining Services', '447'),
(198, 'new8', 'Bigdata', '447'),
(199, 'new8', 'Textmining', '447'),
(200, 'new9', 'Datamining', '2323'),
(201, 'new9', 'Mapreduce', '2323'),
(202, 'new9', 'Webmining Services', '2323'),
(203, 'new9', 'Bigdata', '2323'),
(204, 'new9', 'Textmining', '2323'),
(205, 'fog1', 'Datamining', '3265'),
(206, 'fog1', 'Mapreduce', '3265'),
(207, 'fog1', 'Webmining Services', '3265'),
(208, 'fog1', 'Bigdata', '3265'),
(209, 'fog1', 'Textmining', '3265');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `key` int(5) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `uname`, `pwd`, `key`) VALUES
(6, 'sen', '123456', 6016),
(5, 'jack', '123456', 6410),
(4, 'tony', '123456', 8552),
(7, 'ram', '123456', 3354),
(8, 'achilis', '123456', 244),
(9, 'sfsdf', '123456', 3225),
(10, 'fdssfs', '123456', 9865),
(11, 'sdfsdf', '123456', 9794),
(12, 'sdfsd', 'dfssdfs', 7445),
(13, 'sdfs', '123124', 5201),
(14, 'sdfsf', '123456', 3381),
(15, 'sdfsdf', '1234567', 5399),
(16, 'sdadas', '1234567', 7923),
(17, 'fsfdfsdfsd', '12345678', 1173),
(18, 'fdgdg', '123456', 6775),
(19, 'sdfsdf', '21312414', 9999),
(20, 'pelus', '123456', 4056),
(21, 'abc', '654321', 2939),
(22, 'xyz', '123456', 4685),
(23, 'jkl', '123456', 9351),
(24, 'cloudanalyst', '123456', 6635),
(25, 'cloud', '123456', 7713),
(29, 'usercheck', '123456', 4940),
(28, 'usertest', '123456', 9556),
(30, 'abc', '123456', 9162),
(31, 'xyz', '123456', 3365),
(32, 'check', '123456', 1269),
(33, 'dfsdf', '123456', 3825),
(34, 'test', '123456', 8011),
(35, 'cloud1', '123456', 2661),
(36, 'cloud2', '123456', 5683),
(37, 'cloud1', '123456', 929),
(38, 'cloud3', '123456', 1051),
(39, 'new6', '123456', 5289),
(40, 'new7', '123456', 4812),
(41, 'new8', '123456', 447),
(42, 'new9', '123456', 2323),
(43, 'fog1', '123456', 3265);
