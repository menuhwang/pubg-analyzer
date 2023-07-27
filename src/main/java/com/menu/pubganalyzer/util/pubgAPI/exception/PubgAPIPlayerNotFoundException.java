package com.menu.pubganalyzer.util.pubgAPI.exception;

import org.springframework.http.HttpStatus;

public class PubgAPIPlayerNotFoundException extends PubgAPIException {
    public static final String DEFAULT_MSG = "해당 플레이어를 찾을 수 없습니다.";
    public PubgAPIPlayerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public PubgAPIPlayerNotFoundException() {
        this(DEFAULT_MSG);
    }
}
