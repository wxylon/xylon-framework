/*
MySQL Data Transfer
Source Host: localhost
Source Database: xylon-test
Target Host: localhost
Target Database: xylon-test
Date: 2012/7/4 11:46:56
*/
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t
-- ----------------------------
CREATE TABLE `t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(200) DEFAULT NULL,
  `smoney` decimal(10,2) unsigned zerofill DEFAULT '00000000.00',
  `sprovince` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t` VALUES ('1', '张三', '00000555.32', 'A');
INSERT INTO `t` VALUES ('2', '李四', '00006232.00', 'B');
INSERT INTO `t` VALUES ('3', '王五', '00002322.00', 'A');
INSERT INTO `t` VALUES ('4', '赵六', '00005434.00', 'C');
INSERT INTO `t` VALUES ('5', '陈七', '00003434.00', 'B');
INSERT INTO `t` VALUES ('6', '黑八', '00005421.00', 'C');


-- ##############################
-- sources： http://www.iteye.com/topic/339546
-- ##############################
-- 第一道：显示出  业绩 大于同一地区平均值的 合同id  姓名 地区 业绩
-- SELECT * FROM T t1 where t1.smoney > (select avg(t2.smoney) from T t2  where t2.sprovince = t1.sprovince)

-- SELECT t1.* FROM T t1, (SELECT avg(smoney) avgMoney, sprovince FROM T GROUP BY sprovince) t3
-- WHERE t1.sprovince = t3.sprovince and t1.smoney > t3.avgMoney
-- 第二道：把同一地区的  平均业绩 地区 插入到新表中 （新表只包含两个字段即：平均业绩 地区）
-- create table TT as (SELECT sprovince, avg(smoney) avgMoney  FROM T GROUP BY sprovince)

