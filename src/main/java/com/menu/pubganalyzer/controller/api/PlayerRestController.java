package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.service.PlayerService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerRestController {
    private final PlayerService playerService;

    @PatchMapping("/{nickname}")
    public ResponseEntity<ApiResult<Void>> updateMatchHistory(
            @Valid @NotBlank @PathVariable String nickname) {
        playerService.updateMatchHistory(nickname);
        return ResponseEntity.ok().body(ApiResultUtil.success());
    }
}
