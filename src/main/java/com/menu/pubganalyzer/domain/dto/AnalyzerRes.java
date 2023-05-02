package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.Analyzer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class AnalyzerRes {
    private final LocalDateTime matchCreatedAt;
    private final List<KillLogRes> killLog;
    private final Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private final Map<String, List<DamageLogRes>> victimDamageLog;

    @Builder
    public AnalyzerRes(LocalDateTime matchCreatedAt, List<KillLogRes> killLog, Map<String, Map<String, Float>> victimPlayerDamageDealt, Map<String, List<DamageLogRes>> victimDamageLog) {
        this.matchCreatedAt = matchCreatedAt;
        this.killLog = killLog;
        this.victimPlayerDamageDealt = victimPlayerDamageDealt;
        this.victimDamageLog = victimDamageLog;
    }

    public static AnalyzerRes of(Analyzer analyzer, LocalDateTime matchCreatedAt) {
        Map<String, List<DamageLogRes>> map = new HashMap<>();
        for (String victim : analyzer.getVictimDamageLog().keySet()) {
            map.put(
                    victim,
                    analyzer.getVictimDamageLog().get(victim).stream()
                            .map(DamageLogRes::of)
                            .collect(Collectors.toList())
            );
        }
        AnalyzerRes result = AnalyzerRes.builder()
                .matchCreatedAt(matchCreatedAt)
                .killLog(analyzer.getLogPlayerKills().stream().map(KillLogRes::of).collect(Collectors.toList()))
                .victimPlayerDamageDealt(analyzer.getVictimPlayerDamageDealt())
                .victimDamageLog(map)
                .build();
        return result;
    }
}
