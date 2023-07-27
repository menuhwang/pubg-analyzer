package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.dto.MatchInfoRes;
import com.menu.pubganalyzer.domain.dto.MatchResultRes;
import com.menu.pubganalyzer.domain.dto.ReportRes;

import static com.menu.pubganalyzer.support.fixture.AnalyzerFixture.ANALYZER;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.*;

public class ReportFixture {
    private static final MatchInfoRes MATCH_INFO = MatchInfoRes.from(MATCH);
    private static final MatchResultRes MATCH_RESULT = MatchResultRes.of(MATCH, ROSTER, PARTICIPANT);
    private static final AnalyzerRes ANALYZER_RESPONSE = AnalyzerRes.of(ANALYZER);
    public static final ReportRes REPORT = ReportRes.of(MATCH_INFO, MATCH_RESULT, ANALYZER_RESPONSE);
}
