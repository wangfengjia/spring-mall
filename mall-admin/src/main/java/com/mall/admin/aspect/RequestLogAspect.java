package com.mall.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class RequestLogAspect {

    @Pointcut("execution(public * com.mall.admin.controller..*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("url : " + request.getRequestURL().toString() + ",IP : " + request.getRemoteAddr()
                + ",class_method : " + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName() + ",args : "
                + Arrays.toString(joinPoint.getArgs()));
    }
}
