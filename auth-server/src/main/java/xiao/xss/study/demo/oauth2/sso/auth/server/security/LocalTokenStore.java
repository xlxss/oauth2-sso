package xiao.xss.study.demo.oauth2.sso.auth.server.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * token management
 *
 * @author xiaoliang
 * @since 2019-07-16 15:17
 */
@Slf4j
public class LocalTokenStore extends JdbcTokenStore {
    private static final String findUsername = "select user_name from oauth_access_token where token_id = ?";
    private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token from oauth_access_token where user_name = ?";
    private final JdbcTemplate jdbcTemplate;
    public LocalTokenStore(DataSource dataSource) {
        super(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<OAuth2AccessToken> readAllAccessToken(String tokenValue, Authentication auth) {
        List<OAuth2AccessToken> list = new ArrayList<>();
        String username = null;
        try {
            username = jdbcTemplate.queryForObject(findUsername, String.class, extractTokenKey(tokenValue));
        } catch (EmptyResultDataAccessException e) {
            if(auth != null) {
                username = auth.getName();
            }
        }
        if(username != null) {
            try {
                List<? extends byte[]> tokens = jdbcTemplate.queryForList(DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT, (new byte[0]).getClass(),  username);
                tokens.forEach(token -> list.add(deserializeAccessToken(token)));
            }catch (IllegalArgumentException e) {
                log.warn("Failed to deserialize access token for " + tokenValue, e);
                removeAccessToken(tokenValue);
            }
        }

        return list;
    }
}
