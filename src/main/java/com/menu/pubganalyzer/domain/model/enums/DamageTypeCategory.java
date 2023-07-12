package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DamageTypeCategory {
    NOT_FOUND("Not_Found", "Not Found"),
    EMPTY("", ""),
    DAMAGE_BLIZZARD("Damage_Blizzard", "Blizzard Damage"),
    DAMAGE_BLUEZONE("Damage_BlueZone", "Bluezone Damage"),
    DAMAGE_BLUEZONEGRENADE("Damage_BlueZoneGrenade", "Bluezone Grenade Damage"),
    DAMAGE_DRONEPACKAGE("Damage_DronePackage", "Drone Damage"),
    DAMAGE_DROWN("Damage_Drown", "Drowning Damage"),
    DAMAGE_EXPLOSION_AIRCRAFT("Damage_Explosion_Aircraft", "Aircraft Explosion Damage"),
    DAMAGE_EXPLOSION_BLACKZONE("Damage_Explosion_BlackZone", "Blackzone Damage"),
    DAMAGE_EXPLOSION_BREACH("Damage_Explosion_Breach", "Breach Explosion Damage"),
    DAMAGE_EXPLOSION_C4("Damage_Explosion_C4", "C4 Explosion Damage"),
    DAMAGE_EXPLOSION_GASPUMP("Damage_Explosion_GasPump", "Gas Pump Explosion"),
    DAMAGE_EXPLOSION_GRENADE("Damage_Explosion_Grenade", "Grenade Explosion Damage"),
    DAMAGE_EXPLOSION_JERRYCAN("Damage_Explosion_JerryCan", "Jerrycan Explosion Damage"),
    DAMAGE_EXPLOSION_LOOTTRUCK("Damage_Explosion_LootTruck", "Loot Truck Explosion Damage"),
    DAMAGE_EXPLOSION_MORTAR("Damage_Explosion_Mortar", "Mortar Explosion"),
    DAMAGE_EXPLOSION_PANZERFAUSTBACKBLAST("Damage_Explosion_PanzerFaustBackBlast", "Panzerfaust Backblast Damage"),
    DAMAGE_EXPLOSION_PANZERFAUSTWARHEAD("Damage_Explosion_PanzerFaustWarhead", "Panzerfaust Explosion Damage"),
    DAMAGE_EXPLOSION_PANZERFAUSTWARHEADVEHICLEARMORPENETRATION("Damage_Explosion_PanzerFaustWarheadVehicleArmorPenetration", "Panzerfaust Explosion Damage"),
    DAMAGE_EXPLOSION_PROPANETANK("Damage_Explosion_PropaneTank", "Propane Tank"),
    DAMAGE_EXPLOSION_REDZONE("Damage_Explosion_RedZone", "Redzone Explosion Damage"),
    DAMAGE_EXPLOSION_STICKYBOMB("Damage_Explosion_StickyBomb", "Sticky Bomb Explosion Damage"),
    DAMAGE_EXPLOSION_VEHICLE("Damage_Explosion_Vehicle", "Vehicle Explosion Damage"),
    DAMAGE_GROGGY("Damage_Groggy", "Bleed out damage"),
    DAMAGE_GUN("Damage_Gun", "Gun Damage"),
    DAMAGE_GUN_PENETRATE_BRDM("Damage_Gun_Penetrate_BRDM", "BRDM"),
    DAMAGE_HELICOPTERHIT("Damage_HelicopterHit", "Pillar Scout Helicopter Damage"),
    DAMAGE_INSTANT_FALL("Damage_Instant_Fall", "Fall Damage"),
    DAMAGE_KILLTRUCKHIT("Damage_KillTruckHit", "Kill Truck Hit"),
    DAMAGE_KILLTRUCKTURRET("Damage_KillTruckTurret", "Kill Truck Turret Damage"),
    DAMAGE_LAVA("Damage_Lava", "Lava Damage"),
    DAMAGE_LOOTTRUCKHIT("Damage_LootTruckHit", "Loot Truck Damage"),
    DAMAGE_MELEE("Damage_Melee", "Melee Damage"),
    DAMAGE_MELEETHROW("Damage_MeleeThrow", "Melee Throw Damage"),
    DAMAGE_MOLOTOV("Damage_Molotov", "Molotov Damage"),
    DAMAGE_MONSTER("Damage_Monster", "Monster Damage"),
    DAMAGE_NONE("Damage_None", "No Damage"),
    DAMAGE_PUNCH("Damage_Punch", "Punch Damage"),
    DAMAGE_SHIPHIT("Damage_ShipHit", "Ferry Damage"),
    DAMAGE_TRAINHIT("Damage_TrainHit", "Train Damage"),
    DAMAGE_VEHICLECRASHHIT("Damage_VehicleCrashHit", "Vehicle Crash Damage"),
    DAMAGE_VEHICLEHIT("Damage_VehicleHit", "Vehicle Damage"),
    SPIKETRAP("SpikeTrap", "Spike Trap damage"),
    ;

    private final String label;
    private final String eng;

    DamageTypeCategory(String label, String eng) {
        this.label = label;
        this.eng = eng;
    }

    public static DamageTypeCategory of(String label) {
        for (DamageTypeCategory damageTypeCategory : values()) {
            if (damageTypeCategory.label.equals(label)) return damageTypeCategory;
        }
        log.warn("DamageTypeCategory not found [{}]", label);
        return NOT_FOUND;
    }
}
