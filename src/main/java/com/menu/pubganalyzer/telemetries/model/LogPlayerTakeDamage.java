package com.menu.pubganalyzer.telemetries.model;

import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageCauserName;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageReason;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageTypeCategory;

import java.time.LocalDateTime;

public interface LogPlayerTakeDamage {
    String getType();
    LocalDateTime getTimestamp();
    Common getCommon();
    DamageTypeCategory getDamageTypeCategory();
    DamageCauserName getDamageCauserName();
    DamageReason getDamageReason();
    float getDamage();
    boolean isThroughPenetrableWall();
    Character getAttacker();
    String getAttackerName();
    Character getVictim();
    String getVictimName();
    int getPhase();
}
