package com.menu.pubganalyzer.model.enums.match;

public enum MapName {
    Baltic_Main("Erangel (Remastered)", "에란겔"),
    Chimera_Main("Paramo", "파라모"),
    Desert_Main("Miramar", "미라마"),
    DihorOtok_Main("Vikendi", "비켄디"),
    Erangel_Main("Erangel", "에란겔"),
    Heaven_Main("Haven", "헤이븐"),
    Kiki_Main("Deston", "데스턴"),
    Range_Main("Camp Jackal", "훈련장"),
    Savage_Main("Sanhok", "사녹"),
    Summerland_Main("Karakin", "카라킨"),
    Tiger_Main("Taego", "테이고"),
    ;

    private final String mapName;
    private final String mapNameKor;

    MapName(String name, String nameKor) {
        this.mapName = name;
        this.mapNameKor = nameKor;
    }

    public String getMapName() {
        return mapName;
    }

    public String getMapNameKor() {
        return mapNameKor;
    }
}
