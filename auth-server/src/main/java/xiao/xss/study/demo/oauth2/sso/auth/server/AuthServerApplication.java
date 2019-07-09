package xiao.xss.study.demo.oauth2.sso.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xiao.xss.study.demo.oauth2.sso.auth.server.common.AuthServer;

/**
 * 认证中心
 *
 * @author xiaoliang
 * @since 2019-07-09 11:31
 */
@SpringBootApplication
public class AuthServerApplication {
    public static void main(String[] args) {
        AuthServer.showVersion();
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
