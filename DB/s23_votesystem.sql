/*
 Navicat MySQL Data Transfer

 Source Server         : 線上投票系統
 Source Server Type    : MySQL
 Source Server Version : 110302
 Source Host           : localhost:3306
 Source Schema         : s23_votesystem

 Target Server Type    : MySQL
 Target Server Version : 110302
 File Encoding         : 65001

 Date: 17/01/2025 22:57:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vote_items
-- ----------------------------
DROP TABLE IF EXISTS `vote_items`;
CREATE TABLE `vote_items`  (
  `item_id` int NOT NULL AUTO_INCREMENT COMMENT '投票項目編號',
  `item_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票項目名稱',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vote_logs
-- ----------------------------
DROP TABLE IF EXISTS `vote_logs`;
CREATE TABLE `vote_logs`  (
  `user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票人',
  `item_id` int NOT NULL COMMENT '投票項目編號'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for createVoteItem
-- ----------------------------
DROP PROCEDURE IF EXISTS `createVoteItem`;
delimiter ;;
CREATE PROCEDURE `createVoteItem`(IN in_item_name VARCHAR(64))
BEGIN
	INSERT INTO `vote_items`(item_name) VALUES (in_item_name);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for createVoteItemsTable
-- ----------------------------
DROP PROCEDURE IF EXISTS `createVoteItemsTable`;
delimiter ;;
CREATE PROCEDURE `createVoteItemsTable`()
BEGIN
	CREATE TABLE IF NOT EXISTS `vote_items` (
		`item_id` INT NOT NULL COMMENT '投票項目編號' AUTO_INCREMENT,
		`item_name` VARCHAR(64) NOT NULL COMMENT '投票項目名稱',
		PRIMARY KEY (`item_id`)
	);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for createVoteLog
-- ----------------------------
DROP PROCEDURE IF EXISTS `createVoteLog`;
delimiter ;;
CREATE PROCEDURE `createVoteLog`(IN in_user VARCHAR(64), IN in_item_id INT)
BEGIN
	INSERT INTO `vote_logs` VALUES (in_user,in_item_id);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for createVoteLogsTable
-- ----------------------------
DROP PROCEDURE IF EXISTS `createVoteLogsTable`;
delimiter ;;
CREATE PROCEDURE `createVoteLogsTable`()
BEGIN
	CREATE TABLE IF NOT EXISTS `vote_logs` (
		`user` VARCHAR(64) NOT NULL COMMENT '投票人',
		`item_id` INT NOT NULL COMMENT '投票項目編號'
	);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for deleteVoteItemById
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteVoteItemById`;
delimiter ;;
CREATE PROCEDURE `deleteVoteItemById`(IN in_item_id INT)
BEGIN
	DELETE FROM `vote_items` WHERE item_id = in_item_id;
	SELECT ROW_COUNT() AS affected_rows;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for deleteVoteLogsById
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteVoteLogsById`;
delimiter ;;
CREATE PROCEDURE `deleteVoteLogsById`(IN in_item_id INT)
BEGIN
	DELETE FROM `vote_logs` WHERE item_id = in_item_id;
	SELECT ROW_COUNT() AS affected_rows;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getAllVoteItems
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAllVoteItems`;
delimiter ;;
CREATE PROCEDURE `getAllVoteItems`()
BEGIN
	SELECT
		i.item_id,
		i.item_name,
		COUNT(l.user) AS 'vote_count'
	FROM vote_items i
	LEFT JOIN vote_logs l on l.item_id	= i.item_id
	GROUP BY
		i.item_id, i.item_name ORDER BY  i.item_id ASC;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getAllVoteLogs
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAllVoteLogs`;
delimiter ;;
CREATE PROCEDURE `getAllVoteLogs`()
BEGIN
	SELECT * FROM `vote_logs`;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getVoteItemById
-- ----------------------------
DROP PROCEDURE IF EXISTS `getVoteItemById`;
delimiter ;;
CREATE PROCEDURE `getVoteItemById`(IN in_item_id INT)
BEGIN
	SELECT
		i.item_id,
		i.item_name,
		COUNT(l.user) AS 'vote_count'
	FROM vote_items i
	LEFT JOIN vote_logs l on l.item_id	= i.item_id
	WHERE
    i.item_id = in_item_id
	GROUP BY
		i.item_id, i.item_name;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getVoteStats
-- ----------------------------
DROP PROCEDURE IF EXISTS `getVoteStats`;
delimiter ;;
CREATE PROCEDURE `getVoteStats`()
BEGIN
	SELECT
		i.item_id AS '投票項目編號',
		i.item_name AS '投票項目名稱',
		COUNT(l.user) AS '目前投票票數'
	FROM vote_items i
	LEFT JOIN vote_logs l on l.item_id	= i.item_id
	GROUP BY
		i.item_id, i.item_name;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for updateVoteItem
-- ----------------------------
DROP PROCEDURE IF EXISTS `updateVoteItem`;
delimiter ;;
CREATE PROCEDURE `updateVoteItem`(IN in_item_id INT, IN in_item_name VARCHAR(64))
BEGIN
	UPDATE `vote_items` SET item_name = in_item_name WHERE item_id = in_item_id;
	SELECT ROW_COUNT() AS affected_rows;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
