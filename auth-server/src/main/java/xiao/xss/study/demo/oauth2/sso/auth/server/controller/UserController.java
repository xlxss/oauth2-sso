package xiao.xss.study.demo.oauth2.sso.auth.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xiao.xss.study.demo.oauth2.sso.auth.server.entity.SysUser;
import xiao.xss.study.demo.oauth2.sso.auth.server.service.UserService;

import java.util.List;

/**
 * 用户管理
 *
 * @author xiaoliang
 * @since 2019-07-09 16:20
 */
@RestController
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/user/list")
    public List<SysUser> findAll() {
        return userService.findAll();
    }
}
