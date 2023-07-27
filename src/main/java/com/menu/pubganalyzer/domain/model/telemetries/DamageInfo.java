package com.menu.pubganalyzer.domain.model.telemetries;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;

public interface DamageInfo {
    DamageReason getDamageReason();
    DamageTypeCategory getDamageTypeCategory();
    DamageCauserName getDamageCauserName();
    float getDistance();
    boolean isThroughPenetrableWall();
}
