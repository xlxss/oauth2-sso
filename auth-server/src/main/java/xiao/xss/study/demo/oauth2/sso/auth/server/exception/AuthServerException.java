package xiao.xss.study.demo.oauth2.sso.auth.server.exception;

import org.springframework.security.core.AuthenticationException;
import xiao.xss.study.demo.oauth2.sso.auth.server.common.AuthServer;

/**
 * 认证异常
 *
 * @author xiaoliang
 * @since 2019-07-09 13:43
 */
public class AuthServerException extends AuthenticationException {
    private static final long serialVersionUID = AuthServer.SERIAL_VERSION_UID;

    public AuthServerException(String msg) {
        super(msg);
    }
}
