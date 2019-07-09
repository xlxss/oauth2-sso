package xiao.xss.study.demo.oauth2.sso.auth.server.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LoginType;
import xiao.xss.study.demo.oauth2.sso.auth.server.service.UserService;

/**
 * 用户名密码登录
 *
 * @author xiaoliang
 * @since 2019-07-09 14:42
 */
@Component
public class UsernamePasswordProvider extends AbstractAuthenticationProvider {
    @Autowired private UserService userService;

    @Override
    protected UserDetails userDetails(String username, String password) {
        return userService.loadUser(username, password);
    }

    @Override
    protected boolean support(LoginType type) {
        return true;
//        return LoginType.USERNAME.equals(type);
    }
}
