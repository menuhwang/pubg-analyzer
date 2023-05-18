package com.menu.pubganalyzer.support.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    private final List<String> allow;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteAddr = request.getRemoteAddr();
        if (allow.contains(remoteAddr)) return true;

        String forwardAddr = request.getHeader("X-Forwarded-For");
        if (forwardAddr != null && allow.contains(forwardAddr)) return true;
        response.setStatus(403);
        return false;
    }
}
