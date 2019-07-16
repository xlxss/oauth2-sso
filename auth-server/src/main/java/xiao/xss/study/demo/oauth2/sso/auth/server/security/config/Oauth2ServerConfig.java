package xiao.xss.study.demo.oauth2.sso.auth.server.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LocalTokenStore;
import xiao.xss.study.demo.oauth2.sso.auth.server.security.LocalUserDetailsService;

import javax.sql.DataSource;

/**
 * OAuth2 Server Configuration
 *
 * @author xiaoliang
 * @since 2019-07-09 14:49
 */
@Configuration
public class Oauth2ServerConfig {
    @Autowired private DataSource dataSource;
    @Autowired private LocalUserDetailsService localUserDetailsService;
    @Autowired private AuthorizationCodeServices authorizationCodeServices;
    @Autowired private LocalTokenStore tokenStore;
    @Autowired private DefaultTokenServices defaultTokenServices;
    @Autowired private AuthenticationManager authenticationManager;

    // 认证授权服务配置
    @Configuration
    @EnableAuthorizationServer
    public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            // 定义令牌端点上的安全约束
            super.configure(security);
            // 让/oauth/token支持client_id以及client_secret作登录认证
            security.allowFormAuthenticationForClients()
                    .tokenKeyAccess("isAuthenticated()")
                    .checkTokenAccess("permitAll()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有存储
            super.configure(clients);
            clients.jdbc(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            // 定义授权和令牌端点以及令牌服务
            super.configure(endpoints);
            // 授权码模式，默认授权码保存在内存
            endpoints.authorizationCodeServices(authorizationCodeServices);
            // 开启密码认证模式
            endpoints.authenticationManager(authenticationManager);
            endpoints.userDetailsService(localUserDetailsService);
            endpoints.reuseRefreshTokens(true);
            endpoints.tokenStore(tokenStore);
            endpoints.tokenServices(defaultTokenServices);
        }
    }
}
