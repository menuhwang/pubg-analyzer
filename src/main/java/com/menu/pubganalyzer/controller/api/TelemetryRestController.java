package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.DamageLogRes;
import com.menu.pubganalyzer.service.TelemetryService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/telemetries")
@RequiredArgsConstructor
public class TelemetryRestController {
    private final TelemetryService telemetryService;

    @GetMapping("/{id}/player/{playerName}/kills/damages")
    public ResponseEntity<ApiResult<List<DamageLogRes>>> finDamagesOfKill(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<DamageLogRes> result = telemetryService.findDamagesOfKill(id, playerName);

        return ResponseEntity.ok(success(result));
    }
}
