package com.menu.pubganalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
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
public class LogPlayerTakeDamage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String matchId;
    @OrderBy
    private LocalDateTime timestamp;
    private Integer attackId;
    private String attackerName;
    private Integer attackerTeamId;
    private Float attackerHealth;
    private Integer attackerRanking;
    private String attackerAccountId;
    private String victimName;
    private Integer victimTeamId;
    private Float victimHealth;
    private Integer victimRanking;
    private String victimAccountId;
    private String damageTypeCategory; // enum
    private String damageReason; // enum
    private Float damage;
    private String damageCauserName; // enum

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
                .damageTypeCategory(telemetryResponse.getDamageTypeCategory())
                .damageReason(telemetryResponse.getDamageReason())
                .damage(telemetryResponse.getDamage())
                .damageCauserName(telemetryResponse.getDamageCauserName())
                .build();
    }
}
