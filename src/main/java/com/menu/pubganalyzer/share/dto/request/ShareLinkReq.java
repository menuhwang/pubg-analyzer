package com.menu.pubganalyzer.share.dto.request;

import com.menu.pubganalyzer.share.model.ShortLink;

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
