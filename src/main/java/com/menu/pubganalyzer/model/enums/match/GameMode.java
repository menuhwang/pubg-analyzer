package com.menu.pubganalyzer.model.enums.match;

public enum GameMode {
    DUO("duo", "Duo TPP"),
    DUO_FPP("duo-fpp", "Duo FPP"),
    SOLO("solo", "Solo TPP"),
    SOLO_FPP("solo-fpp", "Solo FPP"),
    SQUAD("squad", "Squad TPP"),
    SQUAD_FPP("squad-fpp", "Squad FPP"),
    CONQUEST_DUO("conquest-duo", "Conquest Duo TPP"),
    CONQUEST_DUO_FPP("conquest-duo-fpp", "Conquest Duo FPP"),
    CONQUEST_SOLO("conquest-solo", "Conquest Solo TPP"),
    CONQUEST_SOLO_FPP("conquest-solo-fpp", "Conquest Solo FPP"),
    CONQUEST_SQUAD("conquest-squad", "Conquest Squad TPP"),
    CONQUEST_SQUAD_FPP("conquest-squad-fpp", "Conquest Squad FPP"),
    ESPORTS_DUO("esports-duo", "Esports Duo TPP"),
    ESPORTS_DUO_FPP("esports-duo-fpp", "Esports Duo FPP"),
    ESPORTS_SOLO("esports-solo", "Esports Solo TPP"),
    ESPORTS_SOLO_FPP("esports-solo-fpp", "Esports Solo FPP"),
    ESPORTS_SQUAD("esports-squad", "Esports Squad TPP"),
    ESPORTS_SQUAD_FPP("esports-squad-fpp", "Esports Squad FPP"),
    NORMAL_DUO("normal-duo", "Duo TPP"),
    NORMAL_DUO_FPP("normal-duo-fpp", "Duo FPP"),
    NORMAL_SOLO("normal-solo", "Solo TPP"),
    NORMAL_SOLO_FPP("normal-solo-fpp", "Solo FPP"),
    NORMAL_SQUAD("normal-squad", "Squad TPP"),
    NORMAL_SQUAD_FPP("normal-squad-fpp", "Squad FPP"),
    WAR_DUO("war-duo", "War Duo TPP"),
    WAR_DUO_FPP("war-duo-fpp", "War Duo FPP"),
    WAR_SOLO("war-solo", "War Solo TPP"),
    WAR_SOLO_FPP("war-solo-fpp", "War Solo FPP"),
    WAR_SQUAD("war-squad", "Squad TPP"),
    WAR_SQUAD_FPP("war-squad-fpp", "War Squad FPP"),
    ZOMBIE_DUO("zombie-duo", "Zombie Duo TPP"),
    ZOMBIE_DUO_FPP("zombie-duo-fpp", "Zombie Duo FPP"),
    ZOMBIE_SOLO("zombie-solo", "Zombie Solo TPP"),
    ZOMBIE_SOLO_FPP("zombie-solo-fpp", "Zombie Solo FPP"),
    ZOMBIE_SQUAD("zombie-squad", "Zombie Squad TPP"),
    ZOMBIE_SQUAD_FPP("zombie-squad-fpp", "Zombie Squad FPP"),
    LAB_TPP("lab-tpp", "Lab TPP"),
    LAB_FPP("lab-fpp", "Lab FPP"),
    TDM("tdm", "Team Deathmatch"),
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

    private String getTag() {
        return tag;
    }
}
