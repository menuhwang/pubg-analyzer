package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class SaveTelemetryEvent {
    private final String matchId;
    private final Collection<LogPlayerKillV2> logPlayerKills;
    private final Collection<LogPlayerTakeDamage> logPlayerTakeDamages;

    public SaveTelemetryEvent(String matchId, Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.matchId = matchId;
        this.logPlayerKills = logPlayerKills;
        this.logPlayerTakeDamages = logPlayerTakeDamages;
    }

    public static SaveTelemetryEvent of(String matchId, Collection<LogPlayerKillV2> logPlayerKills, Collection<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new SaveTelemetryEvent(matchId, logPlayerKills, logPlayerTakeDamages);
    }

    public List<LogPlayerKillV2> getLogPlayerKillsToList() {
        return new ArrayList<>(logPlayerKills);
    }

    public List<LogPlayerTakeDamage> getLogPlayerTakeDamagesToList() {
        return new ArrayList<>(logPlayerTakeDamages);
    }
}
