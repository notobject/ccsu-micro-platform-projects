/*
 * Created by Long Duping
 * Date 2019-02-16 20:14
 */
package cn.ccsu.proxy.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;

public class AccessCheckFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(AccessCheckFilter.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        if (RequestContext.getCurrentContext().getRequest().getRequestURI().contains("/ccsu-user-service/login"))
            return null;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String sessionId = request.getHeader("sessionId");
        //  会话有效性校验（先简单做个格式校验）
        if (sessionId == null || !sessionId.startsWith("CCSU.MICRO.PLATFORM.SESSION.")) {
            RespUtil.resp(10000, "sessionId is invaild.");
            return null;
        }
        // 填充用户信息
        try {

            ResponseEntity<String> entity = restTemplate.getForEntity("http://ccsu-user-service/getUserInfo?sessionId={1}", String.class, sessionId);
            if (entity.getStatusCode() == HttpStatus.OK) {
                String resp = entity.getBody();
                if (resp != null) log.info("resp: " + resp);
                JSONObject userInfo = JSONObject.parseObject(resp).getJSONObject("userInfo");
                if (userInfo == null) {
                    RespUtil.resp(10000, "userInfo is null.");
                }
                try {
                    JSONObject jsonObject = new JSONObject();
                    Enumeration<String> parameterNames = request.getParameterNames();
                    while (parameterNames.hasMoreElements()) {
                        String key = parameterNames.nextElement();
                        jsonObject.put(key, request.getParameter(key));
                    }
                    log.info("body: " + jsonObject.toString());
                    jsonObject.put("userInfo", userInfo);
                    String newBody = jsonObject.toString();
                    log.info("new body: " + newBody);
                    final byte[] bytes = newBody.getBytes();
                    ctx.setRequest(new HttpServletRequestWrapper(request) {
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStreamWrapper(bytes);
                        }

                        @Override
                        public int getContentLength() {
                            return bytes.length;
                        }

                        @Override
                        public long getContentLengthLong() {
                            return bytes.length;
                        }
                    });
                } catch (Exception e) {
                    RespUtil.resp(10000, e.getMessage());
                }
            } else {
                RespUtil.resp(10000, "getUserInfo error.status code:" + entity.getStatusCode());
            }
        } catch (HttpServerErrorException e) {
            RespUtil.resp(e.getRawStatusCode(), e.getMessage());
        }

        return null;
    }
}
