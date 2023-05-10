package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.Analyzer;
import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.model.*;
import com.menu.pubganalyzer.domain.repository.LogPlayerKillV2Repository;
import com.menu.pubganalyzer.domain.repository.LogPlayerTakeDamageRepository;
import com.menu.pubganalyzer.domain.repository.MatchRepository;
import com.menu.pubganalyzer.domain.repository.ParticipantRepository;
import com.menu.pubganalyzer.event.InsertTelemetryEvent;
import com.menu.pubganalyzer.exception.MatchNotFoundException;
import com.menu.pubganalyzer.exception.PlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.PubgAPI;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzerService {
    private static final Set<String> DAMAGE_TYPE_FILER = Set.of(
            "Damage_BlueZone"
    );
    private final PubgAPI pubgAPI;
    private final ParticipantRepository participantRepository;
    private final LogPlayerKillV2Repository logPlayerKillV2Repository;
    private final LogPlayerTakeDamageRepository logPlayerTakeDamageRepository;
    private final MatchRepository matchRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public AnalyzerRes analyze(String matchId, String nickname) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(MatchNotFoundException::new);
        Participant participant = participantRepository.findByNameAndMatch(nickname, match)
                .orElseThrow(PlayerNotFoundException::new);

        Roster team = participant.getRoster();

        Analyzer analyzer = findLogs(match, team);
        analyzer.filterOfKillName(nickname);
        return AnalyzerRes.of(analyzer, match.getCreatedAt());
    }

    @Cacheable(value = "analyze", key = "#match.id + '_' + #team.teamId")
    public Analyzer findLogs(Match match, Roster team) {
        String matchId = match.getId();
        Set<String> memberNames = team.extractParticipantName();
        // db 조회
        if (existTelemetry(matchId)) return findTelemetry(matchId, memberNames);

        // API 요청
        return requestTelemetry(match, memberNames);
    }

    private Analyzer requestTelemetry(Match match, Set<String> memberNames) {
        String matchId = match.getId();

        List<LogPlayerKillV2> logPlayerKills = new ArrayList<>();
        List<LogPlayerTakeDamage> logPlayerTakeDamages = new ArrayList<>();

        List<TelemetryResponse> telemetryResponses = pubgAPI.telemetry(match.getAsset().getUrl());

        for (TelemetryResponse log : telemetryResponses) {
            if (log.getType().equals("LogPlayerKillV2")) {
                logPlayerKills.add(LogPlayerKillV2.of(log, matchId));
            } else if (log.getType().equals("LogPlayerTakeDamage") && !DAMAGE_TYPE_FILER.contains(log.getDamageTypeCategory())) {
                logPlayerTakeDamages.add(LogPlayerTakeDamage.of(log, matchId));
            }
        }

        eventPublisher.publishEvent(InsertTelemetryEvent.of(logPlayerKills, logPlayerTakeDamages));

        return Analyzer.analyzeOf(memberNames, logPlayerKills, logPlayerTakeDamages);
    }

    private boolean existTelemetry(String matchId) {
        return logPlayerKillV2Repository.existsByMatchId(matchId)
                && logPlayerTakeDamageRepository.existsByMatchId(matchId);
    }

    private Analyzer findTelemetry(String matchId, Set<String> memberNames) {
        List<LogPlayerKillV2> logPlayerKills = logPlayerKillV2Repository.findByKillerNameInAndMatchId(memberNames, matchId); // 팀 전체 킬 내역 조회
        List<String> victimNames = LogPlayerKillV2.extractVictimNames(logPlayerKills);
        List<LogPlayerTakeDamage> logPlayerTakeDamages = logPlayerTakeDamageRepository.findByAttackerNameInAndVictimNameInAndMatchId(memberNames, victimNames, matchId); // 나의 킬에 대한 팀원 전체의 데미지 로그 조회
        return Analyzer.of(logPlayerKills, logPlayerTakeDamages);
    }
}
