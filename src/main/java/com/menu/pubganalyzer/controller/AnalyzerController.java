package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.service.AnalyzerService;
import com.menu.pubganalyzer.service.FindService;
import com.menu.pubganalyzer.util.DatetimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/analyzer")
public class AnalyzerController {
    private final FindService findService;
    private final AnalyzerService analyzerService;

    @GetMapping("/match/{matchId}/player/{nickname}")
    public String analyze(Model model, @PathVariable String matchId, @PathVariable String nickname) {
        Roster roster = findService.findRosterByMatchIdAndPlayer(matchId, nickname);
        AnalyzerRes analyzerRes = analyzerService.analyze(matchId, nickname);

        Set<String> member = roster.extractParticipantNameWithout(nickname);

        int killTotal = analyzerRes.getKillLog().size();
        int killBot = (int) analyzerRes.getKillLog().stream()
                        .filter(log -> log.getVictim().getAccountId().startsWith("ai."))
                        .count();

        model.addAttribute("viewTitle", "매치 분석");
        model.addAttribute("datetimeFormatter", DatetimeFormatter.getInstance());
        model.addAttribute("matchId", matchId);
        model.addAttribute("player", nickname);
        model.addAttribute("member", member);
        model.addAttribute("matchCreatedAt", analyzerRes.getMatchCreatedAt());
        model.addAttribute("killLog", analyzerRes.getKillLog());
        model.addAttribute("killTotal", killTotal);
        model.addAttribute("killBot", killBot);
        model.addAttribute("killPlayer", killTotal - killBot);
        model.addAttribute("memberDamageDealt", analyzerRes.getVictimPlayerDamageDealt());
        model.addAttribute("victimDamageLog", analyzerRes.getVictimDamageLog());
        return "analyzer";
    }
}
