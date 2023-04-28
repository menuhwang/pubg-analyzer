package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.Analyzer;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class AnalyzerRes {
    private final List<KillLogRes> killLogs;
    private final Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private final Map<String, List<DamageLogRes>> victimDamageLog;

    @Builder
    public AnalyzerRes(List<KillLogRes> killLogs, Map<String, Map<String, Float>> victimPlayerDamageDealt, Map<String, List<DamageLogRes>> victimDamageLog) {
        this.killLogs = killLogs;
        this.victimPlayerDamageDealt = victimPlayerDamageDealt;
        this.victimDamageLog = victimDamageLog;
    }

    public static AnalyzerRes of(Analyzer analyzer) {
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
                .killLogs(analyzer.getLogPlayerKills().stream().map(KillLogRes::of).collect(Collectors.toList()))
                .victimPlayerDamageDealt(analyzer.getVictimPlayerDamageDealt())
                .victimDamageLog(map)
                .build();
        return result;
    }
}
