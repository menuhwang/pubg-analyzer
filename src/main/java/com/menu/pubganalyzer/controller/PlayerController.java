package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.model.Player;
import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping()
    public String search(Shard shard, String nickname, Model model) {
        Player player = playerService.getPlayerByNickname(shard, nickname);
        model.addAttribute("viewTitle", nickname);
        model.addAttribute("player", player);

        return "player";
    }
}
