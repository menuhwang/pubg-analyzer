package com.menu.pubganalyzer.domain.model.enums.match;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GameMode {
    DUO("duo", "듀오"),
    DUO_FPP("duo-fpp", "듀오 1인칭"),
    SOLO("solo", "솔로"),
    SOLO_FPP("solo-fpp", "솔로 1인칭"),
    SQUAD("squad", "스쿼드"),
    SQUAD_FPP("squad-fpp", "스쿼드 1인칭"),
    CONQUEST_DUO("conquest-duo", "Conquest Duo TPP"),
    CONQUEST_DUO_FPP("conquest-duo-fpp", "Conquest Duo FPP"),
    CONQUEST_SOLO("conquest-solo", "Conquest Solo TPP"),
    CONQUEST_SOLO_FPP("conquest-solo-fpp", "Conquest Solo FPP"),
    CONQUEST_SQUAD("conquest-squad", "Conquest Squad TPP"),
    CONQUEST_SQUAD_FPP("conquest-squad-fpp", "Conquest Squad FPP"),
    ESPORTS_DUO("esports-duo", "Esports 듀오"),
    ESPORTS_DUO_FPP("esports-duo-fpp", "Esports 듀오 1인칭"),
    ESPORTS_SOLO("esports-solo", "Esports 솔로"),
    ESPORTS_SOLO_FPP("esports-solo-fpp", "Esports 솔로 1인칭"),
    ESPORTS_SQUAD("esports-squad", "Esports 스쿼드"),
    ESPORTS_SQUAD_FPP("esports-squad-fpp", "Esports 스쿼드 1인칭"),
    NORMAL_DUO("normal-duo", "커스텀 듀오"),
    NORMAL_DUO_FPP("normal-duo-fpp", "커스텀 듀오 1인칭"),
    NORMAL_SOLO("normal-solo", "커스텀 솔로"),
    NORMAL_SOLO_FPP("normal-solo-fpp", "커스텀 솔로 1인칭"),
    NORMAL_SQUAD("normal-squad", "커스텀 스쿼드"),
    NORMAL_SQUAD_FPP("normal-squad-fpp", "커스텀 스쿼드 1인칭"),
    WAR_DUO("war-duo", "워모드 듀오"),
    WAR_DUO_FPP("war-duo-fpp", "워모드 듀오 1인칭"),
    WAR_SOLO("war-solo", "워모드 솔로"),
    WAR_SOLO_FPP("war-solo-fpp", "워모드 솔로 1인칭"),
    WAR_SQUAD("war-squad", "워모드 스쿼드"),
    WAR_SQUAD_FPP("war-squad-fpp", "워모드 스쿼드 1인칭"),
    ZOMBIE_DUO("zombie-duo", "좀비 듀오"),
    ZOMBIE_DUO_FPP("zombie-duo-fpp", "좀비 듀오 1인칭"),
    ZOMBIE_SOLO("zombie-solo", "좀비 솔로"),
    ZOMBIE_SOLO_FPP("zombie-solo-fpp", "좀비 솔로 1인칭"),
    ZOMBIE_SQUAD("zombie-squad", "좀비 스쿼드"),
    ZOMBIE_SQUAD_FPP("zombie-squad-fpp", "좀비 스쿼드 1인칭"),
    LAB_TPP("lab-tpp", "실험실"),
    LAB_FPP("lab-fpp", "실험실 1인칭"),
    TDM("tdm", "팀 데스매치"),
    BLUEBALL("blueball", "존 태그"),
    ;

    private final String tag;
    private final String title;

    GameMode(String tag, String title) {
        this.tag = tag;
        this.title = title;
    }

    public static GameMode of(String tag) throws IllegalArgumentException {
        for (GameMode gameMode : values()) {
            if (gameMode.getTag().equals(tag)) return gameMode;
        }
        throw new IllegalArgumentException(String.format("해당하는 gameMode를 찾을 수 없습니다. [tag=%s]", tag));
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }
}
