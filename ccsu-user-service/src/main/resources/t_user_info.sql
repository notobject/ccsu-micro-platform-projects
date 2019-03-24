/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : js_sports

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 24/03/2019 17:56:01
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` smallint(255) DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT 0,
  `stu_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `last_login_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`, `open_id`) USING BTREE,
  UNIQUE INDEX `index_open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES (9, 'abc', 'Not Object', 'https://wx.qlogo.cn/mmopen/vi_32/jBy8W5IyGwicRbI841icic1y38tLe6lCTkBlJXDXOQuV6aW4W7x1nY9ox1caZQib6zX0U0bOWZ0NjiavgzzekK9ibyJw/132', 1, '??', '??', '??', 0, NULL, NULL, '2019-03-24 17:40:00', '2019-03-23 17:48:55');

SET FOREIGN_KEY_CHECKS = 1;
