package com.menu.pubganalyzer.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportRes {
    private final MatchInfoRes matchInfo;
    private final MatchResultRes matchResult;
    private final AnalyzerRes data;

    @Builder
    public ReportRes(
            MatchInfoRes matchInfo,
            MatchResultRes matchResult,
            AnalyzerRes data) {
        this.matchInfo = matchInfo;
        this.matchResult = matchResult;
        this.data = data;
    }

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
