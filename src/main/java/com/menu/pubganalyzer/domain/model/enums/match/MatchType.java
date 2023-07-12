package com.menu.pubganalyzer.domain.model.enums.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MatchType {
    NOT_FOUND(""),
    AIROYALE("ai모드"),
    ARCADE("아케이드"),
    COMPETITIVE("경쟁전"),
    CUSTOM("커스텀 매치"),
    EVENT("이벤트 매치"),
    OFFICIAL("오피셜 매치"),
    SEASONAL("시즌 매치"),
    TRAINING("연습"),
    ;

    private final String kor;

    MatchType(String kor) {
        this.kor = kor;
    }

    public static MatchType of(String name) {
        for (MatchType matchType : values()) {
            if (matchType.name().equals(name)) return matchType;
        }
        log.error("Enum MatchType not found [{}]", name);
        return NOT_FOUND;
    }
}
