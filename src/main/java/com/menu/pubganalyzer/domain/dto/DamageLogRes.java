package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.model.enums.DamageCauserName;
import com.menu.pubganalyzer.domain.model.enums.DamageReason;
import com.menu.pubganalyzer.domain.model.enums.DamageTypeCategory;
import com.menu.pubganalyzer.domain.model.telemetries.LogPlayerTakeDamage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DamageLogRes {
    private final LocalDateTime timestamp;
    private final CharacterRes attacker;
    private final CharacterRes victim;
    private final DamageTypeCategory damageTypeCategory;
    private final DamageReason damageReason;
    private final Float damage;
    private final DamageCauserName damageCauserName;
    private final Integer phase;

    public static DamageLogRes of(LogPlayerTakeDamage logPlayerTakeDamage) {
        return DamageLogRes.builder()
                .timestamp(logPlayerTakeDamage.getTimestamp())
                .attacker(CharacterRes.from(logPlayerTakeDamage.getAttacker()))
                .victim(CharacterRes.from(logPlayerTakeDamage.getVictim()))
                .damageTypeCategory(logPlayerTakeDamage.getDamageTypeCategory())
                .damageReason(logPlayerTakeDamage.getDamageReason())
                .damage(logPlayerTakeDamage.getDamage())
                .damageCauserName(logPlayerTakeDamage.getDamageCauserName())
                .phase(logPlayerTakeDamage.getPhase())
                .build();
    }
}
