package xiao.xss.study.demo.oauth2.sso.auth.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xiao.xss.study.demo.oauth2.sso.auth.server.service.UserService;

/**
 *
 * @author xiaoliang
 * @since 2019-07-09 14:29
 */
@Service
public class LocalUserDetailsService implements UserDetailsService {
    @Autowired private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
