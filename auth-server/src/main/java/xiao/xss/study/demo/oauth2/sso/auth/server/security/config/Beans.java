package xiao.xss.study.demo.oauth2.sso.auth.server.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LocalTokenStore;

import javax.sql.DataSource;

/**
 * 创建bean
 * @author xiaoliang
 * @since 2019-07-09 14:50
 */
@Configuration
public class Beans {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public LocalTokenStore tokenStore(DataSource dataSource) {
        return new LocalTokenStore(dataSource);
    }

    @Bean
    public DefaultTokenServices defaultTokenServices(LocalTokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);//12h
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//1W
        return tokenServices;
    }
}
