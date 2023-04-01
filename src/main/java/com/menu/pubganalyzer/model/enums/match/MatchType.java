package com.menu.pubganalyzer.model.enums.match;

public enum MatchType {
    AIROYALE("ai모드"),
    ARCADE("아케이드"),
    CUSTOM("커스텀 매치"),
    EVENT("이벤트 매치"),
    OFFICIAL("오피셜 매치"),
    SEASONAL("시즌 매치"),
    TRAINING("연습"),
    ;

    private final String title;

    MatchType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
