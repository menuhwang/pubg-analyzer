package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.ShortLink;

public class ShareLinkReq {
    private String link;

    protected ShareLinkReq() {
    }

    public ShareLinkReq(String link) {
        this.link = link;
    }

    public ShortLink toShortLink() {
        return new ShortLink(link);
    }

    public String getLink() {
        return link;
    }
}
