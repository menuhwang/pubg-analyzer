package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.dto.MatchRes;
import com.menu.pubganalyzer.domain.dto.PageDTO;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private static final String BASE_HREF_PATTERN = "?matchId=%s&sort=%s&";
    private final MatchService matchService;

    @GetMapping
    public String home() {
        return "admin/index";
    }

    @GetMapping("/matches")
    public String matches(
            Model model,
            @RequestParam(defaultValue = "") String matchId,
            @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        PageDTO<MatchRes> page = PageDTO.of(matchService.findById(matchId, pageable).map(MatchRes::of), 10);

        model.addAttribute("page", page);
        model.addAttribute("href", String.format(BASE_HREF_PATTERN, matchId, "createdAt," + pageable.getSort().getOrderFor("createdAt").getDirection().name()));
        model.addAttribute("matchId", matchId);
        return "admin/matches";
    }

    @GetMapping("/fetch-api")
    public String fetch(Model model) {
        model.addAttribute("shards", Arrays.stream(Shard.values()).map(Shard::name).collect(Collectors.toList()));
        return "admin/fetch-api";
    }
}
