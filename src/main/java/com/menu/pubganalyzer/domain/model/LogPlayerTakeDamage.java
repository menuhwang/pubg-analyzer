package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
CREATE TABLE log_player_take_damage
        (
        `id`                   INT AUTO_INCREMENT NOT NULL,
        `match_id`             CHAR(36)        NULL,
        `timestamp`            datetime           NULL,
        `attack_id`            INT                NULL,
        `attacker_name`        VARCHAR(255)       NULL,
        `attacker_team_id`     INT                NULL,
        `attacker_health`      FLOAT              NULL,
        `attacker_ranking`     INT                NULL,
        `attacker_account_id`  CHAR(40)        NULL,
        `victim_name`          VARCHAR(255)       NULL,
        `victim_team_id`       INT                NULL,
        `victim_health`        FLOAT              NULL,
        `victim_ranking`       INT                NULL,
        `victim_account_id`    CHAR(40)        NULL,
        `damage_type_category` VARCHAR(255)       NULL,
        `damage_reason`        VARCHAR(255)       NULL,
        `damage`               FLOAT              NULL,
        `damage_causer_name`   VARCHAR(255)       NULL,
        `phase`                INT                NULL,
        CONSTRAINT pk_logplayertakedamage PRIMARY KEY (id)
        );

        CREATE INDEX attackerName_victimName_matchId_index ON log_player_take_damage (attacker_name, victim_name, match_id);

        CREATE INDEX matchId_index ON log_player_take_damage (match_id);
*/

@Entity(name = "log_player_take_damage")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        indexes = {
                @Index(columnList = "matchId", name = "matchId_index"),
                @Index(columnList = "attackerName, victimName, matchId", name = "attackerName_victimName_matchId_index")
        }
)
@Where(clause = "attacker_name != victim_name")
public class LogPlayerTakeDamage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column(length = 36)
    private String matchId;
    private LocalDateTime timestamp;
    private Integer attackId;
    private String attackerName;
    private Integer attackerTeamId;
    private Float attackerHealth;
    private Integer attackerRanking;
    @Column(length = 40)
    private String attackerAccountId;
    private String victimName;
    private Integer victimTeamId;
    private Float victimHealth;
    private Integer victimRanking;
    @Column(length = 40)
    private String victimAccountId;
    @Enumerated(EnumType.STRING)
    private DamageTypeCategory damageTypeCategory;
    @Enumerated(EnumType.STRING)
    private DamageReason damageReason;
    private Float damage;
    @Enumerated(EnumType.STRING)
    private DamageCauserName damageCauserName;
    private Integer phase;

    public static LogPlayerTakeDamage of(TelemetryResponse telemetryResponse, String matchId) {
        TelemetryResponse.Characters attacker = telemetryResponse.getAttacker();
        return LogPlayerTakeDamage.builder()
                .matchId(matchId)
                .timestamp(telemetryResponse.getTimestamp())
                .attackId(telemetryResponse.getAttackId())
                .attackerName(attacker == null ? null : attacker.getName())
                .attackerTeamId(attacker == null ? null : attacker.getTeamId())
                .attackerHealth(attacker == null ? null : attacker.getHealth())
                .attackerRanking(attacker == null ? null : attacker.getRanking())
                .attackerAccountId(attacker == null ? null : attacker.getAccountId())
                .victimName(telemetryResponse.getVictim().getName())
                .victimTeamId(telemetryResponse.getVictim().getTeamId())
                .victimHealth(telemetryResponse.getVictim().getHealth())
                .victimRanking(telemetryResponse.getVictim().getRanking())
                .victimAccountId(telemetryResponse.getVictim().getAccountId())
                .damageTypeCategory(DamageTypeCategory.of(telemetryResponse.getDamageTypeCategory()))
                .damageReason(DamageReason.of(telemetryResponse.getDamageReason()))
                .damage(telemetryResponse.getDamage())
                .damageCauserName(DamageCauserName.of(telemetryResponse.getDamageCauserName()))
                .phase(telemetryResponse.getCommon().getIsGame().intValue())
                .build();
    }
}
