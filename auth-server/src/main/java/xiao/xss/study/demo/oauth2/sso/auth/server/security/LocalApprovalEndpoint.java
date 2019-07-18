package xiao.xss.study.demo.oauth2.sso.auth.server.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author xiaoliang
 * @since 2019-07-16 17:19
 */
//@RestController
//@SessionAttributes("authorizationRequest")
public class LocalApprovalEndpoint {

//    @RequestMapping("/oauth/confirm_access")
    public ResponseEntity approval(Map<String, Object> model, HttpServletRequest request) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        String requestPath = ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
        String clientId = authorizationRequest.getClientId();
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ?
                model.get("scopes") : request.getAttribute("scopes"));


        // http://auth-server:8000/auth/oauth/authorize
        // user_oauth_approval: true
        // authorize: Authorize

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> body = new HashMap<>();
        body.put("user_oauth_approval", true);
        body.put("authorize", "Authorize");
        scopes.forEach((k, v) -> body.put(k, true));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body);
        URI uri = ServletUriComponentsBuilder.fromContextPath(request).path("/oauth/authorize").build().toUri();
        ResponseEntity response = restTemplate.exchange(uri, HttpMethod.POST, entity, ResponseEntity.class);
        System.out.println(response.getBody());
        return ResponseEntity.ok(null);
    }
}
