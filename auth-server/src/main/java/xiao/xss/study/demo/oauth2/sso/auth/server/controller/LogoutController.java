package xiao.xss.study.demo.oauth2.sso.auth.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出
 *
 * @author xiaoliang
 * @since 2019-07-16 10:51
 */
@RestController
@Slf4j
public class LogoutController {
    @Autowired private TokenStore tokenStore;

    @GetMapping("/oauth/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        try {
            String token = request.getParameter("token");
            String logout = request.getParameter("logout");
            if(logout == null) {
                logout = request.getHeader("referer");
            }

            removeToken(token);

            new SecurityContextLogoutHandler().logout(request, response, auth);
            response.sendRedirect(logout);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void removeToken(String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if(accessToken != null) {
            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();

            log.debug("remove access token: {}", accessToken);
            tokenStore.removeAccessToken(accessToken);
            log.debug("remove refresh token: {}", refreshToken);
            tokenStore.removeRefreshToken(refreshToken);
        }
    }

    @GetMapping("/test")
    public void test(String token1, String token2) {
        OAuth2Authentication auth1 = tokenStore.readAuthentication(token1);
        OAuth2Authentication auth2 = tokenStore.readAuthentication(token2);
        log.debug("{}", auth1.equals(auth2));
    }
}
