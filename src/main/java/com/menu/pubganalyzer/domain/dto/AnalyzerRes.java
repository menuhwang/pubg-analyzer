package com.menu.pubganalyzer.domain.dto;

import com.menu.pubganalyzer.domain.Analyzer;
import com.menu.pubganalyzer.util.ChartUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AnalyzerRes {
    private final List<KillLogRes> killLog;
    private final List<DamageLogRes> damageLog;
    private final int player;
    private final int bot;
    private final Map<String, Map<String, Float>> victimPlayerDamageDealt;
    private final Map<String, List<DamageLogRes>> victimDamageLog;
    private final Map<String, Object> charts;

    @Builder
    public AnalyzerRes(
            List<KillLogRes> killLog,
            List<DamageLogRes> damageLog,
            Integer player,
            Integer bot,
            Map<String, Map<String, Float>> victimPlayerDamageDealt,
            Map<String, List<DamageLogRes>> victimDamageLog,
            Map<String, Object> charts) {
        this.killLog = killLog;
        this.damageLog = damageLog;
        this.player = player == null ? 0 : player;
        this.bot = bot == null ? 0 : bot;
        this.victimPlayerDamageDealt = victimPlayerDamageDealt;
        this.victimDamageLog = victimDamageLog;
        this.charts = charts;
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
        Map<String, Object> charts = new HashMap<>();
        charts.put("phaseDamageChart", ChartUtil.phaseDamageChart(analyzer.getLogPlayerTakeDamages()));
        charts.put("contributeDamageChart", ChartUtil.contributeDamageChart(analyzer.getLogPlayerKills(), analyzer.getVictimPlayerDamageDealt()));

        return AnalyzerRes.builder()
                .killLog(analyzer.getLogPlayerKills().stream().map(KillLogRes::of).collect(Collectors.toList()))
                .damageLog(analyzer.getLogPlayerTakeDamages().stream().map(DamageLogRes::of).collect(Collectors.toList()))
                .player(analyzer.getCount().get("player"))
                .bot(analyzer.getCount().get("bot"))
                .victimPlayerDamageDealt(analyzer.getVictimPlayerDamageDealt())
                .victimDamageLog(map)
                .charts(charts)
                .build();
    }

    public Set<String> extractVictimNames() {
        return victimPlayerDamageDealt.keySet();
    }
}
