package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HitDetailResponse {
    private String bodyPart;
    private int dBNOs;
    private int dBNOHits;
    private int dBNODamage;
    private int kills;
    private int hits;
    private int damage;

    public static HitDetailResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to HitDetailResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String bodyPart = (String) map.get("bodyPart");
            int dBNOs = (int) map.get("dBNOs");
            int dBNOHits = (int) map.get("dBNOHits");
            int dBNODamage = (int) map.get("dBNODamage");
            int kills = (int) map.get("kills");
            int hits = (int) map.get("hits");
            int damage = (int) map.get("damage");

            return HitDetailResponse.builder()
                    .bodyPart(bodyPart)
                    .dBNOs(dBNOs)
                    .dBNOHits(dBNOHits)
                    .dBNODamage(dBNODamage)
                    .kills(kills)
                    .hits(hits)
                    .damage(damage)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
