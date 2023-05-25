package com.menu.pubganalyzer.util.pubgAPI.exception;

public class PubgAPIPlayerNotFoundException extends PubgAPIException {
    public static final String DEFAULT_MSG = "해당 플레이어를 찾을 수 없습니다.";
    public PubgAPIPlayerNotFoundException(String message) {
        super(message);
    }

    public PubgAPIPlayerNotFoundException() {
        this(DEFAULT_MSG);
    }
}
