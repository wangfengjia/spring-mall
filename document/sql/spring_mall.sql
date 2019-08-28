DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) not null DEFAULT '' COMMENT '用户名',
  `password` varchar(64) not null DEFAULT '' COMMENT '密码',
  `icon` varchar(500) not null DEFAULT '' COMMENT '头像',
  `email` varchar(100) not null DEFAULT '' COMMENT '邮箱',
  `nick_name` varchar(200) not null DEFAULT '' COMMENT '昵称',
  `note` varchar(500) not null DEFAULT '' COMMENT '备注信息',
  `login_time` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '最后登录时间',
  `status` tinyint not null DEFAULT '2' COMMENT '帐号启用状态：1->禁用；2->启用',
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) not null DEFAULT 0 COMMENT '父级权限id',
  `name` varchar(100) not null DEFAULT '' COMMENT '名称',
  `value` varchar(200) not null DEFAULT '' COMMENT '权限值',
  `icon` varchar(500) not null DEFAULT '' COMMENT '图标',
  `type` tinyint not null DEFAULT 1 COMMENT '权限类型：1->目录；2->菜单；3->按钮（接口绑定权限）',
  `uri` varchar(200) not null DEFAULT '' COMMENT '前端资源路径',
  `status` tinyint not null DEFAULT 2 COMMENT '启用状态；1->禁用；2->启用',
  `sort` int(11) not null DEFAULT 0 COMMENT '排序',
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

