/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : spring_boot_mybatis_test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-05-15 15:06:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('3', 'admin', 'ADMIN');
INSERT INTO `authorities` VALUES ('2', 'admin', 'USER');
INSERT INTO `authorities` VALUES ('1', 'user', 'USER');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='市级信息';

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '石家庄', '河北');
INSERT INTO `city` VALUES ('2', '邯郸', '河北');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `country_name` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='国家信息';

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', 'Angola', 'AO-');
INSERT INTO `country` VALUES ('2', 'Afghanistan', 'AF');
INSERT INTO `country` VALUES ('3', 'Albania', 'AL');
INSERT INTO `country` VALUES ('4', 'Algeria', 'DZ');
INSERT INTO `country` VALUES ('5', 'Andorra', 'AD');
INSERT INTO `country` VALUES ('6', 'Anguilla', 'AI');
INSERT INTO `country` VALUES ('7', 'Antigua and Barbuda', 'AG');
INSERT INTO `country` VALUES ('13', 'Bahamas', 'BS');
INSERT INTO `country` VALUES ('14', 'Bahrain', 'BH');
INSERT INTO `country` VALUES ('15', 'Bangladesh', 'BD');
INSERT INTO `country` VALUES ('16', 'Barbados', 'BB');
INSERT INTO `country` VALUES ('17', 'Belarus', 'BY');
INSERT INTO `country` VALUES ('18', 'Belgium', 'BE');
INSERT INTO `country` VALUES ('19', 'Belize', 'BZ');
INSERT INTO `country` VALUES ('20', 'Benin', 'BJ');
INSERT INTO `country` VALUES ('21', 'Bermuda Is.', 'BM');
INSERT INTO `country` VALUES ('22', 'Bolivia', 'BO');
INSERT INTO `country` VALUES ('23', 'Botswana', 'BW');
INSERT INTO `country` VALUES ('24', 'Brazil', 'BR');
INSERT INTO `country` VALUES ('25', 'Brunei', 'BN');
INSERT INTO `country` VALUES ('26', 'Bulgaria', 'BG');
INSERT INTO `country` VALUES ('27', 'Burkina-faso', 'BF');
INSERT INTO `country` VALUES ('28', 'Burma', 'MM');
INSERT INTO `country` VALUES ('29', 'Burundi', 'BI');
INSERT INTO `country` VALUES ('30', 'Cameroon', 'CM');
INSERT INTO `country` VALUES ('31', 'Canada', 'CA');
INSERT INTO `country` VALUES ('32', 'Central African Republic', 'CF');
INSERT INTO `country` VALUES ('33', 'Chad', 'TD');
INSERT INTO `country` VALUES ('34', 'Chile', 'CL');
INSERT INTO `country` VALUES ('35', 'China', 'CN');
INSERT INTO `country` VALUES ('36', 'Colombia', 'CO');
INSERT INTO `country` VALUES ('37', 'Congo', 'CG');
INSERT INTO `country` VALUES ('38', 'Cook Is.', 'CK');
INSERT INTO `country` VALUES ('39', 'Costa Rica', 'CR');
INSERT INTO `country` VALUES ('40', 'Cuba', 'CU');
INSERT INTO `country` VALUES ('41', 'Cyprus', 'CY');
INSERT INTO `country` VALUES ('42', 'Czech Republic', 'CZ');
INSERT INTO `country` VALUES ('43', 'Denmark', 'DK');
INSERT INTO `country` VALUES ('44', 'Djibouti', 'DJ');
INSERT INTO `country` VALUES ('45', 'Dominica Rep.', 'DO');
INSERT INTO `country` VALUES ('46', 'Ecuador', 'EC');
INSERT INTO `country` VALUES ('47', 'Egypt', 'EG');
INSERT INTO `country` VALUES ('48', 'EI Salvador', 'SV');
INSERT INTO `country` VALUES ('49', 'Estonia', 'EE');
INSERT INTO `country` VALUES ('50', 'Ethiopia', 'ET');
INSERT INTO `country` VALUES ('51', 'Fiji', 'FJ');
INSERT INTO `country` VALUES ('52', 'Finland', 'FI');
INSERT INTO `country` VALUES ('53', 'France', 'FR');
INSERT INTO `country` VALUES ('54', 'French Guiana', 'GF');
INSERT INTO `country` VALUES ('55', 'Gabon', 'GA');
INSERT INTO `country` VALUES ('56', 'Gambia', 'GM');
INSERT INTO `country` VALUES ('57', 'Georgia', 'GE');
INSERT INTO `country` VALUES ('58', 'Germany', 'DE');
INSERT INTO `country` VALUES ('59', 'Ghana', 'GH');
INSERT INTO `country` VALUES ('60', 'Gibraltar', 'GI');
INSERT INTO `country` VALUES ('61', 'Greece', 'GR');
INSERT INTO `country` VALUES ('62', 'Grenada', 'GD');
INSERT INTO `country` VALUES ('63', 'Guam', 'GU');
INSERT INTO `country` VALUES ('64', 'Guatemala', 'GT');
INSERT INTO `country` VALUES ('65', 'Guinea', 'GN');
INSERT INTO `country` VALUES ('66', 'Guyana', 'GY');
INSERT INTO `country` VALUES ('67', 'Haiti', 'HT');
INSERT INTO `country` VALUES ('68', 'Honduras', 'HN');
INSERT INTO `country` VALUES ('69', 'Hongkong', 'HK');
INSERT INTO `country` VALUES ('70', 'Hungary', 'HU');
INSERT INTO `country` VALUES ('71', 'Iceland', 'IS');
INSERT INTO `country` VALUES ('72', 'India', 'IN');
INSERT INTO `country` VALUES ('73', 'Indonesia', 'ID');
INSERT INTO `country` VALUES ('74', 'Iran', 'IR');
INSERT INTO `country` VALUES ('75', 'Iraq', 'IQ');
INSERT INTO `country` VALUES ('76', 'Ireland', 'IE');
INSERT INTO `country` VALUES ('77', 'Israel', 'IL');
INSERT INTO `country` VALUES ('78', 'Italy', 'IT');
INSERT INTO `country` VALUES ('79', 'Jamaica', 'JM');
INSERT INTO `country` VALUES ('80', 'Japan', 'JP');
INSERT INTO `country` VALUES ('81', 'Jordan', 'JO');
INSERT INTO `country` VALUES ('82', 'Kampuchea (Cambodia )', 'KH');
INSERT INTO `country` VALUES ('83', 'Kazakstan', 'KZ');
INSERT INTO `country` VALUES ('84', 'Kenya', 'KE');
INSERT INTO `country` VALUES ('85', 'Korea', 'KR');
INSERT INTO `country` VALUES ('86', 'Kuwait', 'KW');
INSERT INTO `country` VALUES ('87', 'Kyrgyzstan', 'KG');
INSERT INTO `country` VALUES ('88', 'Laos', 'LA');
INSERT INTO `country` VALUES ('89', 'Latvia', 'LV');
INSERT INTO `country` VALUES ('90', 'Lebanon', 'LB');
INSERT INTO `country` VALUES ('91', 'Lesotho', 'LS');
INSERT INTO `country` VALUES ('92', 'Liberia', 'LR');
INSERT INTO `country` VALUES ('93', 'Libya', 'LY');
INSERT INTO `country` VALUES ('94', 'Liechtenstein', 'LI');
INSERT INTO `country` VALUES ('95', 'Lithuania', 'LT');
INSERT INTO `country` VALUES ('96', 'Luxembourg', 'LU');
INSERT INTO `country` VALUES ('97', 'Macao', 'MO');
INSERT INTO `country` VALUES ('98', 'Madagascar', 'MG');
INSERT INTO `country` VALUES ('99', 'Malawi', 'MW');
INSERT INTO `country` VALUES ('100', 'Malaysia', 'MY');
INSERT INTO `country` VALUES ('101', 'Maldives', 'MV');
INSERT INTO `country` VALUES ('102', 'Mali', 'ML');
INSERT INTO `country` VALUES ('103', 'Malta', 'MT');
INSERT INTO `country` VALUES ('104', 'Mauritius', 'MU');
INSERT INTO `country` VALUES ('105', 'Mexico', 'MX');
INSERT INTO `country` VALUES ('106', 'Moldova, Republic of', 'MD');
INSERT INTO `country` VALUES ('107', 'Monaco', 'MC');
INSERT INTO `country` VALUES ('108', 'Mongolia', 'MN');
INSERT INTO `country` VALUES ('109', 'Montserrat Is', 'MS');
INSERT INTO `country` VALUES ('110', 'Morocco', 'MA');
INSERT INTO `country` VALUES ('111', 'Mozambique', 'MZ');
INSERT INTO `country` VALUES ('112', 'Namibia', 'NA');
INSERT INTO `country` VALUES ('113', 'Nauru', 'NR');
INSERT INTO `country` VALUES ('114', 'Nepal', 'NP');
INSERT INTO `country` VALUES ('115', 'Netherlands', 'NL');
INSERT INTO `country` VALUES ('116', 'New Zealand', 'NZ');
INSERT INTO `country` VALUES ('117', 'Nicaragua', 'NI');
INSERT INTO `country` VALUES ('118', 'Niger', 'NE');
INSERT INTO `country` VALUES ('119', 'Nigeria', 'NG');
INSERT INTO `country` VALUES ('120', 'North Korea', 'KP');
INSERT INTO `country` VALUES ('121', 'Norway', 'NO');
INSERT INTO `country` VALUES ('122', 'Oman', 'OM');
INSERT INTO `country` VALUES ('123', 'Pakistan', 'PK');
INSERT INTO `country` VALUES ('124', 'Panama', 'PA');
INSERT INTO `country` VALUES ('125', 'Papua New Cuinea', 'PG');
INSERT INTO `country` VALUES ('126', 'Paraguay', 'PY');
INSERT INTO `country` VALUES ('127', 'Peru', 'PE');
INSERT INTO `country` VALUES ('128', 'Philippines', 'PH');
INSERT INTO `country` VALUES ('129', 'Poland', 'PL');
INSERT INTO `country` VALUES ('130', 'French Polynesia', 'PF');
INSERT INTO `country` VALUES ('131', 'Portugal', 'PT');
INSERT INTO `country` VALUES ('132', 'Puerto Rico', 'PR');
INSERT INTO `country` VALUES ('133', 'Qatar', 'QA');
INSERT INTO `country` VALUES ('134', 'Romania', 'RO');
INSERT INTO `country` VALUES ('135', 'Russia', 'RU');
INSERT INTO `country` VALUES ('136', 'Saint Lueia', 'LC');
INSERT INTO `country` VALUES ('137', 'Saint Vincent', 'VC');
INSERT INTO `country` VALUES ('138', 'San Marino', 'SM');
INSERT INTO `country` VALUES ('139', 'Sao Tome and Principe', 'ST');
INSERT INTO `country` VALUES ('140', 'Saudi Arabia', 'SA');
INSERT INTO `country` VALUES ('141', 'Senegal', 'SN');
INSERT INTO `country` VALUES ('142', 'Seychelles', 'SC');
INSERT INTO `country` VALUES ('143', 'Sierra Leone', 'SL');
INSERT INTO `country` VALUES ('144', 'Singapore', 'SG');
INSERT INTO `country` VALUES ('145', 'Slovakia', 'SK');
INSERT INTO `country` VALUES ('146', 'Slovenia', 'SI');
INSERT INTO `country` VALUES ('147', 'Solomon Is', 'SB');
INSERT INTO `country` VALUES ('148', 'Somali', 'SO');
INSERT INTO `country` VALUES ('149', 'South Africa', 'ZA');
INSERT INTO `country` VALUES ('150', 'Spain', 'ES');
INSERT INTO `country` VALUES ('151', 'Sri Lanka', 'LK');
INSERT INTO `country` VALUES ('152', 'St.Lucia', 'LC');
INSERT INTO `country` VALUES ('153', 'St.Vincent', 'VC');
INSERT INTO `country` VALUES ('154', 'Sudan', 'SD');
INSERT INTO `country` VALUES ('155', 'Suriname', 'SR');
INSERT INTO `country` VALUES ('156', 'Swaziland', 'SZ');
INSERT INTO `country` VALUES ('157', 'Sweden', 'SE');
INSERT INTO `country` VALUES ('158', 'Switzerland', 'CH');
INSERT INTO `country` VALUES ('159', 'Syria', 'SY');
INSERT INTO `country` VALUES ('160', 'Taiwan', 'TW');
INSERT INTO `country` VALUES ('161', 'Tajikstan', 'TJ');
INSERT INTO `country` VALUES ('162', 'Tanzania', 'TZ');
INSERT INTO `country` VALUES ('163', 'Thailand', 'TH');
INSERT INTO `country` VALUES ('164', 'Togo', 'TG');
INSERT INTO `country` VALUES ('165', 'Tonga', 'TO');
INSERT INTO `country` VALUES ('166', 'Trinidad and Tobago', 'TT');
INSERT INTO `country` VALUES ('167', 'Tunisia', 'TN');
INSERT INTO `country` VALUES ('168', 'Turkey', 'TR');
INSERT INTO `country` VALUES ('169', 'Turkmenistan', 'TM');
INSERT INTO `country` VALUES ('170', 'Uganda', 'UG');
INSERT INTO `country` VALUES ('171', 'Ukraine', 'UA');
INSERT INTO `country` VALUES ('172', 'United Arab Emirates', 'AE');
INSERT INTO `country` VALUES ('173', 'United Kiongdom', 'GB');
INSERT INTO `country` VALUES ('174', 'United States of America', 'US');
INSERT INTO `country` VALUES ('175', 'Uruguay', 'UY');
INSERT INTO `country` VALUES ('176', 'Uzbekistan', 'UZ');
INSERT INTO `country` VALUES ('177', 'Venezuela', 'VE');
INSERT INTO `country` VALUES ('178', 'Vietnam', 'VN');
INSERT INTO `country` VALUES ('179', 'Yemen', 'YE');
INSERT INTO `country` VALUES ('180', 'Yugoslavia', 'YU');
INSERT INTO `country` VALUES ('181', 'Zimbabwe', 'ZW');
INSERT INTO `country` VALUES ('182', 'Zaire', 'ZR');
INSERT INTO `country` VALUES ('183', 'Zambia', 'ZM');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', 'user');
INSERT INTO `groups` VALUES ('2', 'admin');
INSERT INTO `groups` VALUES ('3', 'ANONYMOUS');

