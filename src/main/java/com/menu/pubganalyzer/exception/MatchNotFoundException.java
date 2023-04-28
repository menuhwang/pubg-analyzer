package com.menu.pubganalyzer.exception;

public class MatchNotFoundException extends AbstractApplicationException {
    public MatchNotFoundException() {
        super(ErrorCode.MATCH_NOT_FOUND);
    }
}
