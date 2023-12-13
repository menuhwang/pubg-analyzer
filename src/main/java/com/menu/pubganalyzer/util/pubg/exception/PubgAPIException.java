package com.menu.pubganalyzer.util.pubg.exception;

import org.springframework.http.HttpStatus;

public abstract class PubgAPIException extends RuntimeException {
    private final HttpStatus httpStatus;
    protected PubgAPIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
