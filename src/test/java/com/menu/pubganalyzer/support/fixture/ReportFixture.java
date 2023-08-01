package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.dto.AnalyzerRes;
import com.menu.pubganalyzer.domain.dto.MatchInfoRes;
import com.menu.pubganalyzer.domain.dto.MatchResultRes;
import com.menu.pubganalyzer.domain.dto.ReportRes;

//import static com.menu.pubganalyzer.support.fixture.AnalyzerFixture.ANALYZER;
import static com.menu.pubganalyzer.support.fixture.AnalyzerFixture.OFFICIAL_ANALYZER;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.*;

public class ReportFixture {
    private static final MatchInfoRes MATCH_INFO = MatchInfoRes.from(MATCH);
    private static final MatchResultRes MATCH_RESULT = MatchResultRes.of(MATCH, ROSTER, PARTICIPANT);
//    private static final AnalyzerRes ANALYZER_RESPONSE = AnalyzerRes.of(ANALYZER);
    private static final AnalyzerRes OFFICIAL_ANALYZER_RESPONSE = AnalyzerRes.of(OFFICIAL_ANALYZER);
//    public static final ReportRes REPORT = ReportRes.of(MATCH_INFO, MATCH_RESULT, ANALYZER_RESPONSE);
    public static final ReportRes OFFICIAL_REPORT = ReportRes.of(MATCH_INFO, MATCH_RESULT, OFFICIAL_ANALYZER_RESPONSE);
}
