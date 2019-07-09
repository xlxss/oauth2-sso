-- password=admin
INSERT INTO `sys_user` (`id`, `username`, `password`, `name`, `email`, `mobile`, `active`, `create_time`, `update_time`) VALUES ('1', 'admin', '$2a$10$8pj./OmeQQjj3Jy6EeUTA.48gswOEo7A/BnvMaS42b0WMRl.vDsRS', '管理员', 'aa@bb.cc', '13482832735', '', '2019-07-04 16:02:51', '2019-07-04 16:02:51');
INSERT INTO `sys_authority` (`id`, `code`, `description`) VALUES ('1', 'AUTH_1', '权限_1');
INSERT INTO `sys_authority` (`id`, `code`, `description`) VALUES ('2', 'AUTH_2', '权限_2');
INSERT INTO `sys_authority` (`id`, `code`, `description`) VALUES ('3', 'AUTH_3', '权限_3');
INSERT INTO `sys_authority` (`id`, `code`, `description`) VALUES ('4', 'AUTH_4', '权限_4');
INSERT INTO `sys_authority` (`id`, `code`, `description`) VALUES ('5', 'AUTH_5', '权限_5');
INSERT INTO `sys_user_authority` (`user_id`, `authority_id`) VALUES ('1', '4');
INSERT INTO `sys_user_authority` (`user_id`, `authority_id`) VALUES ('1', '5');

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('weixin', '', '$2a$10$D2lvrKeG1.N2SbJxN6DANuBboguoJLFhFrljETO/O5ESLEM5/XACi', 'read, write', 'authorization_code,client_credentials,refresh_token,password,implicit', 'https://www.baidu.com', 'ROLE_CLIENT_1', '600', '3600', '{}', '');

