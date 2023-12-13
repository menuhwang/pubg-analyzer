package com.menu.pubganalyzer.support.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    private static final String REQUEST_LOG = "[{}] REQUEST [{}] {}";
    private static final String RESPONSE_LOG = "[{}] RESPONSE {}ms";
    private static final String QUERY_PATH_PATTERN = "%s?%s";

    @Around("within(com.menu.pubganalyzer.*.controller.*)")
    public Object logResponseTime(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String transactionId = MDC.get("transactionId");

        String method = req.getMethod();
        String requestPath = getRequestPath(req);

        log.info(REQUEST_LOG, transactionId, method, requestPath);
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info(RESPONSE_LOG, transactionId, end - start);
        return result;
    }

    @Around("within(com.menu.pubganalyzer.*.service.*)")
    public Object logServiceExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = pjp.proceed();

        long end = System.currentTimeMillis();

        String transactionId = MDC.get("transactionId");

        log.info("[{}] {}.{}({}) executed in: {}ms", transactionId, pjp.getSignature().getDeclaringType().getSimpleName(), pjp.getSignature().getName(), getParameterType(pjp), end - start);

        return proceed;
    }

    @Around("bean(pubgAPI)")
    public Object logPubgApi(ProceedingJoinPoint pjp) throws Throwable {
        String transactionId = MDC.get("transactionId");

        Object[] args = pjp.getArgs();
        if (args.length > 0) {
            log.debug("[{}] {}.{}({})", transactionId, pjp.getSignature().getDeclaringType().getSimpleName(), pjp.getSignature().getName(), getParameterType(pjp));
            for (int i = 0; i < args.length; i++) {
                log.debug("[{}] arg[{}]: {}", transactionId, i, args[i]);
            }
        }

        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();

        log.info("[{}] {}.{}({}) executed in: {}ms", transactionId, pjp.getSignature().getDeclaringType().getSimpleName(), pjp.getSignature().getName(), getParameterType(pjp), end - start);
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

    private String getParameterType(ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        Object[] args = pjp.getArgs();
        for (int size = args.length, i = 0; i < size; i++) {
            sb.append(args[i].getClass().getSimpleName());
            if (i < size - 1) {
                sb.append(',');
            }
        }

        return sb.toString();
    }
}
