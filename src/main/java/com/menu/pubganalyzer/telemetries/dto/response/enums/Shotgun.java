package com.menu.pubganalyzer.telemetries.dto.response.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Shotgun {
    WEAPBERRETA686_C(Weapon.WEAPBERRETA686_C, 9),
    WEAPDP12_C(Weapon.WEAPDP12_C, 9),
    WEAPSAIGA12_C(Weapon.WEAPSAIGA12_C, 9),
    WEAPSAWNOFF_C(Weapon.WEAPSAWNOFF_C, 9),
    WEAPWINCHESTER_C(Weapon.WEAPWINCHESTER_C, 9),
    ;

    private final Weapon weapon;
    private final int ammo;

    private static final Map<Weapon, Shotgun> WEAPON_SHOTGUN = new HashMap<>();

    static {
        for (Shotgun shotgun : values()) {
            WEAPON_SHOTGUN.put(shotgun.getWeapon(), shotgun);
        }
    }

    Shotgun(Weapon weapon, int ammo) {
        this.weapon = weapon;
        this.ammo = ammo;
    }

    public static Shotgun of(Weapon weapon) {
        return WEAPON_SHOTGUN.get(weapon);
    }

    public static boolean contains(Weapon weapon) {
        return WEAPON_SHOTGUN.containsKey(weapon);
    }
}
