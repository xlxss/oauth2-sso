package xiao.xss.study.demo.oauth2.sso.client.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证
 *
 * @author xiaoliang
 * @since 2019-07-15 11:13
 */
@RestController
@Slf4j
public class AuthController {

    @GetMapping("/callback")
    public void code(@RequestParam(required = false) String code) {
        log.debug("Authorization_code: {}", code);
    }
}
