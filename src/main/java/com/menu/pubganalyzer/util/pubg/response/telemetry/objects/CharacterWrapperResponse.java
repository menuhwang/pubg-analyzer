package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterWrapperResponse {
    private CharacterResponse character;
    private String primaryWeaponFirst;
    private String primaryWeaponSecond;
    private String secondaryWeapon;
    private int spawnKitIndex;

    public static CharacterWrapperResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to CharacterWrapperResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            CharacterResponse character = CharacterResponse.mappedBy(map.get("character"));
            String primaryWeaponFirst = (String) map.get("primaryWeaponFirst");
            String primaryWeaponSecond = (String) map.get("primaryWeaponSecond");
            String secondaryWeapon = (String) map.get("secondaryWeapon");
            int spawnKitIndex = (int) map.get("spawnKitIndex");

            return CharacterWrapperResponse.builder()
                    .character(character)
                    .primaryWeaponFirst(primaryWeaponFirst)
                    .primaryWeaponSecond(primaryWeaponSecond)
                    .secondaryWeapon(secondaryWeapon)
                    .spawnKitIndex(spawnKitIndex)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
