package xiao.xss.study.demo.oauth2.sso.auth.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
//@Secured(value = "ADMIN")// 角色必须以ROLE_开头，否则报错Access is denied (user is not anonymous)
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/user/list")
    public List<SysUser> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/list2")
    public List<SysUser> findAll2() {
        return userService.findAll();
    }
}
