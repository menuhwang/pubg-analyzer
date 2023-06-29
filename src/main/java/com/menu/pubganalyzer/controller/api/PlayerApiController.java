package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.service.SearchPlayerService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shard/{shardId}/players")
@RequiredArgsConstructor
public class PlayerApiController {
    private final SearchPlayerService searchPlayerService;

    @PatchMapping("/{nickname}")
    public ResponseEntity<ApiResult<Void>> renew(@PathVariable String shardId, @PathVariable String nickname) throws InterruptedException {
        searchPlayerService.updateMatchHistory(nickname);
        return ResponseEntity.ok().body(ApiResultUtil.success());
    }
}
