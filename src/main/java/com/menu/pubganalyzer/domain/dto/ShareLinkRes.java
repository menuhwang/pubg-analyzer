package com.menu.pubganalyzer.domain.dto;

public class ShareLinkRes {
    private final String path;

    private ShareLinkRes(String original) {
        this.path = original;
    }

    private ShareLinkRes(String endPoint, String shortLink) {
        this.path = String.format("%s/%s", endPoint, shortLink);
    }

    public static ShareLinkRes originalOf(String original) {
        return new ShareLinkRes(original);
    }

    public static ShareLinkRes shortLinkOf(String endPoint, String shortLink) {
        return new ShareLinkRes(endPoint, shortLink);
    }

    public String getPath() {
        return path;
    }
}
