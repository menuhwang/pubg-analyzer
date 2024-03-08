package com.menu.pubganalyzer.support.admin;

import com.menu.pubganalyzer.common.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    private final List<String> allow;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!hasAdminOnlyAnnotation(handler)) return true;

        String remoteHost = request.getRemoteHost();
        if (allow.contains(remoteHost)) return true;

        throw new AuthorizationException();
    }

    private boolean hasAdminOnlyAnnotation(Object handler) {
        if (!(handler instanceof  HandlerMethod)) return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getMethodAnnotation(AdminOnly.class) != null;
    }
}
