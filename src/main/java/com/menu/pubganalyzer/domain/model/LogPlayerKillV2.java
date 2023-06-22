package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
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
    private LocalDateTime timestamp;
    private Integer attackId;
    private Integer dBNOId;
    private String victimName;
    private String victimAccountId;
    private String dBNOName;
    private String dBNOAccountId;
    @Enumerated(EnumType.STRING)
    private DamageReason dBNODamageReason;
    @Enumerated(EnumType.STRING)
    private DamageTypeCategory dBNODamageTypeCategory;
    @Enumerated(EnumType.STRING)
    private DamageCauserName dBNODamageCauserName;
    private Float dBNODistance;
    private String finisherName;
    private String finisherAccountId;
    @Enumerated(EnumType.STRING)
    private DamageReason finisherDamageReason;
    @Enumerated(EnumType.STRING)
    private DamageTypeCategory finisherDamageTypeCategory;
    @Enumerated(EnumType.STRING)
    private DamageCauserName finisherDamageCauserName;
    private Float finisherDistance;
    private String killerName;
    private String killerAccountId;
    @Enumerated(EnumType.STRING)
    private DamageReason killerDamageReason;
    @Enumerated(EnumType.STRING)
    private DamageTypeCategory killerDamageTypeCategory;
    @Enumerated(EnumType.STRING)
    private DamageCauserName killerDamageCauserName;
    private Float killerDistance;
    private Boolean suicide;

    public static LogPlayerKillV2 of(TelemetryResponse telemetryResponse, String matchId) {
        TelemetryResponse.Characters victim = telemetryResponse.getVictim();
        TelemetryResponse.Characters dBNOMaker = telemetryResponse.getDBNOMaker();
        TelemetryResponse.Characters finisher = telemetryResponse.getFinisher();
        TelemetryResponse.Characters killer = telemetryResponse.getKiller();

        TelemetryResponse.DamageInfo dbnoDamageInfo = telemetryResponse.getDBNODamageInfo();
        TelemetryResponse.DamageInfo finishDamageInfo = telemetryResponse.getFinishDamageInfo();
        TelemetryResponse.DamageInfo killerDamageInfo = telemetryResponse.getKillerDamageInfo();

        return LogPlayerKillV2.builder()
                .matchId(matchId)
                .timestamp(telemetryResponse.getTimestamp())
                .attackId(telemetryResponse.getAttackId())
                .dBNOId(telemetryResponse.getDBNOId())
                .victimName(victim.getName())
                .victimAccountId(victim.getAccountId())
                .dBNOName(dBNOMaker == null ? null : dBNOMaker.getName())
                .dBNOAccountId(dBNOMaker == null ? null : dBNOMaker.getAccountId())
                .dBNODamageReason(DamageReason.of(dbnoDamageInfo.getDamageReason()))
                .dBNODamageTypeCategory(DamageTypeCategory.of(dbnoDamageInfo.getDamageTypeCategory()))
                .dBNODamageCauserName(DamageCauserName.of(dbnoDamageInfo.getDamageCauserName()))
                .dBNODistance(dbnoDamageInfo.getDistance())
                .finisherName(finisher == null ? null : finisher.getName())
                .finisherAccountId(finisher == null ? null : finisher.getAccountId())
                .finisherDamageReason(DamageReason.of(finishDamageInfo.getDamageReason()))
                .finisherDamageTypeCategory(DamageTypeCategory.of(finishDamageInfo.getDamageTypeCategory()))
                .finisherDamageCauserName(DamageCauserName.of(finishDamageInfo.getDamageCauserName()))
                .finisherDistance(finishDamageInfo.getDistance())
                .killerName(killer == null ? null : killer.getName())
                .killerAccountId(killer == null ? null : killer.getAccountId())
                .killerDamageReason(DamageReason.of(killerDamageInfo.getDamageReason()))
                .killerDamageTypeCategory(DamageTypeCategory.of(killerDamageInfo.getDamageTypeCategory()))
                .killerDamageCauserName(DamageCauserName.of(killerDamageInfo.getDamageCauserName()))
                .killerDistance(killerDamageInfo.getDistance())
                .suicide(telemetryResponse.getIsSuicide())
                .build();
    }

    public static Set<String> extractVictimNames(Collection<LogPlayerKillV2> logPlayerKills) {
        return logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toSet());
    }
}
