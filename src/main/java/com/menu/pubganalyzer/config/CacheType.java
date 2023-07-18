package com.menu.pubganalyzer.config;

public enum CacheType {
    PUBG_API_TELEMETRY("pubg_api_telemetry", 10, 5),
    MATCH("match", 30 * 60, 20 * 30),
    PLAYER("player", 60, 20),
    PARTICIPANT("participant", 5 * 60, 20 * 30 * 100),
    TELEMETRY("telemetry", 5 * 60, 20 * 30),
    ROSTER_TELEMETRY("roster_telemetry", 5 * 60, 20 * 30 * 25),
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
