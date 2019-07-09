package xiao.xss.study.demo.oauth2.sso.auth.server.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import xiao.xss.study.demo.oauth2.sso.auth.server.common.AuthServer;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author xiaoliang
 * @since 2019-07-09 14:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = AuthServer.SERIAL_VERSION_UID;
    private LoginType loginType;

    LoginRequestDetails(HttpServletRequest request) {
        super(request);
        String type = request.getHeader("X-Login-Type");
        if(type == null || type.length() == 0) {
            type = request.getParameter("loginType");
        }
        loginType = LoginType.of(type);
    }
}
