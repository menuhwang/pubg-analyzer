package com.menu.pubganalyzer.domain.dao;

import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Roster;

public interface TelemetryDAO {
    Telemetry findByMatchId(Match match);
    Telemetry findByMatchAndRoster(Match match, Roster roster);
}
