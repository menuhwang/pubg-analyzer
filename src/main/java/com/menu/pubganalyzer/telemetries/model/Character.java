package com.menu.pubganalyzer.telemetries.model;

public interface Character {
    String getAccountId();
    String getName();
    int getTeamId();
    float getHealth();
    int getRanking();
    boolean isInBlueZone();
    boolean isInRedZone();
    boolean isBot();
}
