package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ContributeDamageChartRes;
import com.menu.pubganalyzer.domain.dto.DamageLogRes;
import com.menu.pubganalyzer.domain.dto.KillLogRes;
import com.menu.pubganalyzer.domain.dto.PhaseDamageChartRes;
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

    @GetMapping("/{id}/player/{playerName}/damages")
    public ResponseEntity<ApiResult<List<DamageLogRes>>> findDamageLogByPlayer(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<DamageLogRes> result = telemetryService.findDamageLogByPlayer(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/kills")
    public ResponseEntity<ApiResult<List<KillLogRes>>> findKillLogs(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<KillLogRes> result = telemetryService.findKillLogs(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/phase-damage-chart")
    public ResponseEntity<ApiResult<PhaseDamageChartRes>> getPhaseDamageChart(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        PhaseDamageChartRes result = telemetryService.getPhaseDamageChart(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/contribute-damage-chart")
    public ResponseEntity<ApiResult<ContributeDamageChartRes>> getContributeDamageChart(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        ContributeDamageChartRes result = telemetryService.getContributeDamageChart(id, playerName);

        return ResponseEntity.ok(success(result));
    }
}