INSERT INTO `admin_permission` VALUES ('1', '1', '商品列表', 'pms:product:read', '', '1', '/admin/product/index', '2', 0, '2018-09-29 16:17:01', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('2', '1', '添加商品', 'pms:product:create', '', '1', '/admin/product/add', '2', 0, '2018-09-29 16:18:51', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('3', '1', '商品分类', 'pms:productCategory:read', '', '1', '/admin/productCate/index', '2', 0, '2018-09-29 16:23:07', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('4', '1', '商品类型', 'pms:productAttribute:read', '', '1', '/admin/productAttr/index', '2', 0, '2018-09-29 16:24:43', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('5', '1', '品牌管理', 'pms:brand:read', '', '1', '/admin/brand/index', '2', 0, '2018-09-29 16:25:45', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('6', '2', '编辑商品', 'pms:product:update', '', '2', '/admin/product/updateProduct', '2', 0, '2018-09-29 16:34:23', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('7', '2', '删除商品', 'pms:product:delete', '', '2', '/admin/product/delete', '2', 0, '2018-09-29 16:38:33', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('8', '4', '添加商品分类', 'pms:productCategory:create', '', '2', '/admin/productCate/create', '2', 0, '2018-09-29 16:43:23', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('9', '4', '修改商品分类', 'pms:productCategory:update', '', '2', '/admin/productCate/update', '2', 0, '2018-09-29 16:43:55', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('10', '4', '删除商品分类', 'pms:productCategory:delete', '', '2', '/admin/productAttr/delete', '2', 0, '2018-09-29 16:44:38', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('11', '5', '添加商品类型', 'pms:productAttribute:create', '', '2', '/admin/productAttr/create', '2', 0, '2018-09-29 16:45:25', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('12', '5', '修改商品类型', 'pms:productAttribute:update', '', '2', '/admin/productAttr/update', '2', 0, '2018-09-29 16:48:08', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('13', '5', '删除商品类型', 'pms:productAttribute:delete', '', '2', '/admin/productAttr/delete', '2', 0, '2018-09-29 16:48:44', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('14', '6', '添加品牌', 'pms:brand:create', '', '2', '/admin/brand/add', '2', 0, '2018-09-29 16:49:34', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('15', '6', '修改品牌', 'pms:brand:update', '', '2', '/admin/brand/update', '2', 0, '2018-09-29 16:50:55', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('16', '6', '删除品牌', 'pms:brand:delete', '', '2', '/admin/brand/delete', '2', 0, '2018-09-29 16:50:59', '1980-01-01 00:00:01');
INSERT INTO `admin_permission` VALUES ('17', '0', '首页', '', '', '0', '', '1', 0, '2018-09-29 16:51:57', '1980-01-01 00:00:01');

-- ----------------------------
-- Table structure for admin_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission_relation`;
CREATE TABLE `admin_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of admin_role_permission_relation
-- ----------------------------
INSERT INTO `admin_role_permission_relation` VALUES ('1', '1', '1', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('2', '1', '2', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('3', '1', '3', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('4', '1', '7', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('5', '1', '8', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('6', '2', '4', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('7', '2', '9', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('8', '2', '10', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('9', '2', '11', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('10', '3', '5', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('11', '3', '12', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('12', '3', '13', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('13', '3', '14', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('14', '4', '6', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('15', '4', '15', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('16', '4', '16', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_permission_relation` VALUES ('17', '4', '17', '2018-09-30 15:46:11', '2018-09-30 15:46:11');


DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) not null DEFAULT '0',
  `ip` varchar(64) not null DEFAULT '',
  `address` varchar(100) not null DEFAULT '',
  `user_agent` varchar(100) not null DEFAULT '' COMMENT '浏览器登录类型',
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';


-- ----------------------------
-- Table structure for admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_relation`;
CREATE TABLE `admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) not NULL default 0 comment '后台用户id',
  `role_id` bigint(20) not NULL default 0 comment '角色id',
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `admin_role_relation` VALUES ('13', '3', '1', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_relation` VALUES ('15', '3', '2', '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role_relation` VALUES ('16', '3', '4', '2018-09-30 15:46:11', '2018-09-30 15:46:11');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) not NULL default '' COMMENT '名称',
  `description` varchar(500) not NULL default '' COMMENT '描述',
  `admin_count` int(11) not NULL default '' COMMENT '后台用户数量',
  `status` int(1) not null DEFAULT 2 COMMENT '启用状态：1->禁用；2->启用',
  `sort` int(11) not null DEFAULT '0' COMMENT '排序',
  `created_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT '1980-01-01 00:00:01' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '商品管理员', '商品管理员', '0', 2, 0, '2018-09-30 15:46:11', '2018-09-30 15:46:11');
INSERT INTO `admin_role` VALUES ('2', '商品分类管理员', '商品分类管理员', '0', 2, 0, '2018-09-30 15:53:45', '2018-09-30 15:46:11');
INSERT INTO `admin_role` VALUES ('3', '商品类型管理员', '商品类型管理员', '0', 2, 0, '2018-09-30 15:53:45', '2018-09-30 15:46:11');
INSERT INTO `admin_role` VALUES ('4', '品牌管理员', '品牌管理员', '0', 2, 0, '2018-09-30 15:53:45', '2018-09-30 15:46:11');


-- ----------------------------
-- Table structure for admin_brand
-- ----------------------------
DROP TABLE IF EXISTS `admin_brand`;
CREATE TABLE `admin_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) not null DEFAULT '' COMMENT '品牌名称',
  `first_letter` varchar(8) not NULL default '' COMMENT '首字母',
  `sort` int(11) not null DEFAULT 0 COMMENT '排序',
  `factory_status` int(1) not null DEFAULT 0 COMMENT '是否为品牌制造商：0->不是；1->是',
  `show_status` int(1) not null DEFAULT 2 COMMENT '启用状态：1->禁用；2->启用',
  `product_count` int(11) not NULL default 0 COMMENT '产品数量',
  `product_comment_count` int(11) not NULL default 0 COMMENT '产品评论数量',
  `logo` varchar(255) not NULL default '' COMMENT '品牌logo',
  `big_pic` varchar(255) not NULL default '' COMMENT '专区大图',
  `brand_story` text COMMENT '品牌故事',
  `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='品牌表';

INSERT INTO `admin_brand` VALUES ('1', '万和', 'W', '0', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(5).jpg', '', 'Victoria\'s Secret的故事');
INSERT INTO `admin_brand` VALUES ('2', '三星', 'S', '100', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg', null, '三星的故事');
INSERT INTO `admin_brand` VALUES ('3', '华为', 'H', '100', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/17f2dd9756d9d333bee8e60ce8c03e4c_222_222.jpg', null, 'Victoria\'s Secret的故事');
INSERT INTO `admin_brand` VALUES ('4', '格力', 'G', '30', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg', null, 'Victoria\'s Secret的故事');
INSERT INTO `admin_brand` VALUES ('5', '方太', 'F', '20', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg', null, 'Victoria\'s Secret的故事');
INSERT INTO `admin_brand` VALUES ('6', '小米', 'M', '500', '1', '1', '100', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/1e34aef2a409119018a4c6258e39ecfb_222_222.png', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180518/5afd7778Nf7800b75.jpg', '小米手机的故事');
INSERT INTO `admin_brand` VALUES ('21', 'OPPO', 'O', '0', '1', '1', '88', '500', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg', '', 'string');
INSERT INTO `admin_brand` VALUES ('49', '七匹狼', 'S', '200', '1', '1', '77', '400', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg', null, 'BOOB的故事');
INSERT INTO `admin_brand` VALUES ('50', '海澜之家', 'H', '200', '1', '1', '66', '300', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg', '', '海澜之家的故事');
INSERT INTO `admin_brand` VALUES ('51', '苹果', 'A', '200', '1', '1', '55', '200', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', null, '苹果的故事');
INSERT INTO `admin_brand` VALUES ('58', 'NIKE', 'N', '0', '1', '1', '33', '100', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/timg (51).jpg', '', 'NIKE的故事');

-- ----------------------------
-- Table structure for admin_product
-- ----------------------------
DROP TABLE IF EXISTS `admin_product`;
CREATE TABLE `admin_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) not null DEFAULT 0 COMMENT '品牌id',
  `product_category_id` bigint(20) not null DEFAULT 0 COMMENT '商品类型id',
  `feight_template_id` bigint(20) not null DEFAULT 0 COMMENT '',
  `product_attribute_category_id` bigint(20) not null DEFAULT 0 COMMENT '商品属性类型id',
  `name` varchar(64) NOT NULL default '' COMMENT '商品名称',
  `pic` varchar(255) not NULL default '' COMMENT '商品图片',
  `product_sn` varchar(64) NOT NULL default '' COMMENT '货号',
  `delete_status` int(1) not NULL default 2 COMMENT '删除状态：1->已删除；2->未删除',
  `publish_status` int(1) not NULL default 1 COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) not NULL default 1 COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) not NULL default 1 COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) not NULL default 1 COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) not NULL default 0 COMMENT '排序',
  `sale` int(11) not NULL default 0 COMMENT '销量',
  `price` decimal(10,2) not NULL default '0.00' COMMENT '价格',
  `promotion_price` decimal(10,2) not NULL default '0.00' COMMENT '促销价格',
  `gift_growth` int(11) not null DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) not null DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) not NULL default '0' COMMENT '限制使用的积分数',
  `sub_title` varchar(255) not null DEFAULT '' COMMENT '副标题',
  `description` text COMMENT '商品描述',
  `original_price` decimal(10,2) not null DEFAULT '0.00' COMMENT '市场价',
  `stock` int(11) not null DEFAULT '0' COMMENT '库存',
  `low_stock` int(11) not null DEFAULT '0' COMMENT '库存预警值',
  `unit` varchar(16) not NULL default '' COMMENT '单位',
  `weight` decimal(10,2) not null DEFAULT '0.00' COMMENT '商品重量，默认为克',
  `preview_status` int(1) not NULL default 1 COMMENT '是否为预告商品：0->不是；1->是',
  `service_ids` varchar(64) not NULL default 1 COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) not NULL default '' COMMENT '关键词',
  `note` varchar(255) not NULL default '' COMMENT '备注',
  `album_pics` varchar(255) not null DEFAULT '' COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_title` varchar(255) not NULL default '' COMMENT '产品详情标题',
  `detail_desc` text COMMENT '产品详情描述',
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',
  `promotion_start_time` timestamp not null DEFAULT current_timestamp COMMENT '促销开始时间',
  `promotion_end_time` timestamp not NULL default current_timestamp COMMENT '促销结束时间',
  `promotion_per_limit` int(11) not NULL default 0 COMMENT '活动限购数量',
  `promotion_type` int(1) not NULL default 0 COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购',
  `product_category_name` varchar(255) not NULL default '' COMMENT '商品分类名称',
  `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品信息';
alter table `admin_product` add `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间';
alter table `admin_product` add `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间';

-- ----------------------------
-- Records of admin_product
-- ----------------------------
INSERT INTO `admin_product` VALUES ('1', '49', '7', '0', '0', '银色星芒刺绣网纱底裤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '1', '1', '1', '100', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '', '', '0', '七匹狼', '外套');
INSERT INTO `admin_product` VALUES ('2', '49', '7', '0', '0', '银色星芒刺绣网纱底裤2', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86578', '1', '1', '1', '1', '1', '1', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤2', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '<p>银色星芒刺绣网纱底裤</p>', '<p>银色星芒刺绣网纱底裤</p>', '', '', '', '0', '七匹狼', '外套');
INSERT INTO `admin_product` VALUES ('3', '1', '7', '0', '0', '银色星芒刺绣网纱底裤3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86579', '1', '1', '1', '1', '1', '1', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤3', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '', '', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('4', '1', '7', '0', '0', '银色星芒刺绣网纱底裤4', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86580', '1', '1', '1', '1', '1', '1', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤4', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '', '', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('5', '1', '7', '0', '0', '银色星芒刺绣网纱底裤5', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86581', '1', '0', '1', '1', '1', '1', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤5', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '', '', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('6', '1', '7', '0', '0', '银色星芒刺绣网纱底裤6', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86582', '1', '1', '1', '1', '1', '1', '0', '100.00', '', '0', '100', '', '111', '111', '120.00', '100', '20', '件', '1000.00', '0', '', '银色星芒刺绣网纱底裤6', '银色星芒刺绣网纱底裤', '', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '', '', '', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('7', '1', '7', '0', '1', '女式超柔软拉毛运动开衫', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('8', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('9', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('10', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('11', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('12', '1', '7', '0', '1', '女式超柔软拉毛运动开衫2', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('13', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('14', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('18', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('22', '6', '7', '0', '1', 'test', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', '', '1', '1', '0', '0', '0', '0', '0', '0.00', null, '0', '0', '0', 'test', '', '0.00', '100', '0', '', '0.00', '1', '1,2', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '外套');
INSERT INTO `admin_product` VALUES ('23', '6', '19', '0', '1', '毛衫测试', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', 'NO.1098', '1', '1', '1', '1', '0', '0', '0', '99.00', null, '99', '99', '1000', '毛衫测试11', 'xxx', '109.00', '100', '0', '件', '1000.00', '1', '1,2,3', '毛衫测试', '毛衫测试', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', '毛衫测试', '毛衫测试', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/155x54.bmp\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录bg1080.jpg\" width=\"500\" height=\"500\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录界面.jpg\" width=\"500\" height=\"500\" /></p>', '', null, null, '0', '2', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('24', '6', '7', '0', null, 'xxx', '', '', '1', '0', '0', '0', '0', '0', '0', '0.00', null, '0', '0', '0', 'xxx', '', '0.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '外套');
INSERT INTO `admin_product` VALUES ('26', '3', '19', '0', '3', '华为 HUAWEI P20 ', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '6946605', '0', '1', '1', '1', '0', '100', '0', '3788.00', null, '3788', '3788', '0', 'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待', '', '4288.00', '1000', '0', '件', '0.00', '1', '2,3,1', '', '', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg', '', '', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44f1cNf51f3bb0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa8Nfcf71c10.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa9N40e78ee0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f4N1c94bdda.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f5Nd30de41d.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5b10fb0eN0eb053fb.jpg\" /></p>', '', null, null, '0', '1', '华为', '手机通讯');
INSERT INTO `admin_product` VALUES ('27', '6', '19', '0', '3', '小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '7437788', '0', '1', '1', '1', '0', '0', '0', '2699.00', null, '2699', '2699', '0', '骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购', '小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', '2699.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b2254e8N414e6d3a.jpg\" width=\"500\" /></p>', '', null, null, '0', '3', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('28', '6', '19', '0', '3', '小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '7437789', '0', '1', '1', '1', '0', '0', '0', '649.00', null, '649', '649', '0', '8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购', '', '649.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '4', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('29', '51', '19', '0', '3', 'Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg', '7437799', '0', '1', '1', '0', '0', '0', '0', '5499.00', null, '5499', '5499', '0', '【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。', '', '5499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '苹果', '手机通讯');
INSERT INTO `admin_product` VALUES ('30', '50', '8', '0', '1', 'HLA海澜之家简约动物印花短袖T恤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg', 'HNTBJ2E042A', '0', '1', '1', '1', '0', '0', '0', '98.00', null, '0', '0', '0', '2018夏季新品微弹舒适新款短T男生 6月6日-6月20日，满300减30，参与互动赢百元礼券，立即分享赢大奖', '', '98.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('31', '50', '8', '0', '1', 'HLA海澜之家蓝灰花纹圆领针织布短袖T恤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg', 'HNTBJ2E080A', '0', '1', '0', '0', '0', '0', '0', '98.00', null, '0', '0', '0', '2018夏季新品短袖T恤男HNTBJ2E080A 蓝灰花纹80 175/92A/L80A 蓝灰花纹80 175/92A/L', '', '98.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('32', '50', '8', '0', null, 'HLA海澜之家短袖T恤男基础款', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a51eb88Na4797877.jpg', 'HNTBJ2E153A', '0', '1', '0', '0', '0', '0', '0', '68.00', null, '0', '0', '0', 'HLA海澜之家短袖T恤男基础款简约圆领HNTBJ2E153A藏青(F3)175/92A(50)', '', '68.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('33', '6', '35', '0', null, '小米（MI）小米电视4A ', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg', '4609652', '0', '1', '0', '0', '0', '0', '0', '2499.00', null, '0', '0', '0', '小米（MI）小米电视4A 55英寸 L55M5-AZ/L55M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视', '', '2499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '手机数码');
INSERT INTO `admin_product` VALUES ('34', '6', '35', '0', null, '小米（MI）小米电视4A 65英寸', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b028530N51eee7d4.jpg', '4609660', '0', '1', '0', '0', '0', '0', '0', '3999.00', null, '0', '0', '0', ' L65M5-AZ/L65M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视', '', '3999.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '手机数码');
INSERT INTO `admin_product` VALUES ('35', '58', '29', '0', null, '耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg', '6799342', '0', '1', '0', '0', '0', '0', '0', '369.00', null, '0', '0', '0', '耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码', '', '369.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', 'NIKE', '男鞋');
INSERT INTO `admin_product` VALUES ('36', '58', '29', '0', null, '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg', '6799345', '0', '1', '1', '1', '0', '0', '0', '499.00', null, '0', '0', '0', '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', '', '499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', 'NIKE', '男鞋');


DROP TABLE IF EXISTS `home_brand`;
CREATE TABLE `home_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) not null DEFAULT '0' COMMENT '品牌id',
  `brand_name` varchar(64) not null DEFAULT '' COMMENT '品牌名称',
  `recommend_status` int(1) not null DEFAULT 1 COMMENT '推荐状态:1表示推荐,2表示不推荐',
  `delete_status` tinyint not null DEFAULT 2 COMMENT '1表示被删除,2表示正常',
  `sort` int(11) not null DEFAULT '0' COMMENT '排序',
  `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='首页推荐品牌表';

-- ----------------------------
-- Records of home_brand
-- ----------------------------
INSERT INTO `home_brand` VALUES ('1', '1', '万和', '1', '200');
INSERT INTO `home_brand` VALUES ('2', '2', '三星', '1', '0');
INSERT INTO `home_brand` VALUES ('6', '6', '小米', '1', '300');
INSERT INTO `home_brand` VALUES ('8', '5', '方太', '1', '100');
INSERT INTO `home_brand` VALUES ('32', '50', '海澜之家', '1', '0');
INSERT INTO `home_brand` VALUES ('33', '51', '苹果', '1', '0');
INSERT INTO `home_brand` VALUES ('35', '3', '华为', '1', '0');
INSERT INTO `home_brand` VALUES ('36', '4', '格力', '1', '0');
INSERT INTO `home_brand` VALUES ('37', '5', '方太', '1', '0');
INSERT INTO `home_brand` VALUES ('38', '1', '万和', '1', '0');
INSERT INTO `home_brand` VALUES ('39', '21', 'OPPO', '1', '0');


-- ----------------------------
-- Table structure for admin_product
-- ----------------------------
DROP TABLE IF EXISTS `admin_product`;
CREATE TABLE `admin_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) not NULL default 0 COMMENT '品牌id',
  `product_category_id` bigint(20) not NULL default 0 COMMENT '商品分类id',
  `feight_template_id` bigint(20) not NULL default 0,
  `product_attribute_category_id` bigint(20) not NULL default 0 COMMENT '商品属性类型id',
  `name` varchar(64) NOT NULL default '' COMMENT '商品名称',
  `pic` varchar(255) not NULL default '' COMMENT '商品图片',
  `product_sn` varchar(64) NOT NULL default '' COMMENT '货号',
  `delete_status` int(1) not NULL default 2 COMMENT '删除状态：1->已删除；2->未删除',
  `publish_status` int(1) not NULL default 1 COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) not NULL default 1 COMMENT '新品状态:0->不是新品；1->新品',
  `recommend_status` int(1) not NULL default 1 COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) not NULL default 1 COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) not NULL default 0 COMMENT '排序',
  `sale` int(11) not NULL default 0 COMMENT '销量',
  `price` decimal(10,2) not NULL default '0.00' COMMENT '价格',
  `promotion_price` decimal(10,2) not NULL default '0.00' COMMENT '促销价格',
  `gift_growth` int(11) not null DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) not null DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) not NULL default '0' COMMENT '限制使用的积分数',
  `sub_title` varchar(255) not NULL default '' COMMENT '副标题',
  `description` text COMMENT '商品描述',
  `original_price` decimal(10,2) not NULL default '0.00' COMMENT '市场价',
  `stock` int(11) not NULL default '0' COMMENT '库存',
  `low_stock` int(11) not NULL default '0' COMMENT '库存预警值',
  `unit` varchar(16) not NULL default '' COMMENT '单位',
  `weight` decimal(10,2) not NULL default '0.00' COMMENT '商品重量，默认为克',
  `preview_status` tinyint not NULL default 1 COMMENT '是否为预告商品：0->不是；1->是',
  `service_ids` varchar(64) not NULL default '' COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) not NULL default '' COMMENT '关键词',
  `note` varchar(255) not NULL default '' COMMENT '备注',
  `album_pics` varchar(255) not NULL default '' COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_title` varchar(255) not NULL default '' COMMENT '商品详情标题',
  `detail_desc` text COMMENT '产品详情描述',
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',
  `promotion_start_time` datetime not NULL default '1980-01-01 23:59:59' COMMENT '促销开始时间',
  `promotion_end_time` datetime not NULL default '1980-01-01 23:59:59' COMMENT '促销结束时间',
  `promotion_per_limit` int(11) not NULL default '0' COMMENT '活动限购数量',
  `promotion_type` int(1) not NULL default '0' COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购',
  `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  key `idx_name` (`name`),
  key `idx_keywords` (`keywords`),
  key `idx_product_sn` (`product_sn`),
  key `idx_product_category_id` (`product_category_id`),
  key `idx_brand_id` (`brand_id`),
  key `idx_promotion_start_time_promotion_end_time` (`promotion_start_time`, `promotion_end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品信息';

-- ----------------------------
-- Records of admin_product
-- ----------------------------
INSERT INTO `admin_product` VALUES ('1', '49', '7', '0', '0', '银色星芒刺绣网纱底裤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '1', '1', '1', '100', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, null, null, '0', '七匹狼', '外套');
INSERT INTO `admin_product` VALUES ('2', '49', '7', '0', '0', '银色星芒刺绣网纱底裤2', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86578', '1', '1', '1', '1', '1', '1', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤2', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '<p>银色星芒刺绣网纱底裤</p>', '<p>银色星芒刺绣网纱底裤</p>', null, null, null, '0', '七匹狼', '外套');
INSERT INTO `admin_product` VALUES ('3', '1', '7', '0', '0', '银色星芒刺绣网纱底裤3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86579', '1', '1', '1', '1', '1', '1', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤3', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, null, null, '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('4', '1', '7', '0', '0', '银色星芒刺绣网纱底裤4', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86580', '1', '1', '1', '1', '1', '1', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤4', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, null, null, '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('5', '1', '7', '0', '0', '银色星芒刺绣网纱底裤5', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86581', '1', '0', '1', '1', '1', '1', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤5', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, null, null, '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('6', '1', '7', '0', '0', '银色星芒刺绣网纱底裤6', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86582', '1', '1', '1', '1', '1', '1', '0', '100.00', null, '0', '100', null, '111', '111', '120.00', '100', '20', '件', '1000.00', '0', null, '银色星芒刺绣网纱底裤6', '银色星芒刺绣网纱底裤', null, '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', '银色星芒刺绣网纱底裤', null, null, null, '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('7', '1', '7', '0', '1', '女式超柔软拉毛运动开衫', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('8', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('9', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('10', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '0', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('11', '1', '7', '0', '1', '女式超柔软拉毛运动开衫1', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('12', '1', '7', '0', '1', '女式超柔软拉毛运动开衫2', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('13', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '1', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('14', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('18', '1', '7', '0', '1', '女式超柔软拉毛运动开衫3', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png', 'No86577', '1', '0', '0', '1', '0', '0', '0', '249.00', '0.00', '0', '100', '0', '匠心剪裁，垂感质地', '匠心剪裁，垂感质地', '299.00', '100', '0', '件', '0.00', '0', 'string', '女式超柔软拉毛运动开衫', 'string', 'string', 'string', 'string', 'string', 'string', '2018-04-26 10:41:03', '2018-04-26 10:41:03', '0', '0', '万和', '外套');
INSERT INTO `admin_product` VALUES ('22', '6', '7', '0', '1', 'test', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', '', '1', '1', '0', '0', '0', '0', '0', '0.00', null, '0', '0', '0', 'test', '', '0.00', '100', '0', '', '0.00', '1', '1,2', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '外套');
INSERT INTO `admin_product` VALUES ('23', '6', '19', '0', '1', '毛衫测试', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', 'NO.1098', '1', '1', '1', '1', '0', '0', '0', '99.00', null, '99', '99', '1000', '毛衫测试11', 'xxx', '109.00', '100', '0', '件', '1000.00', '1', '1,2,3', '毛衫测试', '毛衫测试', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg', '毛衫测试', '毛衫测试', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/155x54.bmp\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录bg1080.jpg\" width=\"500\" height=\"500\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/APP登录界面.jpg\" width=\"500\" height=\"500\" /></p>', '', null, null, '0', '2', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('24', '6', '7', '0', null, 'xxx', '', '', '1', '0', '0', '0', '0', '0', '0', '0.00', null, '0', '0', '0', 'xxx', '', '0.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '外套');
INSERT INTO `admin_product` VALUES ('26', '3', '19', '0', '3', '华为 HUAWEI P20 ', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '6946605', '0', '1', '1', '1', '0', '100', '0', '3788.00', null, '3788', '3788', '0', 'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待', '', '4288.00', '1000', '0', '件', '0.00', '1', '2,3,1', '', '', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg', '', '', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44f1cNf51f3bb0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa8Nfcf71c10.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa9N40e78ee0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f4N1c94bdda.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f5Nd30de41d.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5b10fb0eN0eb053fb.jpg\" /></p>', '', null, null, '0', '1', '华为', '手机通讯');
INSERT INTO `admin_product` VALUES ('27', '6', '19', '0', '3', '小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '7437788', '0', '1', '1', '1', '0', '0', '0', '2699.00', null, '2699', '2699', '0', '骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购', '小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', '2699.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b2254e8N414e6d3a.jpg\" width=\"500\" /></p>', '', null, null, '0', '3', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('28', '6', '19', '0', '3', '小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '7437789', '0', '1', '1', '1', '0', '0', '0', '649.00', null, '649', '649', '0', '8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购', '', '649.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '4', '小米', '手机通讯');
INSERT INTO `admin_product` VALUES ('29', '51', '19', '0', '3', 'Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg', '7437799', '0', '1', '1', '0', '0', '0', '0', '5499.00', null, '5499', '5499', '0', '【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。', '', '5499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '苹果', '手机通讯');
INSERT INTO `admin_product` VALUES ('30', '50', '8', '0', '1', 'HLA海澜之家简约动物印花短袖T恤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg', 'HNTBJ2E042A', '0', '1', '1', '1', '0', '0', '0', '98.00', null, '0', '0', '0', '2018夏季新品微弹舒适新款短T男生 6月6日-6月20日，满300减30，参与互动赢百元礼券，立即分享赢大奖', '', '98.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('31', '50', '8', '0', '1', 'HLA海澜之家蓝灰花纹圆领针织布短袖T恤', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg', 'HNTBJ2E080A', '0', '1', '0', '0', '0', '0', '0', '98.00', null, '0', '0', '0', '2018夏季新品短袖T恤男HNTBJ2E080A 蓝灰花纹80 175/92A/L80A 蓝灰花纹80 175/92A/L', '', '98.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('32', '50', '8', '0', null, 'HLA海澜之家短袖T恤男基础款', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a51eb88Na4797877.jpg', 'HNTBJ2E153A', '0', '1', '0', '0', '0', '0', '0', '68.00', null, '0', '0', '0', 'HLA海澜之家短袖T恤男基础款简约圆领HNTBJ2E153A藏青(F3)175/92A(50)', '', '68.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '海澜之家', 'T恤');
INSERT INTO `admin_product` VALUES ('33', '6', '35', '0', null, '小米（MI）小米电视4A ', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg', '4609652', '0', '1', '0', '0', '0', '0', '0', '2499.00', null, '0', '0', '0', '小米（MI）小米电视4A 55英寸 L55M5-AZ/L55M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视', '', '2499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '手机数码');
INSERT INTO `admin_product` VALUES ('34', '6', '35', '0', null, '小米（MI）小米电视4A 65英寸', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b028530N51eee7d4.jpg', '4609660', '0', '1', '0', '0', '0', '0', '0', '3999.00', null, '0', '0', '0', ' L65M5-AZ/L65M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视', '', '3999.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', '小米', '手机数码');
INSERT INTO `admin_product` VALUES ('35', '58', '29', '0', null, '耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg', '6799342', '0', '1', '0', '0', '0', '0', '0', '369.00', null, '0', '0', '0', '耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码', '', '369.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', 'NIKE', '男鞋');
INSERT INTO `admin_product` VALUES ('36', '58', '29', '0', null, '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg', '6799345', '0', '1', '1', '1', '0', '0', '0', '499.00', null, '0', '0', '0', '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', '', '499.00', '100', '0', '', '0.00', '0', '', '', '', '', '', '', '', '', null, null, '0', '0', 'NIKE', '男鞋');



-- ----------------------------
-- Table structure for admin_product_verify_record
-- ----------------------------
DROP TABLE IF EXISTS `admin_product_verify_record`;
CREATE TABLE `admin_product_verify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) not NULL default '0' COMMENT '商品id',
  `verify_member_id` bigint(20) not NULL default '0' COMMENT '审核人id',
  `status` int(1) not NULL default '1' COMMENT '审核状态,默认值为1,表示审核通过',
  `detail` varchar(255) not NULL default '' COMMENT '反馈详情',
  `created_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录创建时间',
  `updated_at` timestamp not null default '1980-01-01 00:00:00' COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  key `idx_product_id_verify_member_id` (`product_id`, `verify_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品审核记录';