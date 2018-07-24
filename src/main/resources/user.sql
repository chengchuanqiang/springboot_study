/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-07-24 14:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增|程传强|2018-07-20',
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名称，唯一|程传强|2018-07-20',
  `age` int(11) DEFAULT '0' COMMENT '年龄|程传强|2018-07-20',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间|程传强|2018-07-20',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间|程传强|2018-07-20',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `update_time` (`update_time`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'ccq', '22', '2018-07-20 16:27:36', '2018-07-20 16:27:36');
INSERT INTO `user` VALUES ('3', 'ccq', '22', '2018-07-20 16:28:46', '2018-07-20 16:28:46');
INSERT INTO `user` VALUES ('4', 'ccq', '22', '2018-07-20 16:28:54', '2018-07-20 16:28:54');
INSERT INTO `user` VALUES ('5', 'ccq', '22', '2018-07-20 17:28:09', '2018-07-20 17:28:09');
