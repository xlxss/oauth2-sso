package xiao.xss.study.demo.oauth2.sso.client.qq.annotation;

import java.lang.annotation.*;

/**
 * 用于请求数据body只有一项时，无需包装对象
 *
 * @author xiaoliang
 * @since 2019-07-23 10:52
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestString {
    String name() default "";
}
