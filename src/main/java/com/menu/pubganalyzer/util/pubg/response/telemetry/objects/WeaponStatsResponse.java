package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeaponStatsResponse {
    private String weapon;
    private int dBNODamage;
    private int dBNOHits;
    private int damage;
    private List<HitDetailResponse> hitDetails;
    private int hits;
    private int holdingTime;
    private int shots;

    public static WeaponStatsResponse mappedBy(Object o) {
        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to WeaponStatsResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String weapon = (String) map.get("weapon");
            int dBNODamage = (int) map.get("dBNODamage");
            int dBNOHits = (int) map.get("dBNOHits");
            int damage = (int) map.get("damage");

            @SuppressWarnings("unchecked")
            List<HitDetailResponse> hitDetails = ((List<Object>) map.get("hitDetails")).stream()
                    .map(HitDetailResponse::mappedBy)
                    .collect(Collectors.toList());

            int hits = (int) map.get("hits");
            int holdingTime = (int) map.get("holdingTime");
            int shots = (int) map.get("shots");

            return WeaponStatsResponse.builder()
                    .weapon(weapon)
                    .dBNODamage(dBNODamage)
                    .dBNOHits(dBNOHits)
                    .damage(damage)
                    .hitDetails(hitDetails)
                    .hits(hits)
                    .holdingTime(holdingTime)
                    .shots(shots)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
