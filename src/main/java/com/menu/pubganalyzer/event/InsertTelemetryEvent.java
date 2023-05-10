package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;

import java.util.List;

public class InsertTelemetryEvent {
    private final List<LogPlayerKillV2> logPlayerKill;
    private final List<LogPlayerTakeDamage> logPlayerTakeDamages;

    private InsertTelemetryEvent(List<LogPlayerKillV2> logPlayerKill, List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.logPlayerKill = logPlayerKill;
        this.logPlayerTakeDamages = logPlayerTakeDamages;
    }

    public List<LogPlayerKillV2> getLogPlayerKill() {
        return logPlayerKill;
    }

    public List<LogPlayerTakeDamage> getLogPlayerTakeDamages() {
        return logPlayerTakeDamages;
    }

    public static InsertTelemetryEvent of(List<LogPlayerKillV2> logPlayerKill, List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new InsertTelemetryEvent(logPlayerKill, logPlayerTakeDamages);
    }
}
