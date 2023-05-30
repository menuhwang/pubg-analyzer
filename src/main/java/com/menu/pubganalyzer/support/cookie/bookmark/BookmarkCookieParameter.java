package com.menu.pubganalyzer.support.cookie.bookmark;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class BookmarkCookieParameter {
    public static final String NAME = "bookmark";
    public static final int VERSION = 1;
    public static final int MAX_AGE = 90 * 24 * 3600;
    public static final int RENEWABLE_AGE = 30 * 24 * 3600;
    public static final String PATH = "/";
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static final Gson GSON = new Gson();
    private Integer version;
    private Long expires;
    private List<Bookmark> bookmarks = new ArrayList<>();
    private transient Cookie origin = new Cookie(NAME, "");

    private BookmarkCookieParameter() {
        this.origin.setPath(PATH);
        this.origin.setMaxAge(0);
        this.version = -1;
        this.expires = -1L;
    }

    private BookmarkCookieParameter(Integer version, Long expires, List<Bookmark> bookmarks, Cookie origin) {
        this.version = version;
        this.expires = expires;
        this.bookmarks = bookmarks;
        this.origin = origin;
    }

    private static BookmarkCookieParameter decode(Cookie cookie) {
        BookmarkCookieParameter decoded = decode(cookie.getValue());
        return new BookmarkCookieParameter(decoded.getVersion(), decoded.getExpires(), decoded.getBookmarks(), cookie);
    }

    public static BookmarkCookieParameter empty() {
        return new BookmarkCookieParameter();
    }

    public static BookmarkCookieParameter from(Cookie[] cookies) {
        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.empty();

        if (cookies == null || cookies.length == 0) return bookmarkCookieParameter;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(NAME)) {
                bookmarkCookieParameter = BookmarkCookieParameter.decode(cookie);
            }
        }

        return bookmarkCookieParameter;
    }

    public boolean contains(Bookmark bookmark) {
        return bookmarks.contains(bookmark);
    }

    public void add(Bookmark bookmark) {
        bookmarks.add(bookmark);
        setCookieValue();
    }

    public void remove(Bookmark bookmark) {
        bookmarks.remove(bookmark);
        setCookieValue();
    }

    private void setCookieValue() {
        expires = System.currentTimeMillis() / 1000 + MAX_AGE;
        version = VERSION;
        origin.setPath(PATH);
        origin.setMaxAge(MAX_AGE);
        origin.setValue(encode());
    }

    private static BookmarkCookieParameter decode(String value) {
        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.empty();
        try {
            bookmarkCookieParameter = GSON.fromJson(new String(DECODER.decode(value.getBytes())), BookmarkCookieParameter.class);
        } catch (IllegalArgumentException e) {
            log.warn("북마크 쿠키 디코딩 실패 [{}]{}", e.getClass().getName(), e.getMessage());
        }
        return bookmarkCookieParameter;
    }

    private String encode() {
        return ENCODER.encodeToString(GSON.toJson(this).getBytes());
    }

    public boolean isEmpty() {
        return this.origin == null || this.origin.getMaxAge() == 0;
    }

    public void renewMaxAge() {
        setCookieValue();
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public Cookie getCookie() {
        return origin;
    }

    public void clear() {
        Cookie temp = new Cookie(NAME, "");
        temp.setPath(PATH);
        temp.setMaxAge(0);
        this.origin = temp;
        this.version = -1;
        this.expires = -1L;
        this.bookmarks = new ArrayList<>();
    }

    public Integer getVersion() {
        return version;
    }

    public boolean verifyVersion() {
        return version == VERSION;
    }

    public Long getExpires() {
        return expires;
    }

    public boolean isRenewable() {
        return !isEmpty() && getExpires() <= System.currentTimeMillis() / 1000 + RENEWABLE_AGE;
    }
}
