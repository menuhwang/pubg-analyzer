package com.menu.pubganalyzer.domain.model.telemetries.impl;

import com.menu.pubganalyzer.domain.model.telemetries.Character;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.CharacterResponse;

import java.util.Objects;

public class CharacterImpl implements Character {
    private final String accountId;
    private final String name;
    private final int teamId;
    private final float health;
    private final int ranking;
    private final boolean inBlueZone;
    private final boolean inRedZone;

    private CharacterImpl(CharacterResponse character) {
        this.accountId = character.getAccountId();
        this.name = character.getName();
        this.teamId = character.getTeamId();
        this.health = character.getHealth();
        this.ranking = character.getRanking();
        this.inBlueZone = character.isInBlueZone();
        this.inRedZone = character.isInRedZone();
    }

    public static Character from(CharacterResponse character) {
        if (Objects.isNull(character)) return null;
        return new CharacterImpl(character);
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTeamId() {
        return teamId;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public int getRanking() {
        return ranking;
    }

    @Override
    public boolean isInBlueZone() {
        return inBlueZone;
    }

    @Override
    public boolean isInRedZone() {
        return inRedZone;
    }

    @Override
    public boolean isBot() {
        return accountId.startsWith("ai");
    }
}
