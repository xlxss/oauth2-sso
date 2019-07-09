package xiao.xss.study.demo.oauth2.sso.auth.server.security;

/**
 * 登录方式
 *
 * @author xiaoliang
 * @since 2019-07-09 14:38
 */
public enum LoginType {
    USERNAME("用户名/密码登录"),
    MOBILE("手机/密码登录"),
    EMAIL("邮箱/密码登录");

    final String value;
    LoginType(String value) {
        this.value = value;
    }

    public static LoginType of(String name) {
        if(name == null || name.length() == 0) {
            return null;
        }
        for(LoginType type : values()) {
            if(type.name().equals(name.toUpperCase())) {
                return type;
            }
        }
        return null;
    }
}
