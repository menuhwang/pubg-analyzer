package com.menu.pubganalyzer.matches.controller;

import com.menu.pubganalyzer.common.dto.response.PageDTO;
import com.menu.pubganalyzer.matches.service.MatchService;
import com.menu.pubganalyzer.matches.model.Match;
import com.menu.pubganalyzer.matches.dto.response.*;
import com.menu.pubganalyzer.support.admin.AdminOnly;
import com.menu.pubganalyzer.common.dto.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.menu.pubganalyzer.common.dto.response.ApiResultUtil.success;

@Tag(
        name = "매치",
        description = "매치 정보 조회 및 관리 API"
)
@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchRestController {
    private final MatchService matchService;

    @AdminOnly
    @GetMapping
    @Operation(
            summary = "매치 조회",
            description = "관리자만 접근 가능합니다."
    )
    public ResponseEntity<ApiResult<PageDTO<MatchResponse>>> findAll(@PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Match> matchPage = matchService.findAll(pageable);
        Page<MatchResponse> matchResPage = matchPage.map(MatchResponse::from);

        return ResponseEntity.ok(success(PageDTO.of(matchResPage)));
    }

    @AdminOnly
    @DeleteMapping("{id}")
    @Operation(
            summary = "매치 삭제",
            description = "관리자만 접근 가능합니다."
    )
    public ResponseEntity<ApiResult<Map<String, String>>> deleteById(@PathVariable final String id) {
        matchService.deleteById(List.of(id));

        return ResponseEntity.ok(success(Map.of("id", id)));
    }

    @GetMapping("/player/{playerName}")
    @Operation(
            summary = "플레이어 매치 리스트 조회"
    )
    public ResponseEntity<ApiResult<SearchPlayerResponse>> findByPlayerName(
            @PathVariable final String playerName,
            @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Match> matchPage = matchService.findByPlayerName(playerName, pageable);
        Page<MatchStatsResponse> matchStatsResPage = matchPage.map(match -> MatchStatsResponse.of(match, playerName));
        PageDTO<MatchStatsResponse> matchResPageDTO = PageDTO.of(matchStatsResPage);

        return ResponseEntity.ok(success(SearchPlayerResponse.of(playerName, matchResPageDTO)));
    }

    @GetMapping("/{id}/info")
    @Operation(
            summary = "매치 정보 조회"
    )
    public ResponseEntity<ApiResult<MatchInfoResponse>> findMatchInfo(
            @PathVariable final String id
    ) {
        MatchInfoResponse result = matchService.findMatchInfo(id);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/result")
    @Operation(
            summary = "플레이어의 매치 결과 조회"
    )
    public ResponseEntity<ApiResult<MatchResultResponse>> findMatchResultByPlayer(
            @PathVariable final String id,
            @PathVariable final String playerName
    ) {
        MatchResultResponse result = matchService.findMatchResultByPlayer(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/roster")
    @Operation(
            summary = "매치를 함께한 팀원 리스트 조회"
    )
    public ResponseEntity<ApiResult<RosterResponse>> findRoster(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        RosterResponse result = matchService.findRoster(id, playerName);

        return ResponseEntity.ok(success(result));
    }
}
