package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "log_player_kill_v2",
        indexes = {
                @Index(columnList = "matchId", name = "matchId_index"),
                @Index(columnList = "killerName, matchId", name = "killerName_matchId_index")
        }
)
@ToString
public class LogPlayerKillV2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String matchId;
    @OrderBy
    private LocalDateTime timestamp;
    private Integer attackId;
    private Integer dBNOId;
    private String victimName;
    private String victimAccountId;
    private String dBNOName;
    private String dBNOAccountId;
    private String dBNODamageReason; // enum
    private String dBNODamageTypeCategory; // enum
    private String dBNODamageCauserName; // enum
    private Float dBNODistance;
    private String finisherName;
    private String finisherAccountId;
    private String finisherDamageReason; // enum
    private String finisherDamageTypeCategory; // enum
    private String finisherDamageCauserName; // enum
    private Float finisherDistance;
    private String killerName;
    private String killerAccountId;
    private String killerDamageReason; // enum
    private String killerDamageTypeCategory; // enum
    private String killerDamageCauserName; // enum
    private Float killerDistance;
    private Boolean suicide;

    public static LogPlayerKillV2 of(TelemetryResponse telemetryResponse, String matchId) {
        TelemetryResponse.Characters victim = telemetryResponse.getVictim();
        TelemetryResponse.Characters dBNOMaker = telemetryResponse.getDBNOMaker();
        TelemetryResponse.Characters finisher = telemetryResponse.getFinisher();
        TelemetryResponse.Characters killer = telemetryResponse.getKiller();
        return LogPlayerKillV2.builder()
                .matchId(matchId)
                .timestamp(telemetryResponse.getTimestamp())
                .attackId(telemetryResponse.getAttackId())
                .dBNOId(telemetryResponse.getDBNOId())
                .victimName(victim.getName())
                .victimAccountId(victim.getAccountId())
                .dBNOName(dBNOMaker == null ? null : dBNOMaker.getName())
                .dBNOAccountId(dBNOMaker == null ? null : dBNOMaker.getAccountId())
                .dBNODamageReason(telemetryResponse.getDBNODamageInfo().getDamageReason())
                .dBNODamageTypeCategory(telemetryResponse.getDBNODamageInfo().getDamageTypeCategory())
                .dBNODamageCauserName(telemetryResponse.getDBNODamageInfo().getDamageCauserName())
                .dBNODistance(telemetryResponse.getDBNODamageInfo().getDistance())
                .finisherName(finisher == null ? null : finisher.getName())
                .finisherAccountId(finisher == null ? null : finisher.getAccountId())
                .finisherDamageReason(telemetryResponse.getFinishDamageInfo().getDamageReason())
                .finisherDamageTypeCategory(telemetryResponse.getFinishDamageInfo().getDamageTypeCategory())
                .finisherDamageCauserName(telemetryResponse.getFinishDamageInfo().getDamageCauserName())
                .finisherDistance(telemetryResponse.getFinishDamageInfo().getDistance())
                .killerName(killer == null ? null : killer.getName())
                .killerAccountId(killer == null ? null : killer.getAccountId())
                .killerDamageReason(telemetryResponse.getKillerDamageInfo().getDamageReason())
                .killerDamageTypeCategory(telemetryResponse.getKillerDamageInfo().getDamageTypeCategory())
                .killerDamageCauserName(telemetryResponse.getKillerDamageInfo().getDamageCauserName())
                .killerDistance(telemetryResponse.getKillerDamageInfo().getDistance())
                .suicide(telemetryResponse.getIsSuicide())
                .build();
    }

    public static List<String> extractVictimNames(Collection<LogPlayerKillV2> logPlayerKills) {
        return logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toList());
    }
}
