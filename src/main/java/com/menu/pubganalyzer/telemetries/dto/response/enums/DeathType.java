package com.menu.pubganalyzer.telemetries.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeathType {
    ALIVE,
    BYPLAYER,
    BYZONE,
    SUICIDE,
    LOGOUT,
    ;
}
