package com.menu.pubganalyzer.telemetries.controller;

import com.menu.pubganalyzer.telemetries.dto.response.*;
import com.menu.pubganalyzer.telemetries.service.TelemetryService;
import com.menu.pubganalyzer.common.dto.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.menu.pubganalyzer.common.dto.response.ApiResultUtil.success;

@Tag(
        name = "텔레메트리",
        description = "텔레메트리 API"
)
@RestController
@RequestMapping("/telemetries")
@RequiredArgsConstructor
public class TelemetryRestController {
    private final TelemetryService telemetryService;

    @GetMapping("/{id}/player/{playerName}/kills/damages")
    @Operation(
            summary = "플레이어 킬에 대한 데미지 로그 조회"
    )
    public ResponseEntity<ApiResult<List<DamageLogResponse>>> finDamagesOfKill(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<DamageLogResponse> result = telemetryService.findDamagesOfKill(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/damages")
    @Operation(
            summary = "플레이어 데미지 로그 전체 조회"
    )
    public ResponseEntity<ApiResult<List<DamageLogResponse>>> findDamageLogByPlayer(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<DamageLogResponse> result = telemetryService.findDamageLogByPlayer(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/kills")
    @Operation(
            summary = "플레이어 킬 로그 조회"
    )
    public ResponseEntity<ApiResult<List<KillLogResponse>>> findKillLogs(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        List<KillLogResponse> result = telemetryService.findKillLogs(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/phase-damage-chart")
    @Operation(
            summary = "플레이어 페이즈 별 데미지 차트 조회"
    )
    public ResponseEntity<ApiResult<PhaseDamageChartResponse>> getPhaseDamageChart(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        PhaseDamageChartResponse result = telemetryService.getPhaseDamageChart(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/contribute-damage-chart")
    @Operation(
            summary = "데미지 기여도 차트 조회",
            description = "플레이어의 킬에 대한 팀원들의 데미지 기여도 차트를 조회합니다."
    )
    public ResponseEntity<ApiResult<ContributeDamageChartResponse>> getContributeDamageChart(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        ContributeDamageChartResponse result = telemetryService.getContributeDamageChart(id, playerName);

        return ResponseEntity.ok(success(result));
    }

    @GetMapping("/{id}/player/{playerName}/weapon-accuracy-chart")
    @Operation(
            summary = "무기 정확도 차트 조회",
            description = "무기 정확도 차트를 조회합니다."
    )
    public ResponseEntity<ApiResult<WeaponAccuracyChartResponse>> getWeaponAccuracy(
            @PathVariable String id,
            @PathVariable String playerName
    ) {
        WeaponAccuracyChartResponse result = telemetryService.getWeaponAccuracyChart(id, playerName);

        return ResponseEntity.ok(success(result));
    }
}
