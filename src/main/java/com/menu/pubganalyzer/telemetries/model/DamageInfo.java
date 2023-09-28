package com.menu.pubganalyzer.telemetries.model;

import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageCauserName;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageReason;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageTypeCategory;

public interface DamageInfo {
    DamageReason getDamageReason();
    DamageTypeCategory getDamageTypeCategory();
    DamageCauserName getDamageCauserName();
    float getDistance();
    boolean isThroughPenetrableWall();
}
