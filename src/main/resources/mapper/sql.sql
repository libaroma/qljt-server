/*
 Navicat Premium Data Transfer

 Source Server         : 43.138.49.193
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 43.138.49.193:3306
 Source Schema         : office

 Target Server Type    : MySQL

 Date: 04/10/2022 21:41:52
*/
USE office;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_sdu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sdu`;
CREATE TABLE `tb_sdu`
(
    `id`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号id',
    `sdu_id`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
    `sdu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
    `status`   int(4)                                                 NOT NULL COMMENT '状态(1可用，0删除)',
    `time`     datetime(0)                                            NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    `id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`       varchar(32) COMMENT 'openid',
    `sdu_id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '学号/工号',
    `category`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '物资类别',
    `user_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '昵称',
    `user_avatar`   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
    `user_province` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份',
    `user_city`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
    `user_country`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
    `user_language` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言',
    `status`        int(4)                                                  NOT NULL COMMENT '状态(1可用，0删除)',
    `role`          int(4)                                                  NOT NULL COMMENT '角色(0普通，1管理员,2高级管理员)',
    `time`          datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (sdu_id) REFERENCES tb_sdu (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_seal
-- ----------------------------
DROP TABLE IF EXISTS `tb_seal`;
CREATE TABLE `tb_seal`
(
    `id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
    `seal`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公章名称',
    `status` int(4)                                                 NOT NULL COMMENT '状态(1可用，0删除)',
    `time`   datetime(0)                                            NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for tb_dispatch_methods
-- ----------------------------
DROP TABLE IF EXISTS `tb_dispatch_methods`;
CREATE TABLE `tb_dispatch_methods`
(
    `id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `method` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方式:党委会议研究决定',
    `office` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机关代字:院党字',
    `seal`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公章类别',
    `status` int(4)                                                 NOT NULL COMMENT '状态(1可用，0删除)',
    `time`   datetime(0)                                            NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (seal) REFERENCES tb_seal (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_space
-- ----------------------------
DROP TABLE IF EXISTS `tb_space`;
CREATE TABLE `tb_space`
(
    `id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `name`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '会场名称',
    `image`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面',
    `order`  int(4)                                                  NOT NULL COMMENT '次序',
    `status` int(4)                                                  NOT NULL COMMENT '状态(1可用，0删除)',
    `time`   datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_supplies_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplies_category`;
CREATE TABLE `tb_supplies_category`
(
    `id`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
    `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
    `status`   int(4)                                                 NOT NULL COMMENT '状态(1可用，0删除)',
    `time`     datetime(0)                                            NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for tb_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_category`;
CREATE TABLE `tb_goods_category`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `name`        varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资名称',
    `options`     varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资描述',
    `cover`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照片',
    `count`       int(8)                                                  NOT NULL COMMENT '数量',
    `total_count` int(8)                                                  NOT NULL COMMENT '总数量',
    `left_count`  int(8)                                                  NOT NULL COMMENT '剩余数量',
    `status`      int(4)                                                  NOT NULL COMMENT '状态(1可用，0删除)',
    `time`        datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for tb_user_default_info
DROP TABLE IF EXISTS `tb_user_default_info`;
CREATE TABLE `tb_user_default_info`
(
    `id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
    `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
    `company` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位',
    `contact` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
    `status`  int(4)                                                 NOT NULL COMMENT '状态(1可用，0删除)',
    `time`    datetime(0)                                            NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_space_records
-- ----------------------------
DROP TABLE IF EXISTS `tb_space_records`;
CREATE TABLE `tb_space_records`
(
    `id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`      varchar(32) COMMENT 'openid',
    `user_id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `confirm_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '批准人',
    `company`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '单位',
    `day`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '日期/星期',
    `date`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '日期/年月日',
    `begin`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '开始时间',
    `end`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '结束时间',
    `space`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '会场',
    `description`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因描述',
    `contact`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '电话',
    `count`        int(8)                                                  NOT NULL COMMENT '人数',
    `media`        int(4)                                                  NOT NULL COMMENT '多媒体',
    `time`         datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id),
    FOREIGN KEY (space) REFERENCES tb_space (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for tb_supplies_records
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplies_records`;
CREATE TABLE `tb_supplies_records`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`     varchar(32) COMMENT 'openid',
    `user_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `company`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '单位',
    `name`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '物资名称',
    `category`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '物资类别',
    `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因描述',
    `cover`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照片',
    `contact`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '电话',
    `count`       int(8)                                                  NOT NULL COMMENT '数量',
    `is_return`   int(4)                                                  NOT NULL COMMENT '是否归还/0:未还，1：已还未确认，2：已还',
    `time`        datetime(0)                                             NOT NULL COMMENT '时间',
    `return_time` datetime(0)                                             NOT NULL COMMENT '归还时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id),
    FOREIGN KEY (category) REFERENCES tb_supplies_category (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_goods_records
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_records`;
CREATE TABLE `tb_goods_records`
(
    `id`                varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`           varchar(32) COMMENT 'openid',
    `user_id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `company`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '单位',
    `goods`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '名称',
    `category`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '物资类别',
    `goods_options`     varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资描述',
    `description`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因描述',
    `cover`             varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照片',
    `contact`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '电话',
    `count`             int(8)                                                  NOT NULL COMMENT '数量',
    `goods_total_count` int(8)                                                  NOT NULL COMMENT '总数量',
    `goods_left_count`  int(8)                                                  NOT NULL COMMENT '剩余数量',
    `time`              datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id),
    FOREIGN KEY (goods) REFERENCES tb_goods_category (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for tb_dispatch_records
-- ----------------------------
DROP TABLE IF EXISTS `tb_dispatch_records`;
CREATE TABLE `tb_dispatch_records`
(
    `id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`      varchar(32) COMMENT 'openid',
    `user_id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `company`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '单位',
    `confirm_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '发文日期',
    `method`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '方式',
    `date`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '日期',
    `meeting_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '会议日期',
    `confirm_user` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资描述',
    `title`        varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因描述',
    `year`         int(8)                                                  NOT NULL COMMENT '年份',
    `index`        int(8)                                                  NOT NULL COMMENT '序号',
    `time`         datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id),
    FOREIGN KEY (method) REFERENCES tb_dispatch_methods (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log`
(
    `id`             bigint(16) AUTO_INCREMENT                               NOT NULL COMMENT '主键id',
    `_openid`        varchar(32) COMMENT 'openid',
    `user_id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '操作用户id',
    `opt_module`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '操作模块',
    `opt_type`       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '操作类型',
    `opt_url`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作url',
    `opt_method`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作方法',
    `opt_desc`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作描述',
    `request_param`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '请求参数',
    `request_method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '请求方式',
    `response_data`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '返回数据',
    `ip_address`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作ip',
    `ip_source`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作地址',
    `create_time`    datetime(0)                                             NOT NULL COMMENT '创建时间',
    `update_time`    datetime(0)                                             NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_seal_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_seal_record`;
CREATE TABLE `tb_seal_record`
(
    `id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `_openid`      varchar(32) COMMENT 'openid',
    `user_id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `company`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '单位',
    `confirm_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '批准人',
    `seal`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '公章',
    `description`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因描述',
    `contact`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '电话',
    `time`         datetime(0)                                             NOT NULL COMMENT '时间',
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (user_id) references tb_user (id),
    FOREIGN KEY (seal) REFERENCES tb_seal (id)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_email
-- ----------------------------
DROP TABLE IF EXISTS `tb_email`;
CREATE TABLE `tb_email`
(
    `id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'id',
    `name`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '名称',
    `email`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
    `status` int(4)                                                  NOT NULL COMMENT '状态(1可用，0删除)',
    `time`   datetime(0)                                             NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Table structure for tb_website_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_website_config`;
CREATE TABLE `tb_website_config`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT 'id',
    `_openid`     varchar(32) COMMENT 'openid',
    `user_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '用户id',
    `config`      varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置信息',
    `create_time` datetime(0)                                              NOT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                              NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
