package com.menu.pubganalyzer.support.cookie.bookmark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkCookieParameterTest {
    private static final String SHARD = "steam";
    private static final String PLAYER_NICKNAME = "WackyJacky101";

    @Test
    @DisplayName("북마크 쿠키가 없는 경우")
    void not_exists_bookmark_cookie() {
        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.from(null);
        Cookie cookie = bookmarkCookieParameter.getCookie();
        assertEquals("bookmark", cookie.getName());
        assertEquals(0, cookie.getMaxAge());
        assertEquals("", cookie.getValue());
        assertEquals(BookmarkCookieParameter.PATH, cookie.getPath());
        assertEquals(-1, bookmarkCookieParameter.getVersion());
        assertEquals(-1L, bookmarkCookieParameter.getExpires());
    }

    @Test
    @DisplayName("북마크를 추가하면 쿠키 값, 만료 시간, 버전이 갱신된다.")
    void bookmark_add() {
        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.from(null);
        bookmarkCookieParameter.add(Bookmark.of(PLAYER_NICKNAME, SHARD));
        Cookie cookie = bookmarkCookieParameter.getCookie();
        assertNotEquals("", cookie.getValue());
        System.out.println(cookie.getValue());
        assertEquals(BookmarkCookieParameter.MAX_AGE, cookie.getMaxAge());
        assertEquals(BookmarkCookieParameter.PATH, cookie.getPath());
        assertEquals(BookmarkCookieParameter.VERSION, bookmarkCookieParameter.getVersion());
        long current = System.currentTimeMillis() / 1000;
        assertTrue(current < bookmarkCookieParameter.getExpires());
        System.out.printf("current:%d\nexpires:%d\n", current, bookmarkCookieParameter.getExpires());
    }

    @Test
    @DisplayName("쿠키값 디코딩")
    void decode_cookie_value() {
        BookmarkCookieParameter bookmarkCookieParameter = BookmarkCookieParameter.from(null);
        bookmarkCookieParameter.add(Bookmark.of(PLAYER_NICKNAME, SHARD));
        Cookie cookie = bookmarkCookieParameter.getCookie();
        Cookie[] cookies = new Cookie[]{cookie};
        BookmarkCookieParameter decoded = BookmarkCookieParameter.from(cookies);
        assertEquals(bookmarkCookieParameter.getVersion(), decoded.getVersion());
        assertEquals(bookmarkCookieParameter.getExpires(), decoded.getExpires());
        assertEquals(bookmarkCookieParameter.getBookmarks(), decoded.getBookmarks());
    }
}