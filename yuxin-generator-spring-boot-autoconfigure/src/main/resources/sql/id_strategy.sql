/*
 Navicat Premium Data Transfer

 Source Server         : localhost3307
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3307
 Source Schema         : sakila

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 24/12/2021 20:13:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for id_strategy
-- ----------------------------
CREATE TABLE IF NOT EXISTS `id_strategy`  (
    `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '策略主键',
    `id_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '策略名称',
    `id_prefix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ID前缀',
    `id_suffix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ID后缀',
    `id_step` int(16) NOT NULL COMMENT 'redis自增步长',
    `id_time_pattern` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组合成ID的时间格式',
    `id_length` int(32) NOT NULL COMMENT 'ID长度：id_length=prefix+id_time_pattern+redis自增数值+suffix',
    `id_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用于缓存规则策略的key，同时也是作为查询该规则的条件',
    `id_key_expire` bigint(0) UNSIGNED NULL DEFAULT 0 COMMENT 'redis过期时间，单位毫秒，不配置，日终过期',
    `id_incr_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'redis自增数值的key,不配置，则所有的规则共用一个默认key',
    `id_incr_key_expire` bigint(0) UNSIGNED NULL DEFAULT 0 COMMENT 'redis自增数值的key,单位毫秒,不配置，日终过期',
    `id_status` int(1) NOT NULL DEFAULT 1 COMMENT 'ID是否可用；0-否，1-是',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id_key_index`(`id_key`) USING BTREE COMMENT '给id_key创建唯一索引',
    UNIQUE KEY `id_incr_key_index` (`id_incr_key`) USING BTREE COMMENT '创建唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
