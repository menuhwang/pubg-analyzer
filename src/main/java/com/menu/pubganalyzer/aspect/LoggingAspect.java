package com.menu.pubganalyzer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    private static final String REQUEST_LOG = "REQUEST [{}] {}";
    private static final String RESPONSE_LOG = "RESPONSE [{}] {} {}ms";
    private static final String QUERY_PATH_PATTERN = "%s/%s";

    @Around("within(com.menu.pubganalyzer.controller..*)")
    public Object controllerLogging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String method = req.getMethod();
        String requestPath = getRequestPath(req);

        log.info(REQUEST_LOG, method, requestPath);
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info(RESPONSE_LOG, method, requestPath, end - start);
        return result;
    }

    private String getRequestPath(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        if (method.equals("GET")) {
            return query == null ? uri : String.format(QUERY_PATH_PATTERN, uri, query);
        }
        return uri;
    }
}
