SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(15) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_authority`;
CREATE TABLE `sys_user_authority` (
  `user_id` bigint(20) DEFAULT NULL,
  `authority_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `uk_role_authority_1` (`user_id`,`authority_id`),
  CONSTRAINT `fk_user_authority_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `fk_user_authority_2` FOREIGN KEY (`authority_id`) REFERENCES `sys_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
create table oauth_access_token (
  token_id VARCHAR(64),
  token BLOB,
  authentication_id VARCHAR(64) PRIMARY KEY,
  user_name VARCHAR(50),
  client_id VARCHAR(64),
  authentication BLOB,
  refresh_token VARCHAR(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
create table oauth_refresh_token (
  token_id VARCHAR(64),
  token BLOB,
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
create table oauth_code (
  code VARCHAR(16), authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
create table oauth_client_details (
  client_id VARCHAR(64) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(64),
  scope VARCHAR(100),
  authorized_grant_types VARCHAR(100),
  web_server_redirect_uri VARCHAR(100),
  authorities VARCHAR(150),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(1024),
  autoapprove VARCHAR(120)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
