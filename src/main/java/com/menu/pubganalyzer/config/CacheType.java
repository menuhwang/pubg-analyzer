package com.menu.pubganalyzer.config;

public enum CacheType {
    RENEW_PLAYERS("renew_players", 10 * 60, 1024),
    MATCHES("matches", 60, 10000),
    ;

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;

    CacheType(String cacheName, int expiredAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expiredAfterWrite = expiredAfterWrite;
        this.maximumSize = maximumSize;
    }

    public String getCacheName() {
        return cacheName;
    }

    public int getExpiredAfterWrite() {
        return expiredAfterWrite;
    }

    public int getMaximumSize() {
        return maximumSize;
    }
}
