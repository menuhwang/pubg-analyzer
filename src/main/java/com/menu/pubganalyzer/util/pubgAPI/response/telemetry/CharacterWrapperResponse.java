package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CharacterWrapperResponse {
    private CharacterResponse character;
    private String primaryWeaponFirst;
    private String primaryWeaponSecond;
    private String secondaryWeapon;
    private int spawnKitIndex;
}
