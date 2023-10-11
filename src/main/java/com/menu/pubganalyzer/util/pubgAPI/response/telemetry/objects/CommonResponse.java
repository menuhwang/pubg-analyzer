package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;
import java.util.Objects;

/**
* isGame = 0 -> Before lift off
* isGame = 0.1 -> On airplane
* isGame = 0.5 -> When there’s no ‘zone’ on map(before game starts)
* isGame = 1.0 -> First safezone and bluezone appear
* isGame = 1.5 -> First bluezone shrinks
* isGame = 2.0 -> Second bluezone appears
* isGame = 2.5 -> Second bluezone shrinks
**/

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse {
    private double isGame;

    public static CommonResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed map to common response. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            double isGame = ((Number) map.get("isGame")).doubleValue();

            return new CommonResponse(isGame);
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
