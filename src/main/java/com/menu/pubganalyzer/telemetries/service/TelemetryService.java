package com.menu.pubganalyzer.telemetries.service;

import com.menu.pubganalyzer.common.exception.MatchNotFoundException;
import com.menu.pubganalyzer.fetch.service.PubgService;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.model.Roster;
import com.menu.pubganalyzer.matches.repository.MatchRepository;
import com.menu.pubganalyzer.telemetries.dto.response.*;
import com.menu.pubganalyzer.telemetries.model.TelemetryEntity;
import com.menu.pubganalyzer.telemetries.repository.TelemetryRepository;
import com.menu.pubganalyzer.util.ChartUtil;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerAttack;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;
import com.menu.pubganalyzer.util.pubg.response.telemetry.objects.CharacterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelemetryService {
    private final PubgService pubgService;
    private final MatchRepository matchRepository;
    private final TelemetryRepository telemetryRepository;

    private final ConcurrentHashMap<String, Boolean> fetchTelemetryProgress = new ConcurrentHashMap<>();

    public List<KillLogResponse> findKillLogs(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        return telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerKillV2) telemetryResponse)
                .map(KillLogResponse::of)
                .collect(Collectors.toList());
    }

    public List<DamageLogResponse> findDamagesOfKill(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);

        Set<String> victims = telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerKillV2) telemetryResponse)
                .map(LogPlayerKillV2::getVictim)
                .map(CharacterResponse::getName)
                .collect(Collectors.toSet());

        Roster roster = match.getRosterByName(playerName);


        return telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(id, victims, roster.extractParticipantName()).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                .map(DamageLogResponse::of)
                .collect(Collectors.toList());
    }

    public List<DamageLogResponse> findDamageLogByPlayer(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        return telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                .map(DamageLogResponse::of)
                .collect(Collectors.toList());
    }

    public PhaseDamageChartResponse getPhaseDamageChart(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        List<LogPlayerTakeDamage> logPlayerTakeDamages = telemetryRepository.findLogPlayerTakeDamageByAttacker(id, playerName).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                .collect(Collectors.toList());

        return ChartUtil.phaseDamageChart(logPlayerTakeDamages);
    }

    public ContributeDamageChartResponse getContributeDamageChart(
            final String id,
            final String playerName) {
        fetchTelemetryIfAbsent(id);

        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);

        Set<String> members = match.getRosterByName(playerName)
                .extractParticipantName();

        List<LogPlayerKillV2> logPlayerKills = telemetryRepository.findLogPlayerKillByMatchIdAndPlayerName(id, playerName).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerKillV2) telemetryResponse)
                .collect(Collectors.toList());

        Set<String> victims = logPlayerKills.stream()
                .map(LogPlayerKillV2::getVictim)
                .map(CharacterResponse::getName)
                .collect(Collectors.toSet());

        List<LogPlayerTakeDamage> logPlayerTakeDamages = telemetryRepository.findLogPlayerTakeDamageByVictimsAndAttacker(id, victims, members).stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                .collect(Collectors.toList());

        return ChartUtil.contributeDamageChart(playerName, members, logPlayerKills, logPlayerTakeDamages);
    }

    /*
    * Telemetry가 DB에 저장되어있는지 확인 후 없는 경우에만 API를 호출합니다.
    * API 호출 후 DB에 저장합니다.
    * API 중복 호출 및 중복 저장 방지를 위해 ConcurrentHashMap을 사용했습니다.
    * ConcurrentHashMap에 Entry가 쌓이는 것은 방지하기 위해 remove 합니다.
    * */
    private void fetchTelemetryIfAbsent(String id) {
        if (telemetryRepository.existsByMatchId(id))
            return;

        fetchTelemetryProgress.computeIfAbsent(id, fetchTelemetryFunction);

        fetchTelemetryProgress.remove(id);
    }

    /*
    * ConcurrentHashMap에 Entry를 remove하기 때문에 이 함수를 중복하여 호출할 가능성이 있습니다.
    * telemetryRepository.existsByMatchId() 메소드를 통해 DB에 Telemetry가 있는지 더블 체크합니다.
    * 이 함수는 ConcurrentHashMap.computeIfAbsent 메소드로 부터 호출되기 때문에, 온전히 DB 저장이 완료될 때까지 블럭킹됩니다.
    * 따라서, telemetryRepository.existsByMatchId는 DB 저장 커밋 이후에 실행됨이 보장됩니다.*/
    private final Function<String, Boolean> fetchTelemetryFunction = new Function<>() {
        @Override
        public Boolean apply(String id) {
            if (telemetryRepository.existsByMatchId(id))
                return true;

            Match match = matchRepository.findById(id)
                    .orElseThrow(MatchNotFoundException::new);

            List<TelemetryEntity> telemetryEntities = pubgService.fetchTelemetry(match).stream()
                    .map(telemetryResponse -> new TelemetryEntity(null, match.getId(), telemetryResponse))
                    .collect(Collectors.toList());

            telemetryRepository.saveAll(telemetryEntities);

            return true;
        }
    };

    public WeaponAccuracyChartResponse getWeaponAccuracyChart(String id, String playerName) {
        fetchTelemetryIfAbsent(id);

        List<LogPlayerAttack> playerAttacksHittableWeapon = telemetryRepository.findPlayerAttackByHittableWeapon(id, playerName);
        List<LogPlayerTakeDamage> playerTakeDamages = telemetryRepository.findPlayerTakeDamageGunByAttacker(id, playerName);

        return ChartUtil.weaponAccuracyChart(playerAttacksHittableWeapon, playerTakeDamages);
    }
}
