package com.menu.pubganalyzer.telemetries.model;

import java.time.LocalDateTime;

public interface LogPlayerKillV2 {
    String getType();
    LocalDateTime getTimestamp();
    Common getCommon();
    boolean isSuicide();
    Character getVictim();
    boolean isVictimBot();
    String getVictimName();
    Character getDBNOMaker();
    DamageInfo getDBNODamageInfo();
    Character getKiller();
    String getKillerName();
    DamageInfo getKillerDamageInfo();
    Character getFinisher();
    DamageInfo getFinisherDamageInfo();
}
