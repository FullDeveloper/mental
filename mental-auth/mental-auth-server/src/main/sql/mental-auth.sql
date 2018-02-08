/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.20-log : Database - mental_auth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mental_auth` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mental_auth`;

/*Table structure for table `auth_client` */

DROP TABLE IF EXISTS `auth_client`;

CREATE TABLE `auth_client` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '服务编号',
  `secret` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '服务密钥',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '服务名称',
  `locked` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否锁定',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  `crt_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `crt_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户名称',
  `crt_host` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建用户主机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `auth_client` */

insert  into `auth_client`(`id`,`code`,`secret`,`name`,`locked`,`description`,`crt_time`,`crt_user`,`crt_name`,`crt_host`) values (1,'mental-gateway','123456','mental-gateway','0','mental_gateway','2018-02-06 11:29:03',NULL,NULL,NULL),(2,'mental-admin','123456','mental-admin','0','mental_admin','2018-02-06 11:33:51',NULL,NULL,NULL);

/*Table structure for table `auth_client_service` */

DROP TABLE IF EXISTS `auth_client_service`;

CREATE TABLE `auth_client_service` (
  `id` bigint(20) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL COMMENT '客户端编号',
  `service_id` bigint(20) DEFAULT NULL COMMENT '服务编号',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  `crt_user_id` bigint(20) DEFAULT NULL COMMENT '创建人编号',
  `crt_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `crt_host` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人主机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `auth_client_service` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
