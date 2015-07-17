/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-07-17 16:10:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `contacts_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `user_role` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`contacts_id`,`user_id`),
  KEY `FK_Reference_6` (`user_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`user_id`) REFERENCES `myuser` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('112001', '112002', '赵晋川', '18756498524', '项目经理', 'zhaojinchuan@temp.com');
INSERT INTO `contacts` VALUES ('112001', '112003', '胡强', '16584755946', '项目经理', 'huqiang117@temp.com');
INSERT INTO `contacts` VALUES ('112001', '112004', '虞毅', '16245647894', '项目成员', 'yuyi1235@temp.com');

-- ----------------------------
-- Table structure for `myuser`
-- ----------------------------
DROP TABLE IF EXISTS `myuser`;
CREATE TABLE `myuser` (
  `user_id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_role` varchar(30) DEFAULT NULL,
  `user_state` tinyint(1) NOT NULL,
  `userlogpath` varchar(200) DEFAULT NULL,
  `summary` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myuser
-- ----------------------------
INSERT INTO `myuser` VALUES ('112001', '张佳琪', '15622224562', 'zhangjiaqi@temp.com', '123456', '项目经理', '1', 'D:\\projectMy\\user\\112001.txt', '马云,1964年9月10日出生于浙江省杭州市,祖籍浙江绍兴嵊州(原嵊县)谷来镇,中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');
INSERT INTO `myuser` VALUES ('112002', '赵晋川', '18756498524', 'zhaojinchuan@temp.com', '123456', '项目经理', '1', 'D:\\projectMy\\user\\112002.txt', '马云,1964年9月10日出生于浙江省杭州市,祖籍浙江绍兴嵊州(原嵊县)谷来镇,中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');
INSERT INTO `myuser` VALUES ('112003', '胡强', '16584755946', 'huqiang117@temp.com', '123456', '项目经理', '1', 'D:\\projectMy\\user\\112003.txt', '马云,1964年9月10日出生于浙江省杭州市,祖籍浙江绍兴嵊州(原嵊县)谷来镇,中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');
INSERT INTO `myuser` VALUES ('112004', '虞毅', '16245647894', 'yuyi1235@temp.com', '123456', '项目成员', '1', 'D:\\projectMy\\user\\112004.txt', '马云,1964年9月10日出生于浙江省杭州市,祖籍浙江绍兴嵊州(原嵊县)谷来镇,中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');
INSERT INTO `myuser` VALUES ('112005', '张伯安', '19856468945', 'zhangboan@temp.com', '123456', '项目成员', '1', 'D:\\projectMy\\user\\112005.txt', '马云,1964年9月10日出生于浙江省杭州市,祖籍浙江绍兴嵊州(原嵊县)谷来镇,中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');

-- ----------------------------
-- Table structure for `problemlog`
-- ----------------------------
DROP TABLE IF EXISTS `problemlog`;
CREATE TABLE `problemlog` (
  `problem_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `createuser_id` bigint(20) NOT NULL,
  `dealuser_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `problem_descreption` varchar(300) NOT NULL,
  `logpath` varchar(100) DEFAULT NULL,
  `senduserid` bigint(20) NOT NULL,
  PRIMARY KEY (`problem_id`),
  KEY `FK_Reference_3` (`task_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problemlog
-- ----------------------------
INSERT INTO `problemlog` VALUES ('1', '3', '2015-07-16 23:27:45', '112001', '112001', '2', '发的啥打法是否', 'D:\\projectMy\\problem\\3_1.txt', '112002');

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL,
  `project_name` varchar(50) NOT NULL,
  `start_time` date DEFAULT NULL,
  `suppose_endtime` date DEFAULT NULL,
  `real_endtime` date DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `manager_id` bigint(20) NOT NULL,
  `state` varchar(10) NOT NULL,
  `level` int(11) NOT NULL,
  `rate` float DEFAULT NULL,
  `havedwork_hour` float DEFAULT NULL,
  `plan_hour` float DEFAULT NULL,
  `real_hour` float DEFAULT NULL,
  `summary` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '制作观光计划书', '2015-07-15', '2016-07-15', null, '56.23', '112001', '已提议', '1', '0', null, '1000', null, '阿里巴巴集团、淘宝网、支付宝创始人。');
INSERT INTO `project` VALUES ('2', '官洲船载制造项目', '2015-07-15', '2015-12-24', null, '10', '112002', '已提议', '1', '0', null, '800', null, '中国着名企业家,阿里巴巴集团、淘宝网、支付宝创始人。');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `user_role` varchar(30) NOT NULL,
  `authorization` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('管理员', '系统最高权限所有者。');
INSERT INTO `role` VALUES ('项目成员', '只具有管理自己参与的项目的权利（权利较小）');
INSERT INTO `role` VALUES ('项目经理', '管理项目创建用户（权限比较大）');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` bigint(20) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `state` varchar(10) NOT NULL,
  `rate` float DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `milepost` tinyint(1) DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `fathertask_id` bigint(20) DEFAULT NULL,
  `summary` varchar(300) DEFAULT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `plan_endtime` date NOT NULL,
  `tasklog_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK_Reference_2` (`project_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', 'test任务1', '已经完成', '100', '1', '1', '0', '23', '-1', '发生发生放大发发', '2015-07-15', '2015-07-16', '2016-07-15', 'D:\\projectMy\\task\\1_1.txt');
INSERT INTO `task` VALUES ('2', 'fasfa发生法', '出现问题', '100', '1', '2', '0', '30.25', '-1', '是打发发生', '2015-07-15', '2015-07-16', '2016-07-15', 'D:\\projectMy\\task\\2_2.txt');
INSERT INTO `task` VALUES ('3', '放大地方', '进行中的', '0', '1', '2', '0', '3231', '-1', '说大声的撒发生', '2015-07-15', '1000-01-01', '2016-07-15', 'D:\\projectMy\\task\\2_3.txt');
INSERT INTO `task` VALUES ('4', '沙发沙发', '已经完成', '100', '1', '2', '0', '123', '-1', 'asfddasfasfasffasfasf', '2015-07-15', '2015-07-17', '2016-07-15', 'D:\\projectMy\\task\\2_4.txt');

-- ----------------------------
-- Table structure for `usertask`
-- ----------------------------
DROP TABLE IF EXISTS `usertask`;
CREATE TABLE `usertask` (
  `user_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`task_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `myuser` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertask
-- ----------------------------
INSERT INTO `usertask` VALUES ('112002', '1');
INSERT INTO `usertask` VALUES ('112002', '4');
INSERT INTO `usertask` VALUES ('112003', '1');
INSERT INTO `usertask` VALUES ('112003', '2');
INSERT INTO `usertask` VALUES ('112003', '3');
INSERT INTO `usertask` VALUES ('112004', '2');
INSERT INTO `usertask` VALUES ('112004', '3');

-- ----------------------------
-- Table structure for `worktime`
-- ----------------------------
DROP TABLE IF EXISTS `worktime`;
CREATE TABLE `worktime` (
  `workdays` bigint(20) NOT NULL,
  `workdayhours` bigint(20) NOT NULL,
  `userlog` varchar(200) DEFAULT NULL,
  `tasklogpath` varchar(200) DEFAULT NULL,
  `problemlogpath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`workdays`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worktime
-- ----------------------------
INSERT INTO `worktime` VALUES ('5', '8', 'D:\\projectMy\\user\\', 'D:\\projectMy\\task\\', 'D:\\projectMy\\problem\\');
