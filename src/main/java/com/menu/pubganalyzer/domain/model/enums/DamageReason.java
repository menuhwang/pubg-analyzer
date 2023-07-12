package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    private final String label;
    private final String kor;

    DamageReason(String label, String kor) {
        this.label = label;
        this.kor = kor;
    }

    public String getKor() {
        return kor;
    }

    public static DamageReason of(String label) {
        for (DamageReason damageReason : values()) {
            if (damageReason.label.equals(label)) return damageReason;
        }
        log.warn("DamageReason not found [{}]", label);
        return NOT_FOUND;
    }
}
