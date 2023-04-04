package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.model.Player;
import com.menu.pubganalyzer.model.dto.MatchRes;
import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.service.MatchesService;
import com.menu.pubganalyzer.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final MatchesService matchesService;

    @GetMapping()
    public String search(Shard shard, String nickname, Model model) {
        Player player = playerService.getPlayerByNickname(shard, nickname);
        List<MatchRes> matches = matchesService.getMatch(player);

        model.addAttribute("viewTitle", nickname);
        model.addAttribute("player", player);
        model.addAttribute("matches", matches);

        return "player";
    }
}
