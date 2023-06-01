package com.menu.pubganalyzer.exception;

public class MatchNotFoundException extends AbstractApplicationException {
    private final String matchId;
    public MatchNotFoundException() {
        this("");
    }

    public MatchNotFoundException(String matchId) {
        super(ErrorCode.MATCH_NOT_FOUND);
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }
}
