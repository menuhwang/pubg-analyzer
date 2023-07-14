package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Shard {
    CONSOLE,
    KAKAO,
    PSN,
    STADIA,
    STEAM,
    TOURNAMENT,
    XBOX,
    ;
}
