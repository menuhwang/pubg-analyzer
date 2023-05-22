package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.service.SearchPlayerService;
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
    public ResponseEntity<Void> renew(@PathVariable String shardId, @PathVariable String nickname) {
        searchPlayerService.renew(Shard.valueOf(shardId), nickname);
        return ResponseEntity.ok().build();
    }
}
