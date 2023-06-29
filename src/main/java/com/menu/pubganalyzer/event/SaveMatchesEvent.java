package com.menu.pubganalyzer.event;

import com.menu.pubganalyzer.domain.model.Match;

import java.util.Collection;

public class SaveMatchesEvent extends Event {
    private final Collection<Match> matches;

    private SaveMatchesEvent(Collection<Match> matches)  {
        this.matches = matches;
    }

    public static SaveMatchesEvent of(Collection<Match> matches) {
        return new SaveMatchesEvent(matches);
    }

    public Collection<Match> getMatches() {
        return matches;
    }
}
