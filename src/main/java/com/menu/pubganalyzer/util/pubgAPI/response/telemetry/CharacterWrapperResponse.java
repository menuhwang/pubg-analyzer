package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterWrapperResponse {
    private CharacterResponse character;
    private String primaryWeaponFirst;
    private String primaryWeaponSecond;
    private String secondaryWeapon;
    private int spawnKitIndex;
}
