package com.menu.pubganalyzer.support.cookie.bookmark;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class BookmarkResolver implements HandlerMethodArgumentResolver {
    private static final Gson GSON = new Gson();
    private static final Type BOOKMARK_LIST = new TypeToken<ArrayList<Bookmark>>(){}.getType();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(BookmarkCookie.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        Cookie[] cookies = httpServletRequest.getCookies();

        ArrayList<Bookmark> bookmarks = new ArrayList<>();

        if (cookies == null) return bookmarks;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("bookmark")) {
                bookmarks = GSON.fromJson(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8), BOOKMARK_LIST);
            }
        }

        return bookmarks;
    }
}
