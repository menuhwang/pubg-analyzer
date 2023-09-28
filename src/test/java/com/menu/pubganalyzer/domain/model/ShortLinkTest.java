package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.share.model.ShortLink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortLinkTest {
    @Test
    void create() {
        String link = "/matches/0001/player/abcde";

        ShortLink shortLink = new ShortLink(link);

        assertNotNull(shortLink.getId());
        assertNotNull(shortLink.getLink());
        assertEquals(link, shortLink.getLink());
    }

    @Test
    void create_duplicated_link() {
        String link1 = "/matches/0001/player/abcde";
        String link2 = "/matches/0001/player/abcde";

        ShortLink shortLink1 = new ShortLink(link1);
        ShortLink shortLink2 = new ShortLink(link2);

        assertEquals(shortLink1.getId(), shortLink2.getId());
        assertEquals(shortLink1.getLink(), shortLink2.getLink());
    }
}