package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.service.FetchAPIService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/api/admin/v2/fetch")
@RequiredArgsConstructor
public class FetchPubgApiController {
    private final FetchAPIService fetchAPIService;

    @GetMapping("player")
    public ResponseEntity<ApiResult<PlayersResponse>> fetchPlayer(
            @RequestParam String shard,
            @RequestParam String nickname) {
        PlayersResponse response = fetchAPIService.player(shard, nickname);

        return ResponseEntity.ok(success(response));
    }

    @GetMapping("match")
    public ResponseEntity<ApiResult<MatchResponse>> fetchMatch(
            @RequestParam String shard,
            @RequestParam String id) {
        MatchResponse response = fetchAPIService.match(shard, id);

        return ResponseEntity.ok(success(response));
    }
}
