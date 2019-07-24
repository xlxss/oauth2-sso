package xiao.xss.study.demo.oauth2.sso.client.qq.resolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xiao.xss.study.demo.oauth2.sso.client.qq.annotation.RequestString;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 用于请求数据body只有一项时，无需包装对象
 *
 * @author xiaoliang
 * @since 2019-07-23 10:55
 */
public class RequestStringHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private ObjectMapper mapper;
    public RequestStringHandlerMethodArgumentResolver(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestString.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(request, "系统错误：请求缺失HttpServletRequest");
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while ((rd = reader.read(buf)) != -1) {
            sb.append(buf, 0, rd);
        }
        Object data = null;
        try {
            JsonNode node = mapper.readTree(sb.toString());

            // is json
            String name = parameter.getParameterAnnotation(RequestString.class).name();
            if(StringUtils.isEmpty(name)) {
                name = parameter.getParameterName();
            }
            if(node != null && node.has(name)) {
                data = node.get(name).asText();
            }
        } catch(IOException e) {
            // is not json
            data = sb.toString();
        }
        return data;
    }
}
