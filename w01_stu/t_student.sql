
/*
 Navicat Premium Data Transfer

 Source Server         : localhost@621799mac
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : bjpowernode

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 11/11/2024 11:55:37
*/
CREATE DATABASE bjpowernode CHARACTER SET utf8mb4;
USE bjpowernode;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `no` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
INSERT INTO `t_student` (`no`, `name`) VALUES ('001', 'zhangSan');
INSERT INTO `t_student` (`no`, `name`) VALUES ('002', '张起灵');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;