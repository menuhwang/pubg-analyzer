package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DamageReason {
    NOT_FOUND("Not_Found", "Not Found"),
    EMPTY("", ""),
    ARMSHOT("ArmShot", "팔"),
    HEADSHOT("HeadShot", "헤드샷"),
    LEGSHOT("LegShot", "다리"),
    NONE("None", ""),
    NONSPECIFIC("NonSpecific", ""),
    PELVISSHOT("PelvisShot", "골반"),
    SIMLATEAIBEKILLED("SimlateAIBeKilled", "SimlateAIBeKilled"),
    SIMULATEAIBEKILLED("SimulateAIBeKilled", "SimulateAIBeKilled"),
    TORSOSHOT("TorsoShot", "몸통"),
    ;

    private final String eng;
    private final String kor;

    DamageReason(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public static DamageReason of(String name) {
        if (name.isBlank()) return null;
        for (DamageReason damageReason : values()) {
            if (damageReason.name().equals(name)) return damageReason;
        }
        log.error("Enum DamageReason not found [{}]", name);
        return NOT_FOUND;
    }
}
