package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.MatchRes;
import com.menu.pubganalyzer.domain.dto.PageDTO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchApiController {
    private final MatchService matchService;

    @GetMapping
    public ApiResult<PageDTO<MatchRes>> findAll(Pageable pageable) {
        Page<Match> matchPage = matchService.findAll(pageable);
        Page<MatchRes> matchResPage = matchPage.map(MatchRes::of);

        return success(PageDTO.of(matchResPage));
    }
}