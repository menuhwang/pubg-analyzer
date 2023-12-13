package com.menu.pubganalyzer.util.pubg.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsResponse {
    private int killCount;
    private float distanceOnFoot;
    private float distanceOnSwim;
    private float distanceOnVehicle;
    private float distanceOnParachute;
    private float distanceOnFreefall;

    public static StatsResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to StatsResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            int killCount = (int) map.get("killCount");
            float distanceOnFoot = ((Number) map.get("distanceOnFoot")).floatValue();
            float distanceOnSwim = ((Number) map.get("distanceOnSwim")).floatValue();
            float distanceOnVehicle = ((Number) map.get("distanceOnVehicle")).floatValue();
            float distanceOnParachute = ((Number) map.get("distanceOnParachute")).floatValue();
            float distanceOnFreefall = ((Number) map.get("distanceOnFreefall")).floatValue();

            return StatsResponse.builder()
                    .killCount(killCount)
                    .distanceOnFoot(distanceOnFoot)
                    .distanceOnSwim(distanceOnSwim)
                    .distanceOnVehicle(distanceOnVehicle)
                    .distanceOnParachute(distanceOnParachute)
                    .distanceOnFreefall(distanceOnFreefall)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
