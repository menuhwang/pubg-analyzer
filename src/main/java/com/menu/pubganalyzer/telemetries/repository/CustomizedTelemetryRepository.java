package com.menu.pubganalyzer.telemetries.repository;

import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerAttack;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;

import java.util.List;

public interface CustomizedTelemetryRepository {
    List<LogPlayerAttack> findPlayerAttackByHittableWeapon(String matchId, String attackerName);
    List<LogPlayerTakeDamage> findPlayerTakeDamageGunByAttacker(String matchId, String attackerName);
}
