/*
 Navicat Premium Dump SQL

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : alishan.rwlb.rds.aliyuncs.com:3306
 Source Schema         : sales

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 13/04/2025 15:19:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int NOT NULL,
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `checksum` int NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1', 'init', 'SQL', 'V1__init.sql', -1960639858, 'root', '2024-03-05 14:29:53', 317, 1);

-- ----------------------------
-- Table structure for index_notes
-- ----------------------------
DROP TABLE IF EXISTS `index_notes`;
CREATE TABLE `index_notes`  (
  `notes_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '备忘id',
  `notes_title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备忘标题',
  `notes_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备忘内容',
  `notes_date` date NULL DEFAULT NULL COMMENT '备忘日期',
  `notes_time` time NULL DEFAULT NULL COMMENT '备忘时间',
  `notes_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`notes_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of index_notes
-- ----------------------------
INSERT INTO `index_notes` VALUES (2, '321', '啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打', '2023-05-06', '19:07:25', '3');
INSERT INTO `index_notes` VALUES (4, '444', '啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打', '2023-05-05', '10:07:48', '3');
INSERT INTO `index_notes` VALUES (5, '555', '啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打', '2023-05-05', '10:07:48', '3');
INSERT INTO `index_notes` VALUES (6, '6666', '啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打', '2023-05-05', '10:07:48', '3');
INSERT INTO `index_notes` VALUES (7, '6666', '啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打啊啊啊啊哇大苏打', '2023-05-05', '10:07:48', '3');
INSERT INTO `index_notes` VALUES (8, '6666', '啊啊啊啊哇大', '2023-05-05', '10:07:48', '1');
INSERT INTO `index_notes` VALUES (13, '哈哈哈哈哈哈哈哈哈哈呵呵', '是哈哈哈哈', '2023-05-06', '21:05:21', '3');
INSERT INTO `index_notes` VALUES (14, '哈哈哈哈哈哈哈哈哈哈呵呵', '是哈哈哈哈', '2023-05-06', '21:05:21', '3');
INSERT INTO `index_notes` VALUES (15, '就哈哈哈哈', '哈哈哈哈hh哈哈哈哈哈呵呵', '2023-05-06', '21:07:35', '3');
INSERT INTO `index_notes` VALUES (16, '坤坤kk', '你干嘛 哎呦', '2023-05-06', '21:14:22', '3');
INSERT INTO `index_notes` VALUES (17, '反反复复烦烦烦', '他吞吞吐吐', '2023-05-06', '21:14:55', '3');
INSERT INTO `index_notes` VALUES (18, '强强强强', '啊啊啊啊', '2023-05-06', '21:15:53', '3');
INSERT INTO `index_notes` VALUES (19, '咿呀咿呀哟', '三生三世十里桃花', '2023-05-06', '21:16:49', '3');
INSERT INTO `index_notes` VALUES (20, '坤坤坤', '你干嘛 哎呦 黑恶hi', '2023-05-06', '21:14:22', '3');
INSERT INTO `index_notes` VALUES (21, '阿斯顿撒', '啊实打', '2023-05-06', '22:10:31', '3');
INSERT INTO `index_notes` VALUES (22, '吃饭', '吃桃李还是什么东西', '2023-05-07', '18:09:06', '1');
INSERT INTO `index_notes` VALUES (23, '备忘1', '备忘内容1', '2023-05-20', '14:50:08', '1');
INSERT INTO `index_notes` VALUES (24, '备忘2', '备忘内容2', '2023-05-20', '15:50:10', '1');
INSERT INTO `index_notes` VALUES (25, '备忘3', '备忘内容3', '2023-05-20', '16:50:26', '1');
INSERT INTO `index_notes` VALUES (27, '备忘4', '备忘4', '2023-05-20', '17:52:22', '1');
INSERT INTO `index_notes` VALUES (28, 'asd', NULL, NULL, NULL, '1');
INSERT INTO `index_notes` VALUES (29, '备忘5', '备忘内容5', '2023-05-20', '13:52:33', '1');
INSERT INTO `index_notes` VALUES (30, '今日需要盘点库存。', '农夫山泉库存不足，需要补货', '2024-01-10', '23:14:07', '1');
INSERT INTO `index_notes` VALUES (31, '美团外卖配送', '明天有18单美团外卖需要在12点前配送', NULL, '23:14:16', '1');
INSERT INTO `index_notes` VALUES (32, '打卡', '下班要打卡', '2024-03-05', '18:00:00', '1');
INSERT INTO `index_notes` VALUES (33, 'test', 'test', NULL, '14:33:08', '1');

-- ----------------------------
-- Table structure for menu_manage
-- ----------------------------
DROP TABLE IF EXISTS `menu_manage`;
CREATE TABLE `menu_manage`  (
  `menu_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  `menu_icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_index` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路由路径',
  `menu_path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '完整路径',
  `menu_level` bigint NULL DEFAULT NULL COMMENT '级别（0：顶级；id：二级）',
  `menu_sort` bigint NULL DEFAULT NULL COMMENT '排序',
  `menu_show` bigint NULL DEFAULT NULL COMMENT '显示状态（0隐藏；1显示）',
  `menu_rights` int NULL DEFAULT NULL COMMENT '权限（0：管理员；1：销售员）',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_manage
-- ----------------------------
INSERT INTO `menu_manage` VALUES (00000001, '数据总览', 'el-icon-s-home', '', '', 0, 1, 1, 0);
INSERT INTO `menu_manage` VALUES (00000002, '员工数据', 'el-icon-user', '/employeeData', '/dataSreening/employeeData', 1, NULL, 1, 1);
INSERT INTO `menu_manage` VALUES (00000004, '系统设置', 'el-icon-s-tools', '', '', 0, 2, 1, 1);
INSERT INTO `menu_manage` VALUES (00000005, '菜单设置', 'el-icon-document', '/menuManage', '/system/menuManage.vue', 4, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000006, '角色管理', 'el-icon-user', '/personalInformation', '/system/personalInformation.vue', 4, NULL, 1, 1);
INSERT INTO `menu_manage` VALUES (00000007, '用户管理', 'el-icon-user', '/userManagement', '/system/userManagement.vue', 4, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000008, '销售管理', 'el-icon-s-marketing', '', '', 0, 3, 1, 1);
INSERT INTO `menu_manage` VALUES (00000009, '商品售出记录', 'el-icon-truck', '/salesRecord', '/sales/salesRecord.vue', 8, NULL, 1, 1);
INSERT INTO `menu_manage` VALUES (00000010, '缺货记录', 'el-icon-sold-out', '/scarceRecord', '/sales/scarceRecord.vue', 8, NULL, 1, 1);
INSERT INTO `menu_manage` VALUES (00000011, '货品采购', 'el-icon-s-goods', '', '', 0, 4, 1, 0);
INSERT INTO `menu_manage` VALUES (00000012, '订单管理', 'el-icon-tickets', '/orderManagement', '/purchase/orderManagement.vue', 11, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000013, '物流运输', 'el-icon-truck', '/transportationManagement', '/purchase/transportationManagement.vue', 11, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000014, '库存管理', 'el-icon-s-cooperation', '', '', 0, 5, 1, 1);
INSERT INTO `menu_manage` VALUES (00000015, '类别管理', 'el-icon-document', '/categoryManagement', '/stock/categoryManagement.vue', 14, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000016, '单位管理', 'el-icon-school', '/unitManagement', '/stock/unitManagement.vue', 14, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000017, '库房登记', 'el-icon-house', '/storehouseManagement', '/stock/storehouseManagement.vue', 14, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000018, '货物登记', 'el-icon-shopping-bag-1', '/goodsManagement', '/stock/goodsManagement.vue', 14, NULL, 1, 1);
INSERT INTO `menu_manage` VALUES (00000019, '店铺管理', 'el-icon-s-custom', '', NULL, 0, 6, 1, 0);
INSERT INTO `menu_manage` VALUES (00000020, '店铺管理', 'el-icon-setting', '/storeManagement', '/store/storeManagement.vue', 19, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000021, '人员分配', 'el-icon-edit-outline', '/delegate', '/store/delegate.vue', 19, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000022, '员工登记', 'el-icon-s-custom', '', '', 0, 7, 1, 0);
INSERT INTO `menu_manage` VALUES (00000023, '员工管理', 'el-icon-user', '/employeeManagement', '/employee/employeeManagement.vue', 22, NULL, 1, 0);
INSERT INTO `menu_manage` VALUES (00000088, '销售数据', 'el-icon-truck', '/salesData', '/dataSreening/salesData', 1, 0, 1, 0);

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `order_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_corporation` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `order_category` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品类别',
  `order_goods` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购商品名',
  `order_num` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购数量',
  `order_unit` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位',
  `order_price` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '批发单价',
  `order_state` int NULL DEFAULT NULL COMMENT '0（未开始）1（开始）2（完成）',
  `order_date` datetime NULL DEFAULT NULL COMMENT '订单日期',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------
INSERT INTO `purchase_order` VALUES (00000035, 'BH20230523', 'XXX厂家', '铸铁弯头,PVC三通', '32铸铁弯头,32PVC三通', '10,10', '个,个', '5,5', 2, '2023-04-26 07:47:03');
INSERT INTO `purchase_order` VALUES (00000036, 'BH20230504', 'XXX厂家', '饮料,饮料', '红牛,果汁', '10,10', '箱,箱', '5,5', 0, '2025-02-28 01:32:21');
INSERT INTO `purchase_order` VALUES (00000038, 'BH010', 'XX厂家', '饮料,饮料', '哇哈哈,哇哈哈桶装水', '100,1', '个,桶', '5,6', 0, '2025-02-28 01:31:50');
INSERT INTO `purchase_order` VALUES (00000039, '1005236', 'test', '饮料', '茉莉花茶', '100', '个', '2', 0, '2025-02-28 01:30:39');

-- ----------------------------
-- Table structure for purchase_transport
-- ----------------------------
DROP TABLE IF EXISTS `purchase_transport`;
CREATE TABLE `purchase_transport`  (
  `transport_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '运输id',
  `transport_mode` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '运输方式',
  `transport_driver` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '司机名字',
  `transport_drivertel` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  `transport_carnumber` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `transport_startdate` date NULL DEFAULT NULL COMMENT '开始日期',
  `transport_enddate` date NULL DEFAULT NULL COMMENT '结束日期',
  `transport_storagehouse` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '库房（id）',
  `transport_ordernumber` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `transport_state` int NULL DEFAULT NULL COMMENT '0（未开始）1（完成）',
  PRIMARY KEY (`transport_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_transport
-- ----------------------------
INSERT INTO `purchase_transport` VALUES (00000039, '公路运输', '张三', '15555555555', '蒙L6931', '2023-04-26', '2023-04-28', '2', 'BH20230523', 1);
INSERT INTO `purchase_transport` VALUES (00000040, '公路运输', '李四', '15555555555', '蒙L6623', '2023-05-04', '2023-05-10', '4', 'BH20230504', 1);
INSERT INTO `purchase_transport` VALUES (00000041, '公路运输', '张三', '17758236987', '京A85FF2', '2024-03-01', '2024-03-04', '2', '1005236', 1);
INSERT INTO `purchase_transport` VALUES (00000043, '公路运送', '李四', '17788887777', '京AFFF85', '2024-03-04', '2024-03-06', '2', 'BH010', 1);

-- ----------------------------
-- Table structure for sales_record
-- ----------------------------
DROP TABLE IF EXISTS `sales_record`;
CREATE TABLE `sales_record`  (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `record_order` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `record_goods` bigint NULL DEFAULT NULL COMMENT '商品id',
  `record_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `record_num` bigint NULL DEFAULT NULL COMMENT '售出数量',
  `record_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `record_salesperson` bigint NULL DEFAULT NULL COMMENT '销售人员id',
  `record_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单日期',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales_record
-- ----------------------------
INSERT INTO `sales_record` VALUES (1, '333', 16, '25管箍', 20, 40.00, 3, '2023-01-01 21:24:35');
INSERT INTO `sales_record` VALUES (2, '333', 18, '32弯头', 10, 100.00, 1, '2023-01-02 21:24:35');
INSERT INTO `sales_record` VALUES (3, '222', 16, '25管箍', 20, 40.00, 2, '2023-02-01 21:24:29');
INSERT INTO `sales_record` VALUES (4, '222', 18, '32弯头', 10, 100.00, 2, '2023-02-01 21:24:29');
INSERT INTO `sales_record` VALUES (5, '888', 18, '32弯头', 10, 150.00, 31, '2023-03-26 21:24:29');
INSERT INTO `sales_record` VALUES (6, '777', 18, '32弯头', 10, 320.00, 31, '2023-03-26 21:24:29');
INSERT INTO `sales_record` VALUES (7, '666', 18, '32弯头', 10, 630.00, 1, '2023-03-26 21:24:29');
INSERT INTO `sales_record` VALUES (8, '444', 31, 'XX牌大型锅炉', 10, 200.00, 1, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (9, '555', 31, 'XX牌大型锅炉', 10, 145.00, 31, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (10, '21354', 31, 'XX牌大型锅炉', 1, 2000.00, 3, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (11, '121', 35, '5组暖气片', 10, 100.00, 32, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (12, '000', 35, '5组暖气片', 10, 200.00, 32, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (13, '123', 35, '5组暖气片', 10, 200.00, 30, '2023-04-01 21:24:29');
INSERT INTO `sales_record` VALUES (33, 'BH009', 40, '32铸铁管箍', 25, 250.00, 31, '2023-04-07 16:32:57');
INSERT INTO `sales_record` VALUES (34, 'BH009', 29, '32管箍', 10, 50.00, 31, '2023-04-07 16:32:57');
INSERT INTO `sales_record` VALUES (35, '1132465', 29, '32管箍', 50, 250.00, 3, '2023-05-07 17:21:06');
INSERT INTO `sales_record` VALUES (36, '1132465', 30, '5组暖气片', 3, 300.00, 3, '2023-05-07 17:21:06');
INSERT INTO `sales_record` VALUES (37, 'BH010', 33, '40弯头', 20, 200.00, 1, '2023-05-07 19:30:39');
INSERT INTO `sales_record` VALUES (38, 'BH010', 40, '32铸铁管箍', 10, 100.00, 1, '2023-05-07 19:30:39');
INSERT INTO `sales_record` VALUES (39, '21354', 31, 'XX牌大型锅炉', 1, 1000.00, 3, '2023-04-02 21:24:29');
INSERT INTO `sales_record` VALUES (40, '213541', 31, 'XX牌大型锅炉', 1, 1500.00, 3, '2023-04-03 21:24:29');
INSERT INTO `sales_record` VALUES (41, '213542', 31, 'XX牌大型锅炉', 1, 1300.00, 3, '2023-04-04 21:24:29');
INSERT INTO `sales_record` VALUES (42, '213543', 31, 'XX牌大型锅炉', 1, 1200.00, 3, '2023-04-06 21:24:29');
INSERT INTO `sales_record` VALUES (43, 'BH008', 41, '32铸铁弯头', 5, 25.00, 1, '2023-05-10 20:36:59');
INSERT INTO `sales_record` VALUES (44, 'BH008', 30, '5组暖气片', 1, 100.00, 1, '2023-05-10 20:36:59');
INSERT INTO `sales_record` VALUES (45, 'BH008', 40, '32铸铁管箍', 5, 50.00, 1, '2023-05-10 20:36:59');
INSERT INTO `sales_record` VALUES (46, 'DDH001', 35, 'XXX牌水龙头', 2, 60.00, 2, '2023-05-19 18:38:16');
INSERT INTO `sales_record` VALUES (47, 'DDH001', 38, '32三通', 10, 50.00, 2, '2023-05-19 18:38:16');
INSERT INTO `sales_record` VALUES (51, 'DDH003', 38, '32三通', 10, 80.00, 2, '2023-05-19 18:38:16');
INSERT INTO `sales_record` VALUES (52, 'DDH002', 38, '32三通', 10, 150.00, 2, '2023-05-18 18:38:16');
INSERT INTO `sales_record` VALUES (53, 'DDH004', 38, '32三通', 10, 120.00, 2, '2023-05-17 18:38:16');
INSERT INTO `sales_record` VALUES (54, 'DDH005', 38, '32三通', 10, 100.00, 2, '2023-05-16 18:38:16');
INSERT INTO `sales_record` VALUES (55, 'DDH006', 38, '32三通', 10, 75.00, 2, '2023-05-15 18:38:16');
INSERT INTO `sales_record` VALUES (56, 'DDH007', 38, '32三通', 10, 99.00, 2, '2023-05-14 18:38:16');
INSERT INTO `sales_record` VALUES (57, '333', 35, 'XXX牌水龙头', 4, 120.00, 1, '2024-01-10 23:31:46');
INSERT INTO `sales_record` VALUES (61, '1888523', 105, '亚克力板', 1000, 500000.00, 39, '2024-03-05 15:20:38');

-- ----------------------------
-- Table structure for stock_category
-- ----------------------------
DROP TABLE IF EXISTS `stock_category`;
CREATE TABLE `stock_category`  (
  `category_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `category_name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类名',
  `category_notes` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_category
-- ----------------------------
INSERT INTO `stock_category` VALUES (00000003, 'PVC弯头', 'PVC管件');
INSERT INTO `stock_category` VALUES (00000004, '铸铁管箍', '铸铁管件');
INSERT INTO `stock_category` VALUES (00000005, '铸铁暖气片', '低端暖气片');
INSERT INTO `stock_category` VALUES (00000006, '锅炉', '大型锅炉');
INSERT INTO `stock_category` VALUES (00000048, 'PVC三通', 'PVC管件');
INSERT INTO `stock_category` VALUES (00000049, '铸铁弯头', '铸铁管件');
INSERT INTO `stock_category` VALUES (00000050, 'PVC管箍', 'PVC管件');
INSERT INTO `stock_category` VALUES (00000051, '家用锅炉', '家用小型锅炉');
INSERT INTO `stock_category` VALUES (00000052, 'PVC热水管', '小型管件');
INSERT INTO `stock_category` VALUES (00000053, 'PVC冷水管', '小型管件');
INSERT INTO `stock_category` VALUES (00000054, '塑料排水管道', '大型管道');
INSERT INTO `stock_category` VALUES (00000055, '塑料水龙头', '低端小型水龙头');
INSERT INTO `stock_category` VALUES (00000056, '铜水龙头', '全铜高端水龙头');
INSERT INTO `stock_category` VALUES (00000057, '饮料', '饮料');
INSERT INTO `stock_category` VALUES (00000058, '亚克力板', '高端亚克力板');

-- ----------------------------
-- Table structure for stock_goods
-- ----------------------------
DROP TABLE IF EXISTS `stock_goods`;
CREATE TABLE `stock_goods`  (
  `goods_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_category` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类别',
  `goods_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `goods_price` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
  `goods_quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  `goods_unit` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位',
  `goods_storehouse` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '存储库房',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_goods
-- ----------------------------
INSERT INTO `stock_goods` VALUES (00000025, '暖气片', '4组陶瓷暖气片', '100', 50, '个', '3');
INSERT INTO `stock_goods` VALUES (00000028, 'PVC弯头', '25弯头', '10', 100, '个', '4');
INSERT INTO `stock_goods` VALUES (00000029, '铸铁管箍', '32管箍', '5', 40, '个', '4');
INSERT INTO `stock_goods` VALUES (00000030, '铸铁暖气片', '5组暖气片', '100', 4, '组', '2');
INSERT INTO `stock_goods` VALUES (00000031, '锅炉', 'XX牌大型锅炉', '2000', 2, '台', '2');
INSERT INTO `stock_goods` VALUES (00000032, 'PVC三通', '32三通', '10', 200, '个', '2');
INSERT INTO `stock_goods` VALUES (00000033, '铸铁弯头', '40弯头', '10', 130, '个', '1');
INSERT INTO `stock_goods` VALUES (00000034, '塑料排水管道', '100排水管', '100', 1, '根', '4');
INSERT INTO `stock_goods` VALUES (00000035, '铜水龙头', 'XXX牌水龙头', '30', 0, '个', '4');
INSERT INTO `stock_goods` VALUES (00000036, '家用锅炉', 'XXX牌小型锅炉', '1000', 14, '台', '4');
INSERT INTO `stock_goods` VALUES (00000037, 'PVC三通', '42三通', '5', 197, '个', '4');
INSERT INTO `stock_goods` VALUES (00000038, 'PVC三通', '32三通', '5', 90, '个', '4');
INSERT INTO `stock_goods` VALUES (00000039, '铸铁弯头', '32弯头', '10', 80, '个', '4');
INSERT INTO `stock_goods` VALUES (00000040, '铸铁管箍', '32铸铁管箍', '10', 40, '个', '4');
INSERT INTO `stock_goods` VALUES (00000041, '铸铁弯头', '32铸铁弯头', '5', 5, '个', '2');
INSERT INTO `stock_goods` VALUES (00000042, 'PVC三通', '32PVC三通', '5', 10, '个', '2');
INSERT INTO `stock_goods` VALUES (00000043, '铸铁弯头', '32铸铁弯头', '5', 10, '个', '2');
INSERT INTO `stock_goods` VALUES (00000044, 'PVC三通', '32PVC三通', '5', 10, '个', '2');
INSERT INTO `stock_goods` VALUES (00000045, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000046, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000047, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000048, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000049, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000050, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000051, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000052, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000053, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000054, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000055, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000056, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000057, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000058, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000059, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000060, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000061, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000062, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000063, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000064, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000065, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000066, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000067, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000068, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000069, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000070, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000071, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000072, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000073, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000074, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000075, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000076, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000077, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000078, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000079, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000080, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000081, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000082, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000083, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000084, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000085, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000086, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000087, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000088, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000089, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000090, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000091, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000092, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000093, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000094, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000095, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000096, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000097, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000098, '铜水龙头', '****水龙头', '2', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000099, '铸铁管箍', '管箍', '5', 10, '个', '4');
INSERT INTO `stock_goods` VALUES (00000100, 'PVC三通', '三通', '5', 10, '个', '4');
INSERT INTO `stock_goods` VALUES (00000101, '铸铁管箍', '管箍', '5', 10, '个', '4');
INSERT INTO `stock_goods` VALUES (00000102, 'PVC三通', '三通', '5', 10, '个', '4');
INSERT INTO `stock_goods` VALUES (00000103, 'PVC弯头', '弯头', '5', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000104, 'PVC弯头', '弯头', '5', 100, '个', '2');
INSERT INTO `stock_goods` VALUES (00000105, '饮料', '阿萨姆奶茶', '500', 98999, '箱', '5');
INSERT INTO `stock_goods` VALUES (00000106, '饮料', '红牛', '700', 9555, '箱', '5');

-- ----------------------------
-- Table structure for stock_storehouse
-- ----------------------------
DROP TABLE IF EXISTS `stock_storehouse`;
CREATE TABLE `stock_storehouse`  (
  `storehouse_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '库房id',
  `storehouse_name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '库房名',
  `storehouse_address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `storehouse_notes` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`storehouse_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_storehouse
-- ----------------------------
INSERT INTO `stock_storehouse` VALUES (00000001, '货物一库', '物流园', '管件暖器配件');
INSERT INTO `stock_storehouse` VALUES (00000002, '货物二库', '口岸', '卫浴厨具');
INSERT INTO `stock_storehouse` VALUES (00000004, '货物三库', '临河', '五金电料');
INSERT INTO `stock_storehouse` VALUES (00000005, '货物四库', '码头', '杂七杂八');

-- ----------------------------
-- Table structure for stock_unit
-- ----------------------------
DROP TABLE IF EXISTS `stock_unit`;
CREATE TABLE `stock_unit`  (
  `unit_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '单位id',
  `unit_name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位名',
  `unit_notes` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位备注',
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_unit
-- ----------------------------
INSERT INTO `stock_unit` VALUES (00000001, '根', '管道，线槽等');
INSERT INTO `stock_unit` VALUES (00000002, '包', '各种管件，保温材料等');
INSERT INTO `stock_unit` VALUES (00000003, '个', '小型管件，弯头水龙头等');
INSERT INTO `stock_unit` VALUES (00000004, '箱', '箱装配件等');
INSERT INTO `stock_unit` VALUES (00000005, '盒', '螺丝钉子，小型配件等');
INSERT INTO `stock_unit` VALUES (00000007, '组', '铸铁，陶瓷暖气片等');
INSERT INTO `stock_unit` VALUES (00000008, '台', '锅炉，热水器等大型件');
INSERT INTO `stock_unit` VALUES (00000009, '箱', '中型散件，管件等');
INSERT INTO `stock_unit` VALUES (00000010, '袋', '小型散件，管件等');
INSERT INTO `stock_unit` VALUES (00000011, '桶', '润滑油，大桶油漆等');
INSERT INTO `stock_unit` VALUES (00000012, '罐', '喷漆，油漆等');
INSERT INTO `stock_unit` VALUES (00000013, '块', '亚克力');

-- ----------------------------
-- Table structure for store_storemanage
-- ----------------------------
DROP TABLE IF EXISTS `store_storemanage`;
CREATE TABLE `store_storemanage`  (
  `storemanage_id` bigint(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `storemanage_name` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '店名',
  `storemanage_content` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '主营',
  `storemanage_address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `storemanage_notes` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`storemanage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_storemanage
-- ----------------------------
INSERT INTO `store_storemanage` VALUES (00000032, '鲜虾', '皮皮虾等', '乌兰察布市集宁区XX街道XX号', '暂无备注');
INSERT INTO `store_storemanage` VALUES (00000033, '饮料', '红牛等', '呼和浩特市XX区XX街道XX号', '在呼市卖货的五金店');
INSERT INTO `store_storemanage` VALUES (00000034, '菜品', '红烧肉等', '巴彦淖尔市临河区XX街道XX号', '在巴彦淖尔市卖货的店');
INSERT INTO `store_storemanage` VALUES (00000036, '果冻', '橘子果冻等', '呼和浩特', '在呼和浩特的店铺');
INSERT INTO `store_storemanage` VALUES (00000037, '奶茶', '奶茶专卖', '集宁XX路', '专卖XXX货物的店铺');
INSERT INTO `store_storemanage` VALUES (00000038, '海参', '青岛大海参', '青岛', '海参专卖店');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_img` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `user_tel` bigint NULL DEFAULT NULL COMMENT '用户电话（账号）',
  `user_password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_gender` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `user_email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `user_notes` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_workstore` int NULL DEFAULT NULL COMMENT '工作店铺（店铺id）',
  `user_appointment` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职务',
  `user_state` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '状态（0：在岗、1：离职）',
  `user_identity` int NULL DEFAULT NULL COMMENT '用户身份：0（管理员）1（销售员）',
  `user_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 1763816573, '123456', '王七', '男', '2189925681@qq.com', '管理员', 37, '店长', '0', 0, '2023-04-22 17:05:33');
INSERT INTO `user` VALUES (2, NULL, 222222, '222222', '李四', '男', 'XXX1@qq.com', '他是李四，老板的亲戚，得给安排个店长', 33, '售货员', '0', 1, '2023-04-22 16:58:57');
INSERT INTO `user` VALUES (3, 'asdasd', 111111, '111111', '张三', '男', 'asd@qq.com', '管理员', 34, '店长', '1', 0, '2023-04-22 16:58:57');
INSERT INTO `user` VALUES (30, NULL, 15555555555, '111111', '张三', '男', 'XXX@qq.com', '他是张三，一个普通的员工', 34, '售货员', '0', 1, '2023-04-27 20:51:48');
INSERT INTO `user` VALUES (31, NULL, 13333333333, '111111', '王五', '男', 'XXX@qq.com', '他是王五，也是一个普通的员工', 32, '店长', '0', 1, '2023-04-28 10:40:07');
INSERT INTO `user` VALUES (32, NULL, 14444444444, '111111', '马六', '女', 'XXX@qq.com', '她是马六，一个普通的女员工', 32, '售货员', '0', 1, '2023-04-28 10:40:49');
INSERT INTO `user` VALUES (38, '', 999999, '999999', '张管理员', '男', '123@qq.com', '没什么好说的。', 37, '店长', '0', 0, '2023-05-04 19:05:37');
INSERT INTO `user` VALUES (39, '', 1008611, '123456', '李四', '男', '15236@163.com', '1111', 38, '店长', '1', 1, '2024-03-05 14:38:32');

SET FOREIGN_KEY_CHECKS = 1;
