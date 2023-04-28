package com.menu.pubganalyzer.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 플레이어를 찾을 수 없습니다."),
    MATCH_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 매치를 찾을 수 없습니다."),
    TELEMETRY_FILE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "로그 파일을 찾을 수 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 발생."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
