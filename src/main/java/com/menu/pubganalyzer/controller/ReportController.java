package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.dto.ReportRes;
import com.menu.pubganalyzer.domain.model.Roster;
import com.menu.pubganalyzer.service.ReportService;
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
@RequestMapping("/report")
public class ReportController {
    private final FindService findService;
    private final ReportService reportService;

    @GetMapping("/match/{matchId}/player/{nickname}")
    public String analyze(Model model, @PathVariable String matchId, @PathVariable String nickname) {
        Roster roster = findService.findRosterByMatchIdAndPlayer(matchId, nickname);
        ReportRes reportRes = reportService.report(matchId, nickname);

        Set<String> member = roster.extractParticipantNameWithout(nickname);

        model.addAttribute("viewTitle", "매치 분석");
        model.addAttribute("datetimeFormatter", DatetimeFormatter.getInstance());
        model.addAttribute("player", nickname);
        model.addAttribute("shard", roster.getShardId());
        model.addAttribute("member", member);
        model.addAttribute("memberDamageDealt", reportRes.getData().getVictimPlayerDamageDealt());
        model.addAttribute("victimDamageLog", reportRes.getData().getVictimDamageLog());
        model.addAttribute("matchInfo", reportRes.getMatchInfo());
        model.addAttribute("matchResult", reportRes.getMatchResult());
        model.addAttribute("analyze", reportRes.getData());
        return "analyzer";
    }
}
