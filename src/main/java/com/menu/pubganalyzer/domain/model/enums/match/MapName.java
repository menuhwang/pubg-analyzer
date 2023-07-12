package com.menu.pubganalyzer.domain.model.enums.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MapName {
    NOT_FOUND("", ""),
    BALTIC_MAIN("Erangel (Remastered)", "에란겔"),
    CHIMERA_MAIN("Paramo", "파라모"),
    DESERT_MAIN("Miramar", "미라마"),
    DIHOROTOK_MAIN("Vikendi", "비켄디"),
    ERANGEL_MAIN("Erangel", "에란겔"),
    HEAVEN_MAIN("Haven", "헤이븐"),
    KIKI_MAIN("Deston", "데스턴"),
    RANGE_MAIN("Camp Jackal", "훈련장"),
    SAVAGE_MAIN("Sanhok", "사녹"),
    SUMMERLAND_MAIN("Karakin", "카라킨"),
    TIGER_MAIN("Taego", "태이고"),
    ITALY_MAIN("Riviera", "리비에라")
    ;

    private final String eng;
    private final String kor;

    MapName(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public static MapName of(String name) {
        for (MapName mapName : values()) {
            if (mapName.name().equals(name)) return mapName;
        }
        log.error("Enum MapName not found [{}]", name);
        return NOT_FOUND;
    }
}
