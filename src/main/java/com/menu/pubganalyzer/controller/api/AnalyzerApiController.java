package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.service.AnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/analyzer")
@RequiredArgsConstructor
public class AnalyzerApiController {
    private final AnalyzerService analyzerService;

    @GetMapping("/match/{matchId}/player/{nickname}")
    public AnalyzerRes analyze(@PathVariable String matchId, @PathVariable String nickname) {
        return analyzerService.analyze(matchId, nickname);
    }
}
