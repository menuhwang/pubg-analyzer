package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
CREATE TABLE log_player_kill_v2
(
    `id`                            INT AUTO_INCREMENT NOT NULL,
    `match_id`                      CHAR(36)        NULL,
    `timestamp`                     datetime           NULL,
    `attack_id`                     INT                NULL,
    `dbnoid`                        INT                NULL,
    `victim_name`                   VARCHAR(255)       NULL,
    `victim_account_id`             CHAR(40)        NULL,
    `dbnoname`                      VARCHAR(255)       NULL,
    `dbnoaccount_id`                CHAR(40)        NULL,
    `dbnodamage_reason`             VARCHAR(255)       NULL,
    `dbnodamage_type_category`      VARCHAR(255)       NULL,
    `dbnodamage_causer_name`        VARCHAR(255)       NULL,
    `dbnodistance`                  FLOAT              NULL,
    `finisher_name`                 VARCHAR(255)       NULL,
    `finisher_account_id`           CHAR(40)        NULL,
    `finisher_damage_reason`        VARCHAR(255)       NULL,
    `finisher_damage_type_category` VARCHAR(255)       NULL,
    `finisher_damage_causer_name`   VARCHAR(255)       NULL,
    `finisher_distance`             FLOAT              NULL,
    `killer_name`                   VARCHAR(255)       NULL,
    `killer_account_id`             CHAR(40)        NULL,
    `killer_damage_reason`          VARCHAR(255)       NULL,
    `killer_damage_type_category`   VARCHAR(255)       NULL,
    `killer_damage_causer_name`     VARCHAR(255)       NULL,
    `killer_distance`               FLOAT              NULL,
    `suicide`                       BIT(1)             NULL,
    CONSTRAINT pk_log_player_kill_v2 PRIMARY KEY (id)
);

CREATE INDEX killerName_matchId_index ON log_player_kill_v2 (killer_name, match_id);

CREATE INDEX matchId_index ON log_player_kill_v2 (match_id);
*/

@Entity(name = "log_player_kill_v2")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
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
    @Column(length = 36)
    private String matchId;
    private LocalDateTime timestamp;
    private Integer attackId;
    private Integer dBNOId;
    private String victimName;
    @Column(length = 40)
    private String victimAccountId;
    private String dBNOName;
    @Column(length = 40)
    private String dBNOAccountId;
    private String dBNODamageReason;
    private String dBNODamageTypeCategory;
    private String dBNODamageCauserName;
    private Float dBNODistance;
    private String finisherName;
    @Column(length = 40)
    private String finisherAccountId;
    private String finisherDamageReason;
    private String finisherDamageTypeCategory;
    private String finisherDamageCauserName;
    private Float finisherDistance;
    private String killerName;
    @Column(length = 40)
    private String killerAccountId;
    private String killerDamageReason;
    private String killerDamageTypeCategory;
    private String killerDamageCauserName;
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
                .dBNODamageReason(dbnoDamageInfo.getDamageReason())
                .dBNODamageTypeCategory(dbnoDamageInfo.getDamageTypeCategory())
                .dBNODamageCauserName(dbnoDamageInfo.getDamageCauserName())
                .dBNODistance(dbnoDamageInfo.getDistance())
                .finisherName(finisher == null ? null : finisher.getName())
                .finisherAccountId(finisher == null ? null : finisher.getAccountId())
                .finisherDamageReason(finishDamageInfo.getDamageReason())
                .finisherDamageTypeCategory(finishDamageInfo.getDamageTypeCategory())
                .finisherDamageCauserName(finishDamageInfo.getDamageCauserName())
                .finisherDistance(finishDamageInfo.getDistance())
                .killerName(killer == null ? null : killer.getName())
                .killerAccountId(killer == null ? null : killer.getAccountId())
                .killerDamageReason(killerDamageInfo.getDamageReason())
                .killerDamageTypeCategory(killerDamageInfo.getDamageTypeCategory())
                .killerDamageCauserName(killerDamageInfo.getDamageCauserName())
                .killerDistance(killerDamageInfo.getDistance())
                .suicide(telemetryResponse.getIsSuicide())
                .build();
    }

    public Boolean isVictimBot() {
        if (victimAccountId == null) return null;
        return victimAccountId.startsWith("ai.");
    }

    public Boolean isDBNOMakerBot() {
        if (dBNOAccountId == null) return null;
        return dBNOAccountId.startsWith("ai.");
    }

    public Boolean isFinisherBot() {
        if (finisherAccountId == null) return null;
        return finisherAccountId.startsWith("ai.");
    }

    public Boolean isKillerBot() {
        if (killerAccountId == null) return null;
        return killerAccountId.startsWith("ai.");
    }

    public static Set<String> extractVictimNames(Collection<LogPlayerKillV2> logPlayerKills) {
        return logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictimName)
                .collect(Collectors.toSet());
    }
}
