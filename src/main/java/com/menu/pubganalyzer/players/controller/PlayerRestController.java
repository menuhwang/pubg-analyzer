package com.menu.pubganalyzer.players.controller;

import com.menu.pubganalyzer.common.dto.response.ApiResult;
import com.menu.pubganalyzer.common.dto.response.ApiResultUtil;
import com.menu.pubganalyzer.players.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Tag(
        name = "플레이어",
        description = "플레이어 API"
)
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerRestController {
    private final PlayerService playerService;

    @PatchMapping("/{nickname}")
    @Operation(
            summary = "플레이어 매치 내역 업데이트"
    )
    public ResponseEntity<ApiResult<Void>> updateMatchHistory(
            @Valid @NotBlank @PathVariable String nickname) {
        playerService.updateMatchHistory(nickname);
        return ResponseEntity.ok().body(ApiResultUtil.success());
    }
}
