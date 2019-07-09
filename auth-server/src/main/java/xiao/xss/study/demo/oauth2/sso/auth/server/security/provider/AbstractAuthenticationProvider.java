package xiao.xss.study.demo.oauth2.sso.auth.server.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LoginRequestDetails;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LoginType;

import java.util.Optional;

/**
 * 根据登录方式进行用户认证
 *
 * @author xiaoliang
 * @since 2019-07-09 14:35
 */
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional principal = Optional.ofNullable(authentication.getPrincipal());
        Optional credentials = Optional.ofNullable(authentication.getCredentials());
        if (!principal.isPresent() || !credentials.isPresent()) {
            throw new BadCredentialsException("Invalid user credentials");
        }
        LoginRequestDetails details = (LoginRequestDetails) authentication.getDetails();
        if(!support(details.getLoginType())) {
            return null;
        }
        String username = (String) principal.get();
        String password = (String) credentials.get();
        // 当登录时密码用Base64编码后传到服务端时，需要解码
        // password = new String(Base64.getDecoder().decode(password), StandardCharsets.UTF_8);
        UserDetails user = userDetails(username, password);
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    protected abstract UserDetails userDetails(String username, String password);

    protected abstract boolean support(LoginType type);

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
