/*
Navicat MySQL Data Transfer

Source Server         : 192.168.163.129
Source Server Version : 50518
Source Host           : 192.168.163.129:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2016-09-11 21:50:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_t`
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', '测试', 'sfasgfaf', '24');
INSERT INTO `user_t` VALUES ('2', '33543', '35435', '4654654');
