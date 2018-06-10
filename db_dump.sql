/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;



CREATE TABLE IF NOT EXISTS `items` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `parameterized` tinyint(4) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`, `name`, `parameterized`, `description`) VALUES
	('1', 'dress', 1, 'blue dress'),
	('2', 'shoes', 1, 'good shoes'),
	('3', 'Hat', 1, 'Red Hat'),
	('4', 'Dreadlocks', 1, 'Windows microsoft');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;


CREATE TABLE IF NOT EXISTS `orders` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `user` varchar(50) NOT NULL,
  `addr` varchar(250) NOT NULL,
  `card` varchar(40) NOT NULL,
  `created` datetime NOT NULL
) ;

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `user`, `addr`, `card`, `created`) VALUES
	(2, 'user', 'some street', '123123', '2015-06-03 14:37:48');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


CREATE TABLE IF NOT EXISTS `order_items` (
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`item_id`)
);

/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` (`order_id`, `item_id`, `count`, `price`) VALUES
	(2, 2, 1, 0),
	(2, 3, 1, 0),
	(2, 4, 1, 0);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;


CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `advertising` tinyint(4) NOT NULL,
  PRIMARY KEY (`login`)
) ;

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`login`, `password`, `name`, `email`, `advertising`) VALUES
	('admin', 'admin', 'Administrator', 'admin@some.any', 0),
	('user', 'user', 'Any user', 'user@company.com', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
