package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TelemetryResponseFixture {
    private static final LocationResponse RES_LOCATION = LocationResponse.builder()
            .x(0F)
            .y(0F)
            .z(0F)
            .build();
    private static final CharacterResponse RES_VICTIM = CharacterResponse.builder()
            .name("test-victim-name")
            .accountId("test-victim-id")
            .teamId(9)
            .health(0F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    private static final StatsResponse VICTIM_STATS = StatsResponse.builder()
            .killCount(0)
            .distanceOnFoot(22.539F)
            .distanceOnSwim(0)
            .distanceOnVehicle(0)
            .distanceOnParachute(332.468F)
            .distanceOnFreefall(1459.933F)
            .build();

    private static final GameResultResponse VICTIM_GAME_RESULT = GameResultResponse.builder()
            .rank(RES_VICTIM.getRanking())
            .gameResult("")
            .teamId(RES_VICTIM.getTeamId())
            .stats(VICTIM_STATS)
            .accountId(RES_VICTIM.getAccountId())
            .build();

    private static final CharacterResponse RES_DBNO_MAKER = CharacterResponse.builder()
            .name("test-dbno-name")
            .accountId("test-dbno-id")
            .teamId(8)
            .health(100F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    private static final DamageInfoResponse RES_DBNO_DAMAGE_INFO = DamageInfoResponse.builder()
            .damageReason("LegShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapBerylM762_C")
            .additionalInfo(Collections.emptyList())
            .distance(128.407F)
            .isThroughPenetrableWall(false)
            .build();

    private static final CharacterResponse RES_FINISHER = CharacterResponse.builder()
            .name("test-finisher-name")
            .accountId("test-finisher-id")
            .teamId(8)
            .health(100F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    private static final DamageInfoResponse RES_FINISHER_DAMAGE_INFO = DamageInfoResponse.builder()
            .damageReason("TorsoShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapBerylM762_C")
            .additionalInfo(Collections.emptyList())
            .distance(200.248F)
            .isThroughPenetrableWall(false)
            .build();

    private static final CharacterResponse RES_KILLER = CharacterResponse.builder()
            .name("test-killer-name")
            .accountId("test-killer-id")
            .teamId(8)
            .health(100F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    private static final DamageInfoResponse RES_KILLER_DAMAGE_INFO = DamageInfoResponse.builder()
            .damageReason("LegShot")
            .damageTypeCategory("Damage_Gun")
            .damageCauserName("WeapBerylM762_C")
            .additionalInfo(Collections.emptyList())
            .distance(128.407F)
            .isThroughPenetrableWall(false)
            .build();

    public static final TelemetryResponse RES_LOG_PLAYER_KILL = TelemetryResponse.builder()
            .type("LogPlayerKillV2")
            .timestamp(LocalDateTime.now())
            .common(CommonResponse.builder().isGame(0.1F).build())
            .attackId(1)
            .victim(RES_VICTIM)
            .victimWeapon("")
            .victimWeaponAdditionalInfo(Collections.emptyList())
            .victimGameResult(VICTIM_GAME_RESULT)
            .dBNOId(1)
            .dBNOMaker(RES_DBNO_MAKER)
            .dBNODamageInfo(RES_DBNO_DAMAGE_INFO)
            .finisher(RES_FINISHER)
            .finishDamageInfo(RES_FINISHER_DAMAGE_INFO)
            .killer(RES_KILLER)
            .killerDamageInfo(RES_KILLER_DAMAGE_INFO)
            .teamKillers_AccountId(Collections.emptyList())
            .assists_AccountId(Collections.emptyList())
            .isSuicide(false)
            .build();

    private static final CharacterResponse RES_DAMAGE_VICTIM = CharacterResponse.builder()
            .name("test-victim-name")
            .accountId("test-victim-id")
            .teamId(9)
            .health(100F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    private static final CharacterResponse RES_ATTACKER = CharacterResponse.builder()
            .name(PlayerResponseFixture.PLAYER_NAME)
            .accountId(PlayerResponseFixture.PLAYER_ID)
            .teamId(8)
            .health(100F)
            .ranking(0)
            .location(RES_LOCATION)
            .isInBlueZone(false)
            .isInRedZone(false)
            .zone(Collections.emptyList())
            .build();

    public static final TelemetryResponse RES_LOG_PLAYER_TAKE_DAMAGE = TelemetryResponse.builder()
            .type("LogPlayerTakeDamage")
            .timestamp(LocalDateTime.now())
            .attackId(1)
            .attacker(RES_ATTACKER)
            .victim(RES_DAMAGE_VICTIM)
            .damageTypeCategory("Damage_Gun")
            .damageReason("PelvisShot")
            .damage(44D)
            .damageCauserName("WeapBerylM762_C")
            .common(CommonResponse.builder().isGame(0.1F).build())
            .isThroughPenetrableWall(false)
            .build();

    public static final List<TelemetryResponse> TELEMETRY_RESPONSES = List.of(RES_LOG_PLAYER_KILL, RES_LOG_PLAYER_TAKE_DAMAGE);
}
