/*
 Navicat Premium Data Transfer

 Source Server         : data
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : micro-platform

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 13/03/2019 23:04:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `information_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态 ：正在申请 ，申请失败，申请成功',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_user_information`(`information_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_apply
-- ----------------------------
INSERT INTO `activity_apply` VALUES (4, 5, 1, 'FAILURE');

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `authors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `release_time` datetime(0) NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'activity（活动），notify（通知），recruitment（招聘），public（公示）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES (3, '文章标题', '张航 曹孝双', '这是一大片的富文本编辑器', '2019-01-28 14:55:47', 'ACTIVITY');
INSERT INTO `information` VALUES (5, 'yyyy', 'xxxx', 'xxxx', '2019-01-28 15:58:04', 'ACTIVITY');

-- ----------------------------
-- Table structure for note_comment_notified
-- ----------------------------
DROP TABLE IF EXISTS `note_comment_notified`;
CREATE TABLE `note_comment_notified`  (
  `notify_id` int(11) NOT NULL,
  `notified_user_id` int(11) NOT NULL COMMENT '被通知者的userId',
  `status` int(11) NOT NULL COMMENT '0 未读 1 已读 2 删除',
  PRIMARY KEY (`notify_id`, `notified_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of note_comment_notified
-- ----------------------------
INSERT INTO `note_comment_notified` VALUES (2, 2, 1);
INSERT INTO `note_comment_notified` VALUES (2, 3, 0);
INSERT INTO `note_comment_notified` VALUES (2, 4, 0);
INSERT INTO `note_comment_notified` VALUES (2, 5, 0);
INSERT INTO `note_comment_notified` VALUES (2, 6, 0);
INSERT INTO `note_comment_notified` VALUES (6, 10, 0);
INSERT INTO `note_comment_notified` VALUES (7, 10, 0);
INSERT INTO `note_comment_notified` VALUES (8, 10, 0);
INSERT INTO `note_comment_notified` VALUES (9, 10, 0);
INSERT INTO `note_comment_notified` VALUES (10, 10, 0);
INSERT INTO `note_comment_notified` VALUES (11, 10, 0);
INSERT INTO `note_comment_notified` VALUES (12, 10, 0);
INSERT INTO `note_comment_notified` VALUES (13, 10, 0);
INSERT INTO `note_comment_notified` VALUES (14, 10, 0);
INSERT INTO `note_comment_notified` VALUES (15, 10, 0);
INSERT INTO `note_comment_notified` VALUES (16, 10, 0);
INSERT INTO `note_comment_notified` VALUES (17, 10, 0);
INSERT INTO `note_comment_notified` VALUES (18, 10, 0);
INSERT INTO `note_comment_notified` VALUES (19, 10, 0);

-- ----------------------------
-- Table structure for note_comment_notify
-- ----------------------------
DROP TABLE IF EXISTS `note_comment_notify`;
CREATE TABLE `note_comment_notify`  (
  `notify_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `note_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `send_time` timestamp(0) NOT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`notify_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of note_comment_notify
-- ----------------------------
INSERT INTO `note_comment_notify` VALUES (2, 1, 1, '2018-12-12 06:27:45', '你好，可以叫我的微信吗？');
INSERT INTO `note_comment_notify` VALUES (3, 10, 10, '2018-12-12 06:42:03', '你说的对');
INSERT INTO `note_comment_notify` VALUES (4, 10, 10, '2018-12-12 06:42:03', '你说的对');
INSERT INTO `note_comment_notify` VALUES (5, 10, 10, '2018-12-12 06:42:03', '你说的对');
INSERT INTO `note_comment_notify` VALUES (6, 10, 10, '2018-12-12 06:48:34', '你说的对');
INSERT INTO `note_comment_notify` VALUES (7, 10, 10, '2018-12-12 08:34:57', '你说的对');
INSERT INTO `note_comment_notify` VALUES (8, 10, 10, '2018-12-12 08:43:25', '你说的对');
INSERT INTO `note_comment_notify` VALUES (9, 10, 10, '2018-12-12 08:57:33', '你说的对');
INSERT INTO `note_comment_notify` VALUES (10, 10, 10, '2018-12-12 08:58:21', '你说的对');
INSERT INTO `note_comment_notify` VALUES (11, 10, 10, '2018-12-12 08:58:27', '你说的对');
INSERT INTO `note_comment_notify` VALUES (12, 10, 10, '2018-12-12 08:57:33', '你说的对');
INSERT INTO `note_comment_notify` VALUES (13, 10, 10, '2018-12-12 08:58:27', '你说的对');
INSERT INTO `note_comment_notify` VALUES (14, 10, 10, '2018-12-12 08:58:21', '你说的对');
INSERT INTO `note_comment_notify` VALUES (15, 10, 10, '2018-12-12 08:58:21', '你说的对');
INSERT INTO `note_comment_notify` VALUES (16, 10, 10, '2018-12-12 08:58:27', '你说的对');
INSERT INTO `note_comment_notify` VALUES (17, 10, 10, '2018-12-12 08:57:33', '你说的对');
INSERT INTO `note_comment_notify` VALUES (18, 10, 10, '2018-12-12 10:08:10', '你说的对');
INSERT INTO `note_comment_notify` VALUES (19, 10, 10, '2018-12-12 10:09:15', '你说的对');

-- ----------------------------
-- Table structure for system_notified
-- ----------------------------
DROP TABLE IF EXISTS `system_notified`;
CREATE TABLE `system_notified`  (
  `notify_id` int(11) NOT NULL COMMENT '活动通知id',
  `notified_user_id` int(11) NOT NULL COMMENT '被通知者id',
  `status` int(11) NOT NULL COMMENT '0 未读 1 已读 2 已删除',
  PRIMARY KEY (`notify_id`, `notified_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_notified
-- ----------------------------
INSERT INTO `system_notified` VALUES (23, 2, 0);
INSERT INTO `system_notified` VALUES (24, 1, 0);

-- ----------------------------
-- Table structure for system_notify
-- ----------------------------
DROP TABLE IF EXISTS `system_notify`;
CREATE TABLE `system_notify`  (
  `notify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动消息id',
  `system_notify_type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'activity 活动, recurit 招聘\r\n',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '消息内容',
  `send_time` timestamp(0) NOT NULL COMMENT '通知发送时间',
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '活动名称',
  PRIMARY KEY (`notify_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_notify
-- ----------------------------
INSERT INTO `system_notify` VALUES (23, 'activity', '您已经报名成功', '2019-01-30 14:01:31', '云计数大赛');
INSERT INTO `system_notify` VALUES (24, 'activity', '非常抱歉，当前活动报名失败', '2019-01-30 16:16:44', 'yyyy');

SET FOREIGN_KEY_CHECKS = 1;
