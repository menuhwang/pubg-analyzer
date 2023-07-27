package com.menu.pubganalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReportRes {
    private final MatchInfoRes matchInfo;
    private final MatchResultRes matchResult;
    private final AnalyzerRes data;

    public static ReportRes of(
            MatchInfoRes matchInfo,
            MatchResultRes matchResult,
            AnalyzerRes data) {
        return ReportRes.builder()
                .matchInfo(matchInfo)
                .matchResult(matchResult)
                .data(data)
                .build();
    }
}