-- ----------------------------
-- Table structure for group_authorities
-- ----------------------------
DROP TABLE IF EXISTS `group_authorities`;
CREATE TABLE `group_authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_authorities_group` (`group_id`),
  CONSTRAINT `fk_group_authorities_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_authorities
-- ----------------------------
INSERT INTO `group_authorities` VALUES ('1', '1', 'USER');
INSERT INTO `group_authorities` VALUES ('2', '2', 'ADMIN');
INSERT INTO `group_authorities` VALUES ('3', '2', 'USER');

-- ----------------------------
-- Table structure for group_members
-- ----------------------------
DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_members_group` (`group_id`),
  CONSTRAINT `fk_group_members_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_members
-- ----------------------------
INSERT INTO `group_members` VALUES ('1', 'user', '1');
INSERT INTO `group_members` VALUES ('2', 'admin', '1');
INSERT INTO `group_members` VALUES ('3', 'admin', '2');

-- ----------------------------
-- Table structure for group_resource
-- ----------------------------
DROP TABLE IF EXISTS `group_resource`;
CREATE TABLE `group_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_resource
-- ----------------------------
INSERT INTO `group_resource` VALUES ('1', '1', '1');
INSERT INTO `group_resource` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `USERNAME` varchar(64) DEFAULT NULL,
  `SERIES` varchar(64) NOT NULL,
  `TOKEN` varchar(64) DEFAULT NULL,
  `LAST_USED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Remember me 持久化';

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorities`;
CREATE TABLE `sys_authorities` (
  `AUTHORITY_ID` varchar(100) NOT NULL,
  `AUTHORITY_MARK` varchar(100) DEFAULT NULL,
  `AUTHORITY_NAME` varchar(100) NOT NULL,
  `AUTHORITY_DESC` varchar(200) DEFAULT NULL,
  `MESSAGE` varchar(100) DEFAULT NULL,
  `ENABLE` decimal(8,0) DEFAULT NULL,
  `ISSYS` decimal(8,0) DEFAULT NULL,
  `MODULE_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of sys_authorities
-- ----------------------------
INSERT INTO `sys_authorities` VALUES ('1', 'USER', 'AUTH_PASSWORD_MODIFY', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_authorities_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorities_resources`;
CREATE TABLE `sys_authorities_resources` (
  `ID` varchar(100) NOT NULL,
  `RESOURCE_ID` varchar(100) NOT NULL,
  `AUTHORITY_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源';

-- ----------------------------
-- Records of sys_authorities_resources
-- ----------------------------

-- ----------------------------
-- Table structure for sys_modules
-- ----------------------------
DROP TABLE IF EXISTS `sys_modules`;
CREATE TABLE `sys_modules` (
  `MODULE_ID` varchar(100) NOT NULL,
  `MODULE_NAME` varchar(100) NOT NULL,
  `MODULE_DESC` varchar(200) DEFAULT NULL,
  `MODULE_TYPE` varchar(100) DEFAULT NULL,
  `PARENT` varchar(100) DEFAULT NULL,
  `MODULE_URL` varchar(100) DEFAULT NULL,
  `I_LEVEL` decimal(8,0) DEFAULT NULL COMMENT '1',
  `LEAF` decimal(8,0) DEFAULT NULL,
  `APPLICATION` varchar(100) DEFAULT NULL,
  `CONTROLLER` varchar(100) DEFAULT NULL,
  `ENABLE` decimal(1,0) DEFAULT NULL,
  `PRIORITY` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块';

-- ----------------------------
-- Records of sys_modules
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `RESOURCE_ID` varchar(100) NOT NULL,
  `RESOURCE_TYPE` varchar(100) DEFAULT NULL COMMENT 'URL,METHOD',
  `RESOURCE_NAME` varchar(100) DEFAULT NULL,
  `RESOURCE_DESC` varchar(200) DEFAULT NULL,
  `RESOURCE_PATH` varchar(200) DEFAULT NULL,
  `PRIORITY` varchar(100) DEFAULT NULL,
  `ENABLE` decimal(8,0) DEFAULT NULL,
  `ISSYS` decimal(8,0) DEFAULT NULL,
  `MODULE_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resources
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `ROLE_DESC` varchar(200) DEFAULT NULL,
  `ENABLE` decimal(8,0) DEFAULT NULL,
  `ISSYS` decimal(8,0) DEFAULT NULL,
  `MODULE_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', 'ADMIN', null, null, null, null);
INSERT INTO `sys_roles` VALUES ('2', 'USER', null, null, null, null);

-- ----------------------------
-- Table structure for sys_roles_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_authorities`;
CREATE TABLE `sys_roles_authorities` (
  `ID` varchar(100) NOT NULL,
  `AUTHORITY_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

-- ----------------------------
-- Records of sys_roles_authorities
-- ----------------------------
INSERT INTO `sys_roles_authorities` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_roles_moudles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_moudles`;
CREATE TABLE `sys_roles_moudles` (
  `ID` varchar(100) NOT NULL,
  `MODULE_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='控制角色对模块的访问权，主要用于生成菜单';

-- ----------------------------
-- Records of sys_roles_moudles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `DT_CREATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_LOGIN` datetime DEFAULT NULL,
  `DEADLINE` datetime DEFAULT NULL,
  `LOGIN_IP` varchar(100) DEFAULT NULL,
  `V_QZJGID` varchar(100) DEFAULT NULL,
  `V_QZJGMC` varchar(100) DEFAULT NULL,
  `DEP_ID` varchar(100) DEFAULT NULL,
  `DEP_NAME` varchar(100) DEFAULT NULL,
  `ENABLED` tinyint(1) DEFAULT '1',
  `ACCOUNT_NON_EXPIRED` tinyint(1) DEFAULT '1',
  `ACCOUNT_NON_LOCKED` tinyint(1) DEFAULT '1',
  `CREDENTIALS_NON_EXPIRED` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', 'admin', '123456', '2017-05-11 13:58:36', null, null, null, null, null, null, null, '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) NOT NULL,
  `USER_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('1', '1', '1');
INSERT INTO `sys_users_roles` VALUES ('2', '2', '1');

-- ----------------------------
-- Table structure for traffic_statistics
-- ----------------------------
DROP TABLE IF EXISTS `traffic_statistics`;
CREATE TABLE `traffic_statistics` (
  `IP` varchar(255) DEFAULT NULL COMMENT '访问者IP地址',
  `SCREEN_LOCATION` varchar(255) DEFAULT NULL COMMENT '屏位',
  `AREA` varchar(255) DEFAULT NULL COMMENT '区域',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `URL` varchar(255) DEFAULT NULL COMMENT '当前访问地址',
  `CLIENT` varchar(255) DEFAULT NULL COMMENT '终端（PC\\安装、IOS）',
  `CLIENT_VERSION` varchar(255) DEFAULT NULL COMMENT '客户端版本',
  `BROWSER` varchar(255) DEFAULT NULL COMMENT '浏览器类型',
  `BROWSER_VERSION` varchar(255) DEFAULT NULL COMMENT '浏览器版本',
  `LOGIN_CODE` varchar(255) DEFAULT NULL COMMENT '登录信息主键  当前记录关联的登录者',
  `PAGE` varchar(255) DEFAULT NULL COMMENT '访问页面：首页、登录等',
  `UUID` varchar(255) NOT NULL COMMENT '主键',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traffic_statistics
-- ----------------------------
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:23', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008180703563776');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:24', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008183257894912');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:24', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008184163864576');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:24', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008185115971584');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:24', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008185979998208');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:25', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008186898550784');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:25', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008187791937536');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:25', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008189801009152');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:26', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008193743654912');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:48:27', '/memberinfo/findBymobile', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860008198088953856');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:48:40', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008253214691328');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:49:27', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008449390678016');
INSERT INTO `traffic_statistics` VALUES ('127.0.0.1', null, null, '2017-05-04 13:50:11', '/lawcloudlawper/memberShip/toLogin', 'PC', null, 'firefox', '53.0', null, '首页', '860008634443370496');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:50:49', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008790978990080');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:50:54', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008812046979072');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:51:11', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008886651064320');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:51:29', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860008962408583168');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:51:43', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009018222186496');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:51:55', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009068067295232');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:52:16', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009155635974144');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:52:42', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009265723871232');
INSERT INTO `traffic_statistics` VALUES ('127.0.0.1', null, null, '2017-05-04 13:52:47', '/lawcloudlawper/memberShip/toLogin', 'PC', null, 'firefox', '53.0', null, '首页', '860009287962071040');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:52:49', '/memberShip/toIndex', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009293934759936');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:52:50', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009301971046400');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', null, null, '2017-05-04 13:52:51', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009305389404160');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '1', null, '2017-05-04 13:52:51', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009305955635200');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:52:51', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009306253430784');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:52:59', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009339061276672');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:00', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009343607902208');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '4-1', null, '2017-05-04 13:53:01', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009347621851136');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:02', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009348339077120');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:02', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009351874875392');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:03', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009354643116032');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:06', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009367163113472');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '5-1', null, '2017-05-04 13:53:06', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009368626925568');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '4-1', null, '2017-05-04 13:53:13', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009394845519872');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '5-1', null, '2017-05-04 13:53:13', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009394912628736');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '5-1', null, '2017-05-04 13:53:18', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009419109568512');
INSERT INTO `traffic_statistics` VALUES ('172.16.131.89', '4-1', null, '2017-05-04 13:53:18', '/memberShip/toMushroom', 'PC', null, 'chrome', '56.0.2924.87', '860007155326259200', '首页', '860009419109568513');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:23', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009436553678848');
INSERT INTO `traffic_statistics` VALUES ('172.16.154.159', null, null, '2017-05-04 13:53:24', '/lawcloudlawper/memberShip/toLogin', 'iPad', '9.0', 'safari', '601.1', null, '首页', '860009441406488576');

