package com.menu.pubganalyzer.domain.model.telemetries.impl;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.domain.model.telemetries.DamageInfo;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.DamageInfoResponse;

import java.util.Map;
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

    private DamageInfoImpl(Map<String, Object> damageInfo) {
        this.damageReason = DamageReason.of(((String) damageInfo.getOrDefault("damageReason", "")).toUpperCase());
        this.damageTypeCategory = DamageTypeCategory.of(((String) damageInfo.getOrDefault("damageTypeCategory", "")).toUpperCase());
        this.damageCauserName = DamageCauserName.of(((String) damageInfo.getOrDefault("damageCauserName", "")).replaceAll("-", "_").toUpperCase());
        this.distance = ((Double) damageInfo.getOrDefault("distance", -1D)).floatValue();
        this.throughPenetrableWall = (boolean) damageInfo.getOrDefault("isThroughPenetrableWall", false);
    }

    public static DamageInfo from(DamageInfoResponse damageInfo) {
        if (Objects.isNull(damageInfo)) return null;
        return new DamageInfoImpl(damageInfo);
    }

    public static DamageInfo from(Map<String, Object> damageInfo) {
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
