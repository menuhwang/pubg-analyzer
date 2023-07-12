package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DamageTypeCategory {
    NOT_FOUND("Not Found", ""),
    EMPTY("", ""),
    DAMAGE_BLIZZARD("Blizzard Damage", ""),
    DAMAGE_BLUEZONE("Bluezone Damage", ""),
    DAMAGE_BLUEZONEGRENADE("Bluezone Grenade Damage", ""),
    DAMAGE_DRONEPACKAGE("Drone Damage", ""),
    DAMAGE_DROWN("Drowning Damage", ""),
    DAMAGE_EXPLOSION_AIRCRAFT("Aircraft Explosion Damage", ""),
    DAMAGE_EXPLOSION_BLACKZONE("Blackzone Damage", ""),
    DAMAGE_EXPLOSION_BREACH("Breach Explosion Damage", ""),
    DAMAGE_EXPLOSION_C4("C4 Explosion Damage", ""),
    DAMAGE_EXPLOSION_GASPUMP("Gas Pump Explosion", ""),
    DAMAGE_EXPLOSION_GRENADE("Grenade Explosion Damage", ""),
    DAMAGE_EXPLOSION_JERRYCAN("Jerrycan Explosion Damage", ""),
    DAMAGE_EXPLOSION_LOOTTRUCK("Loot Truck Explosion Damage", ""),
    DAMAGE_EXPLOSION_MORTAR("Mortar Explosion", ""),
    DAMAGE_EXPLOSION_PANZERFAUSTBACKBLAST("Panzerfaust Backblast Damage", ""),
    DAMAGE_EXPLOSION_PANZERFAUSTWARHEAD("Panzerfaust Explosion Damage", ""),
    DAMAGE_EXPLOSION_PANZERFAUSTWARHEADVEHICLEARMORPENETRATION("Panzerfaust Explosion Damage", ""),
    DAMAGE_EXPLOSION_PROPANETANK("Propane Tank", ""),
    DAMAGE_EXPLOSION_REDZONE("Redzone Explosion Damage", ""),
    DAMAGE_EXPLOSION_STICKYBOMB("Sticky Bomb Explosion Damage", ""),
    DAMAGE_EXPLOSION_VEHICLE("Vehicle Explosion Damage", ""),
    DAMAGE_GROGGY("Bleed out damage", ""),
    DAMAGE_GUN("Gun Damage", ""),
    DAMAGE_GUN_PENETRATE_BRDM("BRDM", ""),
    DAMAGE_HELICOPTERHIT("Pillar Scout Helicopter Damage", ""),
    DAMAGE_INSTANT_FALL("Fall Damage", ""),
    DAMAGE_KILLTRUCKHIT("Kill Truck Hit", ""),
    DAMAGE_KILLTRUCKTURRET("Kill Truck Turret Damage", ""),
    DAMAGE_LAVA("Lava Damage", ""),
    DAMAGE_LOOTTRUCKHIT("Loot Truck Damage", ""),
    DAMAGE_MELEE("Melee Damage", ""),
    DAMAGE_MELEETHROW("Melee Throw Damage", ""),
    DAMAGE_MOLOTOV("Molotov Damage", ""),
    DAMAGE_MONSTER("Monster Damage", ""),
    DAMAGE_NONE("No Damage", ""),
    DAMAGE_PUNCH("Punch Damage", ""),
    DAMAGE_SHIPHIT("Ferry Damage", ""),
    DAMAGE_TRAINHIT("Train Damage", ""),
    DAMAGE_VEHICLECRASHHIT("Vehicle Crash Damage", ""),
    DAMAGE_VEHICLEHIT("Vehicle Damage", ""),
    SPIKETRAP("Spike Trap damage", ""),
    ;

    private final String eng;
    private final String kor;

    DamageTypeCategory(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public static DamageTypeCategory of(String name) {
        for (DamageTypeCategory damageTypeCategory : values()) {
            if (damageTypeCategory.name().equals(name)) return damageTypeCategory;
        }
        log.error("Enum DamageTypeCategory not found [{}]", name);
        return NOT_FOUND;
    }
}
