package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.dto.MatchRes;
import com.menu.pubganalyzer.domain.model.enums.DeleteCondition;
import com.menu.pubganalyzer.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
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
        Page<MatchRes> page = matchService.findById(matchId, pageable).map(MatchRes::of);
        model.addAttribute("deleteConditionOption", DeleteCondition.values());
        model.addAttribute("page", page);
        model.addAttribute("matchId", matchId);
        return "admin/matches";
    }

    @GetMapping("/fetch-api")
    public String fetch() {
        return "admin/fetch-api";
    }
}
