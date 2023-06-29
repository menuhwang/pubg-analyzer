package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;

import java.util.List;

public class SaveTelemetryEvent extends Event {
    private final List<LogPlayerKillV2> logPlayerKill;
    private final List<LogPlayerTakeDamage> logPlayerTakeDamages;

    private SaveTelemetryEvent(List<LogPlayerKillV2> logPlayerKill, List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        this.logPlayerKill = logPlayerKill;
        this.logPlayerTakeDamages = logPlayerTakeDamages;
    }

    public List<LogPlayerKillV2> getLogPlayerKill() {
        return logPlayerKill;
    }

    public List<LogPlayerTakeDamage> getLogPlayerTakeDamages() {
        return logPlayerTakeDamages;
    }

    public static SaveTelemetryEvent of(List<LogPlayerKillV2> logPlayerKill, List<LogPlayerTakeDamage> logPlayerTakeDamages) {
        return new SaveTelemetryEvent(logPlayerKill, logPlayerTakeDamages);
    }
}
