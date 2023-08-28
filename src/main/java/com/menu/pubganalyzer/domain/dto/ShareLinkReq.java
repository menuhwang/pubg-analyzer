package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.ShortLink;

public class ShareLinkReq {
    private final String link;

    public ShareLinkReq(String link) {
        this.link = link;
    }

    public ShortLink toShortLink() {
        return new ShortLink(link);
    }
}
