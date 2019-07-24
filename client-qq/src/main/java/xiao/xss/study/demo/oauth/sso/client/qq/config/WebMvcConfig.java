package xiao.xss.study.demo.oauth.sso.client.qq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xiao.xss.study.demo.oauth.sso.client.qq.resolver.RequestStringHandlerMethodArgumentResolver;

import java.util.List;

/**
 * web mvc config
 *
 * @author xiaoliang
 * @since 2019-07-22 15:29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired private ObjectMapper mapper;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders("X-PINGOTHER","Origin","X-Requested-With","Content-Type","Accept","Authorization","X-Login-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestStringHandlerMethodArgumentResolver(mapper));
    }
}
