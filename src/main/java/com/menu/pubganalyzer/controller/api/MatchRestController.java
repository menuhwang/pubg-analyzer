package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.MatchRes;
import com.menu.pubganalyzer.domain.dto.PageDTO;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.support.admin.AdminOnly;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchRestController {
    private final MatchService matchService;

    @AdminOnly
    @GetMapping
    public ResponseEntity<ApiResult<PageDTO<MatchRes>>> findAll(@PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Match> matchPage = matchService.findAll(pageable);
        Page<MatchRes> matchResPage = matchPage.map(MatchRes::of);

        return ResponseEntity.ok(success(PageDTO.of(matchResPage)));
    }

    @AdminOnly
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResult<Map<String, String>>> deleteById(@PathVariable final String id) {
        matchService.deleteById(List.of(id));

        return ResponseEntity.ok(success(Map.of("id", id)));
    }
}
