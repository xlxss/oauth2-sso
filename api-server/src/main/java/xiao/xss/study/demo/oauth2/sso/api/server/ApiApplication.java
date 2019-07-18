package xiao.xss.study.demo.oauth2.sso.api.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import xiao.xss.study.demo.oauth2.sso.api.server.common.ApiServer;

/**
 * Resource Server
 *
 * @author xiaoliang
 * @since 2019-07-17 9:30
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class ApiApplication {
    public static void main(String[] args) {
        ApiServer.showVersion();
        SpringApplication.run(ApiApplication.class, args);
    }
}
