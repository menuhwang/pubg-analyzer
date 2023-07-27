package com.menu.pubganalyzer.domain.model.telemetries.impl;

import com.menu.pubganalyzer.domain.model.telemetries.*;
import com.menu.pubganalyzer.domain.model.telemetries.Character;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CharacterResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.DamageInfoResponse;

import java.time.LocalDateTime;
import java.util.Map;

public class LogPlayerKillV2Impl implements LogPlayerKillV2 {
    private final String matchId;
    private final String type;
    private final LocalDateTime timestamp;
    private final Common common;
    private final boolean suicide;
    private final Character victim;
    private final Character dBNOMaker;
    private final DamageInfo dBNODamageInfo;
    private final Character killer;
    private final DamageInfo killerDamageInfo;
    private final Character finisher;
    private final DamageInfo finisherDamageInfo;


    public LogPlayerKillV2Impl(Telemetry telemetry) {
        Map<String, Object> attribute = telemetry.getAttribute();
        this.matchId = telemetry.getMatchId();
        this.type = telemetry.getType();
        this.timestamp = telemetry.getTimestamp();
        this.common = telemetry.getCommon();
        this.suicide = (boolean) attribute.get("isSuicide");
        this.victim = CharacterImpl.from((CharacterResponse) attribute.get("victim"));
        this.dBNOMaker = CharacterImpl.from((CharacterResponse) attribute.get("dBNOMaker"));
        this.dBNODamageInfo = DamageInfoImpl.from((DamageInfoResponse) attribute.get("dBNODamageInfo"));
        this.killer = CharacterImpl.from((CharacterResponse) attribute.get("killer"));
        this.killerDamageInfo = DamageInfoImpl.from((DamageInfoResponse) attribute.get("killerDamageInfo"));
        this.finisher = CharacterImpl.from((CharacterResponse) attribute.get("finisher"));
        this.finisherDamageInfo = DamageInfoImpl.from((DamageInfoResponse) attribute.get("finisherDamageInfo"));
    }

    @Override
    public String getMatchId() {
        return matchId;
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
    public boolean isSuicide() {
        return suicide;
    }

    @Override
    public Character getVictim() {
        return victim;
    }

    @Override
    public boolean isVictimBot() {
        return victim.isBot();
    }

    @Override
    public String getVictimName() {
        return victim.getName();
    }

    @Override
    public Character getDBNOMaker() {
        return dBNOMaker;
    }

    @Override
    public DamageInfo getDBNODamageInfo() {
        return dBNODamageInfo;
    }

    @Override
    public Character getKiller() {
        return killer;
    }

    @Override
    public String getKillerName() {
        return killer.getName();
    }

    @Override
    public DamageInfo getKillerDamageInfo() {
        return killerDamageInfo;
    }

    @Override
    public Character getFinisher() {
        return finisher;
    }

    @Override
    public DamageInfo getFinisherDamageInfo() {
        return finisherDamageInfo;
    }
}
