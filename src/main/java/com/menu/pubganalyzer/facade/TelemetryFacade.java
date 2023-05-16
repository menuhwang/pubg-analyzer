package com.menu.pubganalyzer.facade;

import com.menu.pubganalyzer.domain.Analyzer;
import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Roster;

public interface TelemetryFacade {
    Analyzer findLogs(Match match, Roster roster);
}
