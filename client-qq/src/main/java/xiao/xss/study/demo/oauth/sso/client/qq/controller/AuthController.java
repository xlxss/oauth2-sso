package xiao.xss.study.demo.oauth.sso.client.qq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiao.xss.study.demo.oauth.sso.client.qq.annotation.RequestString;
import xiao.xss.study.demo.oauth.sso.client.qq.config.AppUtil;

import java.util.Map;

/**
 * 认证
 *
 * @author xiaoliang
 * @since 2019-07-15 11:13
 */
@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AppUtil appUtil;

    @GetMapping("/auth/code")
    public void code(@RequestParam(required = false) String code) {
        log.debug("Authorization_code: {}", code);
    }

    @PostMapping("/auth/token")
    public Map<String, Object> token(@RequestString String code) {
        return appUtil.getToken(code);
    }

    @PostMapping("/auth/refreshToken")
    public Map<String, Object> refreshToken(@RequestBody String refreshToken) {
        return appUtil.refreshToken(refreshToken);
    }
}
