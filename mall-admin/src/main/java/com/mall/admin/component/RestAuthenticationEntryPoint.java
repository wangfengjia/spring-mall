package com.mall.admin.component;

import com.alibaba.fastjson.JSON;
import com.mall.common.dto.RestResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 当未登录或者token失效访问接口时，自定义的返回结果
* create by wangyongchun 2019/01/27 19:38
*/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSON.toJSON(RestResponse.unauthorized(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
