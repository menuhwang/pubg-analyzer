package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.MatchDeleteRequest;
import com.menu.pubganalyzer.service.FetchAPIService;
import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {
    private final MatchService matchService;
    private final FetchAPIService fetchAPIService;

    @DeleteMapping("/matches")
    public ApiResult<?> deleteMatches(@RequestBody MatchDeleteRequest request) {
        matchService.deleteById(request.getMatches());

        return ApiResultUtil.success(204, "delete done");
    }

    @GetMapping("/fetch/player")
    public ResponseEntity<ApiResult<?>> fetchPlayer(
            @RequestParam String shard,
            @RequestParam String nickname) {
        PlayersResponse response;
        try {
            response = fetchAPIService.player(shard, nickname);
        } catch (PubgAPIPlayerNotFoundException e) {
            return ResponseEntity.status(404).body(ApiResultUtil.error(404, "player not found"));
        }

        return ResponseEntity.ok(ApiResultUtil.success(response));
    }

    @GetMapping("/fetch/match")
    public ResponseEntity<ApiResult<?>> fetchMatch(
            @RequestParam String shard,
            @RequestParam String matchId) {
        return ResponseEntity.ok(ApiResultUtil.success(fetchAPIService.match(shard, matchId)));
    }
}
