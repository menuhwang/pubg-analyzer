package com.menu.pubganalyzer.domain.model.telemetries;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;

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
