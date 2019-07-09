package xiao.xss.study.demo.oauth2.sso.auth.server.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录参数包装器
 * @author xiaoliang
 * @since 2019-07-09 14:33
 */
@Component
public class LoginRequestDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, LoginRequestDetails> {
    @Override
    public LoginRequestDetails buildDetails(HttpServletRequest context) {
        return new LoginRequestDetails(context);
    }
}
