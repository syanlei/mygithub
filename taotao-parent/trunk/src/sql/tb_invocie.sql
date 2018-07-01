/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : myproject

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-07-01 12:15:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_invocie
-- ----------------------------
DROP TABLE IF EXISTS `tb_invocie`;
CREATE TABLE `tb_invocie` (
  `invocie_id` varchar(100) NOT NULL COMMENT '发票id',
  `invocie_name` varchar(20) DEFAULT NULL COMMENT '收件人姓名',
  `invocie_phone` varchar(20) DEFAULT NULL COMMENT '收件人电话',
  `invocie_type` varchar(20) DEFAULT NULL COMMENT '发票类型',
  `invocie_mode` varchar(20) DEFAULT NULL COMMENT '发票方式',
  `invocie_head` varchar(255) DEFAULT NULL COMMENT '发票台头',
  `invocie_content` varchar(255) DEFAULT NULL COMMENT '发票内容',
  `created` datetime DEFAULT NULL COMMENT '发票创建时间',
  `updated` datetime DEFAULT NULL COMMENT '发票更新时间',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`invocie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
