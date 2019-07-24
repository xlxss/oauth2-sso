package xiao.xss.study.demo.oauth.sso.client.qq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 测试
 *
 * @author xiaoliang
 * @since 2019-07-23 9:24
 */
@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello(Principal principal) {
        System.out.println(principal);
        return "Hello, Client-qq";
    }
}
