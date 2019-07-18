package xiao.xss.study.demo.oauth2.sso.api.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 认证回调URL
 *
 * @author xiaoliang
 * @since 2019-07-17 9:47
 */
@RestController
@Slf4j
public class AuthController {
    private RestTemplate rest = new RestTemplate();

    @GetMapping("/auth/code")
    public void code(@RequestParam String code) {
        log.debug("Authorization_code: {}", code);
    }

//    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", "admin");
        body.add("password", "admin");
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
        ParameterizedTypeReference<Map<String, Object>> ref = new ParameterizedTypeReference<Map<String, Object>>() {};
        ResponseEntity<Map<String, Object>> res = rest.exchange("http://auth-server:8000/auth/login", HttpMethod.POST, entity, ref);
        System.out.println(res.getBody());

//        http://auth-server:8000/auth/oauth/authorize?client_id=weixin&response_type=code&redirect_uri=https://www.baidu.com
        String url = "http://auth-server:8000/auth/oauth/authorize?client_id=api&response_type=code&redirect_uri=http://client-api:8050/api/auth/code";
        res = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(null), ref);
        System.out.println(res.getBody());
    }
}
