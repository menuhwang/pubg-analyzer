package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects;

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
public class LocationResponse {
    private double x;
    private double y;
    private double z;

    public static LocationResponse mappedBy(Object o) {
        if (Objects.isNull(o))
            return null;

        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed map to location response. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Number> map = (Map<String, Number>) o;

        try {
            double x = map.get("x").doubleValue();
            double y = map.get("y").doubleValue();
            double z = map.get("z").doubleValue();


            return new LocationResponse(x, y, z);
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }
}
