package com.menu.pubganalyzer.support.cookie.bookmark;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BookmarkFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.from(cookies);

        if (!bookmarkCookieParameter.isEmpty()) {
            if (!bookmarkCookieParameter.verifyVersion()) {
                bookmarkCookieParameter.clear();
                response.addCookie(bookmarkCookieParameter.getCookie());
            } else if (bookmarkCookieParameter.isRenewable()) {
                bookmarkCookieParameter.renewMaxAge();
                response.addCookie(bookmarkCookieParameter.getCookie());
            }
        }

        request.setAttribute(BookmarkCookieParameter.class.getName(), bookmarkCookieParameter);

        filterChain.doFilter(request, response);
    }
}
