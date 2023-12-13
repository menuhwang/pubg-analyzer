package com.menu.pubganalyzer.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 플레이어를 찾을 수 없습니다."),
    ILLEGAL_PLAYER_NAME(HttpStatus.NOT_FOUND, "잘못된 플레이어 이름입니다."),
    MATCH_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 매치를 찾을 수 없습니다."),
    PARTICIPANT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 참가자를 찾을 수 없습니다."),
    LINK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 링크를 찾을 수 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 발생."),
    AUTHORIZATION(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
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
