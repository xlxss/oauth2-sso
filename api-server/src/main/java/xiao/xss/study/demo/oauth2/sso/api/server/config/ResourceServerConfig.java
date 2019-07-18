package xiao.xss.study.demo.oauth2.sso.api.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * resource server config
 *
 * @author xiaoliang
 * @since 2019-07-17 9:30
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId("api");
        resources.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .anonymous().disable()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/code").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().permitAll();
    }

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setTokenName("token");
        tokenServices.setClientId("api");
        tokenServices.setClientSecret("111");
        tokenServices.setCheckTokenEndpointUrl("http://auth-server:8000/auth/oauth/check_token");
        return tokenServices;
    }
}
