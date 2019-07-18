package xiao.xss.study.demo.oauth2.sso.api.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author xiaoliang
 * @since 2019-07-17 10:20
 */
@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN')")
public class HelloController {
    @GetMapping("/hello1")
    public String hello1() {
        return "Hello1";
    }
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2";
    }
    @GetMapping("/hello3")
    public String hello3() {
        return "Hello3";
    }
}
