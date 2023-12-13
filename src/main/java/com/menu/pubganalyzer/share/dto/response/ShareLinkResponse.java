package com.menu.pubganalyzer.share.dto.response;

public class ShareLinkResponse {
    private final String path;

    private ShareLinkResponse(String original) {
        this.path = original;
    }

    private ShareLinkResponse(String endPoint, String shortLink) {
        this.path = String.format("%s/%s", endPoint, shortLink);
    }

    public static ShareLinkResponse originalOf(String original) {
        return new ShareLinkResponse(original);
    }

    public static ShareLinkResponse shortLinkOf(String endPoint, String shortLink) {
        return new ShareLinkResponse(endPoint, shortLink);
    }

    public String getPath() {
        return path;
    }
}
