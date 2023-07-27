package com.menu.pubganalyzer.config;

public enum CacheType {
    MATCH("match", 60, 20),
    PLAYER("player", 60, 100),
    TELEMETRY("telemetry", 60, 20),
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
