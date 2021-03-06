package xiao.xss.study.demo.oauth2.sso.auth.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author xiaoliang
 * @since 2019-07-09 17:01
 */
@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER', 'ROLE_DUMMY')")
public class DummyController {

    @GetMapping("/dummy/hello")
    public String dummy() {
        return "Hello, Dummy";
    }
}
