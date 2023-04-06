package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.service.SearchPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final SearchPlayerService searchPlayerService;

    @GetMapping()
    public String search(SearchPlayerReq req, Model model, @PageableDefault(size = 20) Pageable pageable) {
        SearchPlayer searchPlayer = searchPlayerService.searchPlayer(req, pageable);

        model.addAttribute("viewTitle", req.getNickname());
        model.addAttribute("player", searchPlayer.getPlayer());
        model.addAttribute("stats", searchPlayer.getParticipants());

        return "player";
    }
}
