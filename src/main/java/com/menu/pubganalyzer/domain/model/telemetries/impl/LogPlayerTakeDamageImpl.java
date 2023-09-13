package com.menu.pubganalyzer.domain.model.telemetries.impl;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.domain.model.telemetries.Character;
import com.menu.pubganalyzer.domain.model.telemetries.Common;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.telemetries.Telemetry;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CharacterResponse;

import java.time.LocalDateTime;
import java.util.Map;

public class LogPlayerTakeDamageImpl implements LogPlayerTakeDamage {
    private final String type;
    private final LocalDateTime timestamp;
    private final Common common;
    private final DamageTypeCategory damageTypeCategory;
    private final DamageCauserName damageCauserName;
    private final DamageReason damageReason;
    private final float damage;
    private final boolean throughPenetrableWall;
    private final Character attacker;
    private final Character victim;

    public LogPlayerTakeDamageImpl(Telemetry telemetry) {
        Map<String, Object> attribute = telemetry.getAttribute();
        this.type = telemetry.getType();
        this.timestamp = telemetry.getTimestamp();
        this.common = telemetry.getCommon();
        this.damageTypeCategory = DamageTypeCategory.of(((String) attribute.get("damageTypeCategory")).toUpperCase());
        this.damageCauserName = DamageCauserName.of(((String) attribute.get("damageCauserName")).replaceAll("-", "_").toUpperCase());
        this.damageReason = DamageReason.of(((String) attribute.get("damageReason")).toUpperCase());
        this.damage = ((Double) attribute.get("damage")).floatValue();
        this.throughPenetrableWall = (boolean) attribute.get("isThroughPenetrableWall");
        this.attacker = CharacterImpl.from((CharacterResponse) attribute.get("attacker"));
        this.victim = CharacterImpl.from((CharacterResponse) attribute.get("victim"));
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public Common getCommon() {
        return common;
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
    public DamageReason getDamageReason() {
        return damageReason;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public boolean isThroughPenetrableWall() {
        return throughPenetrableWall;
    }

    @Override
    public Character getAttacker() {
        return attacker;
    }

    @Override
    public String getAttackerName() {
        return attacker.getName();
    }

    @Override
    public Character getVictim() {
        return victim;
    }

    @Override
    public String getVictimName() {
        return victim.getName();
    }

    @Override
    public int getPhase() {
        return (int) common.getIsGame();
    }
}
