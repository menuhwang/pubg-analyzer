package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ReportRes;
import com.menu.pubganalyzer.service.ReportService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportRestController {
    private final ReportService reportService;

    @GetMapping("/match/{matchId}/player/{nickname}")
    public ResponseEntity<ApiResult<ReportRes>> getMatchReport(
            @Valid @NotBlank @PathVariable String matchId,
            @Valid @NotBlank @PathVariable String nickname) {
        ReportRes report = reportService.report(matchId, nickname);

        return ResponseEntity.ok(success(report));
    }
}
