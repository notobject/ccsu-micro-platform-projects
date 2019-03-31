package cn.ccsu.team.handler;

import cn.ccsu.team.anno.OpenId;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @author hangs.zhang
 * @date 2018/12/13
 * *****************
 * function:
 */
@Slf4j
@Component
public class OpenIdResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(OpenId.class) != null;
    }

    @Override
    public String resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String result = "";
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();;
        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        JSONObject jsonObject = JSONObject.parseObject(body).getJSONObject("userInfo");
        if (jsonObject != null) {
            log.info("body: " + jsonObject.toString());
            result = jsonObject.getString("openId");
        }
        return result;
    }
}
