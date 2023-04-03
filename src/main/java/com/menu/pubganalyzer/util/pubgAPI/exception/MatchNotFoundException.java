package com.menu.pubganalyzer.util.pubgAPI.exception;

public class MatchNotFoundException extends PubgAPIException {
    public static String MESSAGE_PATTERN = "해당 매치를 찾을 수 없습니다. [matchId=%s]";
    public MatchNotFoundException(String matchId) {
        super(String.format(MESSAGE_PATTERN, matchId));
    }
}
