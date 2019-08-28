package com.mall.admin.component;

import com.alibaba.fastjson.JSON;
import com.mall.common.dto.RestResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 当访问接口没有权限时，自定义的返回结果
* create by wangyongchun 2019/01/27 19:37
*/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSON.toJSON(RestResponse.fobbiden(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
