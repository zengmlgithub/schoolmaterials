/*
SQLyog v10.2 
MySQL - 5.1.73-community : Database - supermarket
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supermarket` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `supermarket`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_no` varchar(50) DEFAULT NULL,
  `goods_name` varchar(225) DEFAULT NULL,
  `goods_type_id` int(11) DEFAULT '0',
  `goods_price` double DEFAULT '0',
  `goods_count` int(11) DEFAULT '0',
  `goods_desc` text,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

/*Table structure for table `goods_type` */

DROP TABLE IF EXISTS `goods_type`;

CREATE TABLE `goods_type` (
  `goods_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_type_name` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `goods_type` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `orders_count` int(11) DEFAULT NULL,
  `orders_admin` varchar(50) DEFAULT NULL,
  `orders_date` date DEFAULT NULL,
  `goods_count` date DEFAULT 0,
  PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `sale` */

DROP TABLE IF EXISTS `sale`;

CREATE TABLE `sale`  (
  `sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT 0,
  `sale_count` int(11) NULL DEFAULT NULL,
  `sale_admin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_date` date NULL DEFAULT NULL,
  `sale_receiver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_tel` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_count` date DEFAULT 0,
  PRIMARY KEY (`sale_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


/*Data for the table `sale` */


/*Table structure for table `user` */


-- ----------------------------
-- Table structure for user
-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` int(11) NULL DEFAULT 0 COMMENT '1：男 0：女',
  `user_age` int(11) NULL DEFAULT NULL,
  `user_mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reg_date` datetime(0) NULL DEFAULT NULL,
  `user_type` int(11) NULL DEFAULT 1 COMMENT '1：超市用户 2：系统管理员 ',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

insert into `user` (user_name,user_pass,real_name,user_sex,user_age,user_mail,reg_date,user_type) values('admin','9fc0a0555e4f857f874da29c9790695cd110cb4e','admin',1,18,'admin@sany.com.cn','2016-10-06 23:08:39',2);


