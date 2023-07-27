package com.menu.pubganalyzer.domain.model.telemetries.impl;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.domain.model.telemetries.DamageInfo;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.DamageInfoResponse;

import java.util.Objects;

public class DamageInfoImpl implements DamageInfo {
    private final DamageReason damageReason;
    private final DamageTypeCategory damageTypeCategory;
    private final DamageCauserName damageCauserName;
    private final float distance;
    private final boolean throughPenetrableWall;

    private DamageInfoImpl(DamageInfoResponse damageInfo) {
        this.damageReason = DamageReason.of(damageInfo.getDamageReason().toUpperCase());
        this.damageTypeCategory = DamageTypeCategory.of(damageInfo.getDamageTypeCategory().toUpperCase());
        this.damageCauserName = DamageCauserName.of(damageInfo.getDamageCauserName().replaceAll("-", "_").toUpperCase());
        this.distance = damageInfo.getDistance();
        this.throughPenetrableWall = damageInfo.isThroughPenetrableWall();
    }

    public static DamageInfo from(DamageInfoResponse damageInfo) {
        if (Objects.isNull(damageInfo)) return null;
        return new DamageInfoImpl(damageInfo);
    }

    @Override
    public DamageReason getDamageReason() {
        return damageReason;
    }

    @Override
    public DamageTypeCategory getDamageTypeCategory() {
        return damageTypeCategory;
    }

    @Override
    public DamageCauserName getDamageCauserName() {
        return damageCauserName;
    }

    @Override
    public float getDistance() {
        return distance;
    }

    @Override
    public boolean isThroughPenetrableWall() {
        return throughPenetrableWall;
    }
}
