package com.menu.pubganalyzer.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractApplicationException extends RuntimeException {
    private final HttpStatus httpStatus;
    public AbstractApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }

    public AbstractApplicationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.httpStatus = errorCode.getHttpStatus();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
