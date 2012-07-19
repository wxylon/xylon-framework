/*
MySQL Data Transfer
Source Host: localhost
Source Database: xylon-test
Target Host: localhost
Target Database: xylon-test
Date: 2012/7/4 11:49:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for test
-- ----------------------------
CREATE TABLE `test` (
  `NAME` varchar(20) NOT NULL,
  `HOBBY` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `test` VALUES ('Adam', 'basketball');
INSERT INTO `test` VALUES ('Bill', 'basketball');
INSERT INTO `test` VALUES ('Bill', 'football');
INSERT INTO `test` VALUES ('Cyper', 'basketball');
INSERT INTO `test` VALUES ('Cyper', 'badminton');
INSERT INTO `test` VALUES ('David', 'basketball');
INSERT INTO `test` VALUES ('David', 'badminton');
INSERT INTO `test` VALUES ('David', 'table tennis');


-- ##############################
-- sources： http://www.iteye.com/topic/1122917
-- ##############################
