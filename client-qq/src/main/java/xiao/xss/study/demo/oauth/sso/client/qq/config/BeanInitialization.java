package xiao.xss.study.demo.oauth.sso.client.qq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 初始化Bean
 *
 * @author xiaoliang
 * @since 2019-07-23 8:52
 */
@Configuration
public class BeanInitialization {

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }
}