-- ----------------------------
-- Table structure for url_resources
-- ----------------------------
DROP TABLE IF EXISTS `url_resources`;
CREATE TABLE `url_resources` (
  `RESOURCE_ID` varchar(100) NOT NULL,
  `RESOURCE_NAME` varchar(100) DEFAULT NULL,
  `RESOURCE_DESC` varchar(200) DEFAULT NULL,
  `RESOURCE_PATH` varchar(200) DEFAULT NULL,
  `ENABLE` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of url_resources
-- ----------------------------
INSERT INTO `url_resources` VALUES ('1', null, null, '/**', null);
INSERT INTO `url_resources` VALUES ('2', null, null, '/authentication_failure_url', null);
INSERT INTO `url_resources` VALUES ('3', null, null, '/toLogin', null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'user', 'user', '1');
INSERT INTO `users` VALUES ('2', 'admin', 'admin', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `usertype` varchar(2) DEFAULT NULL COMMENT '用户类型',
  `enabled` int(2) DEFAULT NULL COMMENT '是否可用',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(14) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `city_id` bigint(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'test1', '12345678', '1', null, null, null, null, null, '1', '1');
INSERT INTO `user_info` VALUES ('2', 'test2', 'aaaa', '2', null, null, null, null, null, '2', '2');
INSERT INTO `user_info` VALUES ('3', 'test3', 'bbbb', '1', null, null, null, null, null, null, null);
INSERT INTO `user_info` VALUES ('4', 'test4', 'cccc', '2', null, null, null, null, null, null, null);
INSERT INTO `user_info` VALUES ('5', 'test5', 'dddd', '1', null, null, null, null, null, null, null);
