package com.menu.pubganalyzer.telemetries.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Weapon {
    NOT_FOUND("Not Found", "Not Found"),
    EMPTY("", ""),
    WEAPACE32_C("ACE32", "ACE32"),
    WEAPAK47_C("AKM", "AKM"),
    WEAPAUG_C("AUG A3", "AUG"),
    WEAPAWM_C("AWM", "AWM"),
    WEAPBERRETA686_C("S686", "S686"),
    WEAPBERYLM762_C("Beryl", "Beryl"),
    WEAPBIZONPP19_C("Bizon", "Bizon"),
    WEAPCOWBARPROJECTILE_C("Crowbar Projectile", "석궁"),
    WEAPCOWBAR_C("Crowbar", "석궁"),
    WEAPCROSSBOW_1_C("Crossbow", "석궁"),
    WEAPDP12_C("DBS", "DBS"),
    WEAPDP28_C("DP-28", "DP-28"),
    WEAPDESERTEAGLE_C("Deagle", "Deagle"),
    WEAPDUNCANSHK416_C("M416", "M416"),
    WEAPFAMASG2_C("Famas", "Famas"),
    WEAPFNFAL_C("SLR", "SLR"),
    WEAPG18_C("P18C", "P18C"),
    WEAPG36C_C("G36C", "G36C"),
    WEAPGROZA_C("Groza", "Groza"),
    WEAPHK416_C("M416", "M416"),
    WEAPJULIESKAR98K_C("Kar98k", "Kar98k"),
    WEAPK2_C("K2", "K2"),
    WEAPKAR98K_C("Kar98k", "Kar98k"),
    WEAPL6_C("Lynx AMR", "링스"),
    WEAPLUNCHMEATSAK47_C("AKM", "AKM"),
    WEAPM16A4_C("M16A4", "M16A4"),
    WEAPM1911_C("P1911", "P1911"),
    WEAPM249_C("M249", "M249"),
    WEAPM24_C("M24", "M24"),
    WEAPM9_C("P92", "P92"),
    WEAPMG3_C("MG3", "MG3"),
    WEAPMP5K_C("MP5K", "MP5K"),
    WEAPMP9_C("MP9", "MP9"),
    WEAPMACHETEPROJECTILE_C("Machete Projectile", "마체테"),
    WEAPMACHETE_C("Machete", "마체테"),
    WEAPMADSQBU88_C("QBU88", "QBU88"),
    WEAPMINI14_C("Mini 14", "Mini14"),
    WEAPMK12_C("Mk12", "Mk12"),
    WEAPMK14_C("Mk14 EBR", "Mk14"),
    WEAPMK47MUTANT_C("Mk47 Mutant", "Mutant"),
    WEAPMOSINNAGANT_C("Mosin-Nagant", "모신나강"),
    WEAPNAGANTM1895_C("R1895", "R1895"),
    WEAPORIGINS12_C("O12", "O12"),
    WEAPP90_C("P90", "P90"),
    WEAPPANPROJECTILE_C("Pan Projectile", "프라이팬"),
    WEAPPAN_C("Pan", "프라이팬"),
    WEAPPANZERFAUST100M1_C("Panzerfaust", "판처"),
    WEAPQBU88_C("QBU88", "QBU88"),
    WEAPQBZ95_C("QBZ95", "QBZ95"),
    WEAPRHINO_C("R45", "R45"),
    WEAPSCAR_L_C("SCAR-L", "SCAR-L"),
    WEAPSKS_C("SKS", "SKS"),
    WEAPSAIGA12_C("S12K", "S12K"),
    WEAPSAWNOFF_C("Sawed-off", "소드오프"),
    WEAPSICKLEPROJECTILE_C("Sickle Projectile", "낫"),
    WEAPSICKLE_C("Sickle", "낫"),
    WEAPTHOMPSON_C("Tommy Gun", "Tommy Gun"),
    WEAPTURRET_KILLTRUCK_MAIN_C("Kill Truck Turret", "터렛"),
    WEAPUMP_C("UMP9", "UMP9"),
    WEAPUZI_C("Micro Uzi", "Uzi"),
    WEAPVSS_C("VSS", "VSS"),
    WEAPVECTOR_C("Vector", "Vector"),
    WEAPWIN94_C("Win94", "Win94"),
    WEAPWINCHESTER_C("S1897", "S1897"),
    WEAPVZ61SKORPION_C("Skorpion", "Skorpion"),
    WEAPDRAGUNOV_C("Dragunov", "드라구노프"),
    WEAPJS9_C("JS9", "JS9"),
    ;

    private final String eng;
    private final String kor;

    Weapon(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public static Weapon of(String name) {
        if (name.isBlank()) return null;
        name = name.toUpperCase().replaceAll("-", "_");
        for (Weapon weapon : values()) {
            if (weapon.name().equals(name)) return weapon;
        }
        log.error("Enum Weapon not found [{}]", name);
        return NOT_FOUND;
    }
}
