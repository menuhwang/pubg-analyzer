package com.menu.pubganalyzer.telemetries.dto.response;

import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageCauserName;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageReason;
import com.menu.pubganalyzer.telemetries.dto.response.enums.DamageTypeCategory;
import com.menu.pubganalyzer.telemetries.model.LogPlayerTakeDamage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DamageLogResponse {
    private final LocalDateTime timestamp;
    private final CharacterResponse attacker;
    private final CharacterResponse victim;
    private final DamageTypeCategory damageTypeCategory;
    private final DamageReason damageReason;
    private final Float damage;
    private final DamageCauserName damageCauserName;
    private final Integer phase;

    public static DamageLogResponse of(LogPlayerTakeDamage logPlayerTakeDamage) {
        return DamageLogResponse.builder()
                .timestamp(logPlayerTakeDamage.getTimestamp())
                .attacker(CharacterResponse.from(logPlayerTakeDamage.getAttacker()))
                .victim(CharacterResponse.from(logPlayerTakeDamage.getVictim()))
                .damageTypeCategory(logPlayerTakeDamage.getDamageTypeCategory())
                .damageReason(logPlayerTakeDamage.getDamageReason())
                .damage(logPlayerTakeDamage.getDamage())
                .damageCauserName(logPlayerTakeDamage.getDamageCauserName())
                .phase(logPlayerTakeDamage.getPhase())
                .build();
    }
}
