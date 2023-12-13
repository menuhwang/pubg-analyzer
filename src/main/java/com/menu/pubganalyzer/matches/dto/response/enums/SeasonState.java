package com.menu.pubganalyzer.matches.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SeasonState {
    CLOSED,
    PREPARE,
    PROGRESS,
    ;
}
