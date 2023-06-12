package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;

import java.time.LocalDateTime;
import java.util.List;

public class TelemetryResponseFixture {
    private static final TelemetryResponse.Characters RES_VICTIM = TelemetryResponse.Characters.builder()
            .name("test-victim-name")
            .accountId("test-victim-id")
            .teamId(2)
            .health(50F)
            .ranking(2)
            .build();

    private static final TelemetryResponse.Characters RES_DBNO_MAKER = TelemetryResponse.Characters.builder()
            .name("test-dbno-name")
            .accountId("test-dbno-id")
            .build();

    private static final TelemetryResponse.DamageInfo RES_DBNO_DAMAGE_INFO = TelemetryResponse.DamageInfo.builder()
            .damageReason("HeadShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapAUG_C")
            .distance(50F)
            .build();

    private static final TelemetryResponse.Characters RES_FINISHER = TelemetryResponse.Characters.builder()
            .name("test-finisher-name")
            .accountId("test-finisher-id")
            .build();

    private static final TelemetryResponse.DamageInfo RES_FINISHER_DAMAGE_INFO = TelemetryResponse.DamageInfo.builder()
            .damageReason("HeadShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapAUG_C")
            .distance(50F)
            .build();

    private static final TelemetryResponse.Characters RES_KILLER = TelemetryResponse.Characters.builder()
            .name("test-killer-name")
            .accountId("test-killer-id")
            .build();

    private static final TelemetryResponse.DamageInfo RES_KILLER_DAMAGE_INFO = TelemetryResponse.DamageInfo.builder()
            .damageReason("HeadShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapAUG_C")
            .distance(50F)
            .build();

    public static final TelemetryResponse RES_LOG_PLAYER_KILL = TelemetryResponse.builder()
            .type("LogPlayerKillV2")
            .timestamp(LocalDateTime.now())
            .attackId(1)
            .victim(RES_VICTIM)
            .dBNOId(1)
            .dBNOMaker(RES_DBNO_MAKER)
            .dBNODamageInfo(RES_DBNO_DAMAGE_INFO)
            .finisher(RES_FINISHER)
            .finishDamageInfo(RES_FINISHER_DAMAGE_INFO)
            .killer(RES_KILLER)
            .killerDamageInfo(RES_KILLER_DAMAGE_INFO)
            .isSuicide(false)
            .build();

    private static final TelemetryResponse.Characters RES_ATTACKER = TelemetryResponse.Characters.builder()
            .name(PlayerResponseFixture.PLAYER_NAME)
            .accountId(PlayerResponseFixture.PLAYER_ID)
            .teamId(1)
            .health(100F)
            .ranking(1)
            .build();

    public static final TelemetryResponse RES_LOG_PLAYER_TAKE_DAMAGE = TelemetryResponse.builder()
            .type("LogPlayerTakeDamage")
            .timestamp(LocalDateTime.now())
            .attackId(1)
            .attacker(RES_ATTACKER)
            .victim(RES_VICTIM)
            .damageTypeCategory("Damage_Gun")
            .damageReason("HeadShot")
            .damage(60F)
            .damageCauserName("WeapAUG_C")
            .build();

    public static final List<TelemetryResponse> TELEMETRY_RESPONSES = List.of(RES_LOG_PLAYER_KILL, RES_LOG_PLAYER_TAKE_DAMAGE);
}
