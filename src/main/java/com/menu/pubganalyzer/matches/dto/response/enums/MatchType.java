package com.menu.pubganalyzer.matches.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MatchType {
    NOT_FOUND("", ""),
    AIROYALE("AI mode", "ai모드"),
    ARCADE("arcade", "아케이드"),
    COMPETITIVE("competitive", "경쟁전"),
    CUSTOM("custom", "커스텀 매치"),
    EVENT("event", "이벤트 매치"),
    OFFICIAL("official", "오피셜 매치"),
    SEASONAL("seasonal", "시즌 매치"),
    TRAINING("training", "연습"),
    ;

    private final String eng;
    private final String kor;

    MatchType(String eng, String kor) {
        this.eng = eng;
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
