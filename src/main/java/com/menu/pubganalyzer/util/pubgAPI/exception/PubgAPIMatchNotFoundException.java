package com.menu.pubganalyzer.util.pubgAPI.exception;

public class PubgAPIMatchNotFoundException extends PubgAPIException {
    public static String MESSAGE_PATTERN = "해당 매치를 찾을 수 없습니다. [matchId=%s]";
    public PubgAPIMatchNotFoundException(String matchId) {
        super(String.format(MESSAGE_PATTERN, matchId));
    }
}
