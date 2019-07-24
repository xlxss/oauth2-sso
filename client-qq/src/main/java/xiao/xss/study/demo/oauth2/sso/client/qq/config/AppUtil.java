package xiao.xss.study.demo.oauth2.sso.client.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 认证
 *
 * @author xiaoliang
 * @since 2019-07-23 9:03
 */
@Component
public class AppUtil {
    @Autowired private RestTemplate rest;
    @Value("${security.oauth2.client.user-authorization-uri}")
    private String authorizationUri;
    @Value("${security.oauth2.client.grant-type}")
    private String grantType;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.pre-established-redirect-uri}")
    private String redirectUri;

    private static final String ACCESS_TOKEN_URL = "%s?grant_type=%s&code=%s&client_id=%s&client_secret=%s&redirect_uri=%s";
    private static final String REFRESH_TOKEN_URL = "%s?grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s";

    public Map<String, Object> getToken(String code) {
        Assert.hasLength(code, "授权码为空");

        HttpHeaders headers = new HttpHeaders();
        String url = String.format(ACCESS_TOKEN_URL, accessTokenUri, grantType, code, clientId, clientSecret, redirectUri);
        ParameterizedTypeReference<Map<String, Object>> ref = new ParameterizedTypeReference<Map<String, Object>>() {};
        ResponseEntity<Map<String, Object>> res = rest.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), ref);
        System.out.println(res.getBody());
        return res.getBody();
    }

    public Map<String, Object> refreshToken(String refreshToken) {
        Assert.hasLength(refreshToken, "refresh_token为空");

        HttpHeaders headers = new HttpHeaders();
        String url = String.format(REFRESH_TOKEN_URL, accessTokenUri, refreshToken, clientId, clientSecret);
        ParameterizedTypeReference<Map<String, Object>> ref = new ParameterizedTypeReference<Map<String, Object>>() {};
        ResponseEntity<Map<String, Object>> res = rest.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), ref);
        System.out.println(res.getBody());
        return res.getBody();
    }
}
