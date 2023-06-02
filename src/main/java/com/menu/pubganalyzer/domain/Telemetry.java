package com.menu.pubganalyzer.domain;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Telemetry {
    private static final Set<String> DAMAGE_TYPE_FILER = Set.of(
            "Damage_BlueZone"
    );
    @Builder.Default
    private List<LogPlayerKillV2> logPlayerKills = new ArrayList<>();
    @Builder.Default
    private List<LogPlayerTakeDamage> logPlayerTakeDamages = new ArrayList<>();

    public static Telemetry of(List<TelemetryResponse> telemetryResponses, String matchId) {
        Telemetry telemetry = new Telemetry();
        for (TelemetryResponse log : telemetryResponses) {
            if (log.getType().equals("LogPlayerKillV2")) {
                telemetry.logPlayerKills.add(LogPlayerKillV2.of(log, matchId));
            } else if (log.getType().equals("LogPlayerTakeDamage") && !DAMAGE_TYPE_FILER.contains(log.getDamageTypeCategory())) {
                telemetry.logPlayerTakeDamages.add(LogPlayerTakeDamage.of(log, matchId));
            }
        }
        return telemetry;
    }

    public static Telemetry ofRoster(Telemetry telemetry, Roster roster) {
        Telemetry rosterTelemetry = new Telemetry();
        rosterTelemetry.logPlayerKills.addAll(telemetry.getLogPlayerKillsGroupByRoster(roster));
        rosterTelemetry.logPlayerTakeDamages.addAll(telemetry.getLogPlayerTakeDamagesGroupByRoster(roster));
        return rosterTelemetry;
    }

    private List<LogPlayerKillV2> getLogPlayerKillsGroupByRoster(Roster roster) {
        Set<String> member = roster.extractParticipantName();
        return this.logPlayerKills.stream()
                .filter(log -> member.contains(log.getKillerName()))
                .collect(Collectors.toList());
    }

    private List<LogPlayerTakeDamage> getLogPlayerTakeDamagesGroupByRoster(Roster roster) {
        Set<String> member = roster.extractParticipantName();
        return this.logPlayerTakeDamages.stream()
                .filter(log -> member.contains(log.getAttackerName()))
                .collect(Collectors.toList());
    }

    public List<LogPlayerKillV2> getLogPlayerKills() {
        return logPlayerKills;
    }

    public List<LogPlayerTakeDamage> getLogPlayerTakeDamages() {
        return logPlayerTakeDamages;
    }
}
