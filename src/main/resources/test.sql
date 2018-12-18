/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-07-30 19:16:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增|程传强|2018-07-30',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '目录名或表名等|程传强|2018-07-30',
  `type` tinyint(1) NOT NULL COMMENT '类型@1:文件夹@2:表|程传强|2018-07-30',
  `parent_id` bigint(11) DEFAULT '0' COMMENT '父节点id|程传强|2018-07-30',
  `weight` int(11) DEFAULT '0' COMMENT '权重，值越大排名越靠后|程传强|2018-07-30',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间|程传强|2018-07-20',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间|程传强|2018-07-20',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `update_time` (`update_time`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES ('1', '文件夹1', '1', '0', '1', '2018-07-30 18:14:59', '2018-07-30 18:14:59');
INSERT INTO `tree` VALUES ('2', '文件夹2', '1', '0', '2', '2018-07-30 18:15:14', '2018-07-30 18:15:14');
INSERT INTO `tree` VALUES ('3', '文件夹3', '1', '0', '3', '2018-07-30 18:15:24', '2018-07-30 18:15:24');
INSERT INTO `tree` VALUES ('4', '文件夹4', '1', '0', '4', '2018-07-30 18:15:37', '2018-07-30 18:15:37');
INSERT INTO `tree` VALUES ('5', '文件11', '2', '1', '1', '2018-07-30 18:15:52', '2018-07-30 18:15:52');
INSERT INTO `tree` VALUES ('6', '文件12', '2', '1', '2', '2018-07-30 18:16:29', '2018-07-30 18:16:29');
INSERT INTO `tree` VALUES ('7', '文件22', '2', '2', '1', '2018-07-30 18:16:43', '2018-07-30 18:16:43');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'ccq', '22', '2018-07-20 16:27:36', '2018-07-20 16:27:36');
INSERT INTO `user` VALUES ('3', 'ccq', '22', '2018-07-20 16:28:46', '2018-07-20 16:28:46');
INSERT INTO `user` VALUES ('4', 'ccq', '22', '2018-07-20 16:28:54', '2018-07-20 16:28:54');
INSERT INTO `user` VALUES ('5', 'ccq', '22', '2018-07-20 17:28:09', '2018-07-20 17:28:09');
INSERT INTO `user` VALUES ('6', 'ccq', '22', '2018-07-25 19:45:07', '2018-07-25 19:45:07');
INSERT INTO `user` VALUES ('7', 'ccq', '22', '2018-07-25 19:45:14', '2018-07-25 19:45:14');
INSERT INTO `user` VALUES ('8', 'ccq', '22', '2018-07-25 19:45:17', '2018-07-25 19:45:17');
INSERT INTO `user` VALUES ('9', 'ccq', '22', '2018-07-25 19:48:24', '2018-07-25 19:48:24');
INSERT INTO `user` VALUES ('10', 'ccq', '22', '2018-07-26 10:08:26', '2018-07-26 10:08:26');
