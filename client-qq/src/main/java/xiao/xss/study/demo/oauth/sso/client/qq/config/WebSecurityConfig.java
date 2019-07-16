package xiao.xss.study.demo.oauth.sso.client.qq.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.IOException;

/**
 * 安全配置
 *
 * @author xiaoliang
 * @since 2019-07-15 11:17
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .anonymous()
                .and()
                .logout()
                .clearAuthentication(true)
                .addLogoutHandler((request, response, authentication) -> {
                    System.out.println("client-qq logout");
                    OAuth2Authentication auth = (OAuth2Authentication) authentication;
                    String token = ((OAuth2AuthenticationDetails)auth.getDetails()).getTokenValue();
                    String logout = "http://client-qq:8020/qq/index.html";
                    try {
                        response.sendRedirect("http://auth-server:8000/auth/oauth/exit?token="+token+"&logout="+logout);
                    } catch(IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("内部错误");
                    }
                })
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("http://auth-server:8000/auth/oauth/exit")
                .and()
                .authorizeRequests()
                .antMatchers("/callback").permitAll()
                .antMatchers("/index.html").permitAll()
                .anyRequest().authenticated();
    }
}
