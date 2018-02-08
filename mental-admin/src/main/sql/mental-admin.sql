/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.20-log : Database - mental_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mental_admin` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mental_admin`;

/*Table structure for table `base_gateway_log` */

DROP TABLE IF EXISTS `base_gateway_log`;

CREATE TABLE `base_gateway_log` (
  `id` bigint(20) NOT NULL,
  `uri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求路径',
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求方式',
  `menu` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `crt_time` datetime DEFAULT NULL COMMENT '请求时间',
  `crt_user` bigint(20) DEFAULT NULL COMMENT '请求人编号',
  `crt_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '请求人名称',
  `crt_host` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '请求人主机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_gateway_log` */

/*Table structure for table `base_organization` */

DROP TABLE IF EXISTS `base_organization`;

CREATE TABLE `base_organization` (
  `id` bigint(20) NOT NULL,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_organization` */

/*Table structure for table `base_permission` */

DROP TABLE IF EXISTS `base_permission`;

CREATE TABLE `base_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `type` int(11) DEFAULT NULL COMMENT '权限类型;1:目录,2:菜单,3:按钮',
  `permission_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限值',
  `uri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '状态;0:禁止,1正常',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_permission` */

/*Table structure for table `base_role` */

DROP TABLE IF EXISTS `base_role`;

CREATE TABLE `base_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色代码',
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色类型',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `display` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否显示',
  `delete_status` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '删除状态',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_role` */

/*Table structure for table `base_role_permission` */

DROP TABLE IF EXISTS `base_role_permission`;

CREATE TABLE `base_role_permission` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL COMMENT '资源编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_role_permission` */

/*Table structure for table `base_user` */

DROP TABLE IF EXISTS `base_user`;

CREATE TABLE `base_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '加密盐',
  `real_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `phone` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `email` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `locked` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '是否锁定',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_user` */

/*Table structure for table `base_user_permission` */

DROP TABLE IF EXISTS `base_user_permission`;

CREATE TABLE `base_user_permission` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `permission_Id` bigint(20) DEFAULT NULL COMMENT '权限编号',
  `type` int(11) DEFAULT NULL COMMENT '类型(-1:减权限,1加权限)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_user_permission` */

/*Table structure for table `base_user_role` */

DROP TABLE IF EXISTS `base_user_role`;

CREATE TABLE `base_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `base_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
