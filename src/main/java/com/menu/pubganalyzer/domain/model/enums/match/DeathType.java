package com.menu.pubganalyzer.domain.model.enums.match;

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
