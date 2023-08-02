package com.menu.pubganalyzer.support.fixture;

import com.menu.pubganalyzer.domain.Analyzer;

//import static com.menu.pubganalyzer.support.fixture.LogPlayerKillFixture.LOG_PLAYER_KILLS;
import static com.menu.pubganalyzer.support.fixture.LogPlayerKillFixture.OFFICIAL_LOG_PLAYER_KILLS;
//import static com.menu.pubganalyzer.support.fixture.LogPlayerTakeDamageFixture.LOG_PLAYER_TAKE_DAMAGES;
import static com.menu.pubganalyzer.support.fixture.LogPlayerTakeDamageFixture.OFFICIAL_LOG_PLAYER_TAKE_DAMAGES;

public class AnalyzerFixture {
//    public static final Analyzer ANALYZER = Analyzer.init()
//            .logPlayerKills(LOG_PLAYER_KILLS)
//            .rosterLogPlayerTakeDamages(LOG_PLAYER_TAKE_DAMAGES)
//            .totalLogPlayerTakeDamage(LOG_PLAYER_TAKE_DAMAGES);

    public static final Analyzer OFFICIAL_ANALYZER = Analyzer.init()
            .logPlayerKills(OFFICIAL_LOG_PLAYER_KILLS)
            .rosterLogPlayerTakeDamages(OFFICIAL_LOG_PLAYER_TAKE_DAMAGES)
            .totalLogPlayerTakeDamage(OFFICIAL_LOG_PLAYER_TAKE_DAMAGES);
}
