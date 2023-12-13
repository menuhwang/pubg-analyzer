package com.menu.pubganalyzer.fetch.controller;

import com.menu.pubganalyzer.fetch.service.FetchAPIService;
import com.menu.pubganalyzer.support.admin.AdminOnly;
import com.menu.pubganalyzer.common.dto.response.ApiResult;
import com.menu.pubganalyzer.util.pubg.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubg.response.player.PlayersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.menu.pubganalyzer.common.dto.response.ApiResultUtil.success;

@Tag(
        name = "PUBG API",
        description = "PUBG API를 호출합니다."
)
@RestController
@RequestMapping("/fetch")
@RequiredArgsConstructor
public class FetchPubgAPIRestController {
    private final FetchAPIService fetchAPIService;

    @AdminOnly
    @GetMapping("player")
    @Operation(
            summary = "플레이어 조회",
            description = "https://api.pubg.com/shards/{shard}/players?filter[playerNames]={nickname}을 호출하여 결과를 출력합니다."
    )
    public ResponseEntity<ApiResult<PlayersResponse>> fetchPlayer(
            @RequestParam String shard,
            @RequestParam String nickname) {
        PlayersResponse response = fetchAPIService.player(shard, nickname);

        return ResponseEntity.ok(success(response));
    }

    @AdminOnly
    @GetMapping("match")
    @Operation(
            summary = "매치 조회",
            description = "https://api.pubg.com/shards/{shard}/matches/{id}을 호출하여 결과를 출력합니다."
    )
    public ResponseEntity<ApiResult<MatchResponse>> fetchMatch(
            @RequestParam String shard,
            @RequestParam String id) {
        MatchResponse response = fetchAPIService.match(shard, id);

        return ResponseEntity.ok(success(response));
    }
}
