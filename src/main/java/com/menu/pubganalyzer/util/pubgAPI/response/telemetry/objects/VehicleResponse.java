package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects;

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
public class VehicleResponse {
    private String vehicleType;
    private String vehicleId;
    private float healthPercent;
    private float fuelPercent;
    private float altitudeAbs;
    private float altitudeRel;
    private float velocity;
    private int seatIndex;
    private Boolean isWheelsInAir;
    private Boolean isInWaterVolume;
    private Boolean isEngineOn;

    public static VehicleResponse mappedBy(Object o) {
        if (Objects.isNull(o)) {
            return null;
        }

        if (!(o instanceof Map)) {
            throw new IllegalArgumentException("Failed to map to VehicleResponse. Object is not of Map type.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        try {
            String vehicleType = (String) map.get("vehicleType");
            String vehicleId = (String) map.get("vehicleId");
            float healthPercent = ((Number) map.get("healthPercent")).floatValue();
            float fuelPercent = ((Number) map.get("feulPercent")).floatValue(); // pubg 공식 api 오타
            float altitudeAbs = ((Number) map.get("altitudeAbs")).floatValue();
            float altitudeRel = ((Number) map.get("altitudeRel")).floatValue();
            float velocity = ((Number) map.get("velocity")).floatValue();
            int seatIndex = (int) map.get("seatIndex");
            Boolean isWheelsInAir = (Boolean) map.get("isWheelsInAir");
            Boolean isInWaterVolume = (Boolean) map.get("isInWaterVolume");
            Boolean isEngineOn = (Boolean) map.get("isEngineOn");

            return VehicleResponse.builder()
                    .vehicleType(vehicleType)
                    .vehicleId(vehicleId)
                    .healthPercent(healthPercent)
                    .fuelPercent(fuelPercent)
                    .altitudeAbs(altitudeAbs)
                    .altitudeRel(altitudeRel)
                    .velocity(velocity)
                    .seatIndex(seatIndex)
                    .isWheelsInAir(isWheelsInAir)
                    .isInWaterVolume(isInWaterVolume)
                    .isEngineOn(isEngineOn)
                    .build();
        } catch (ClassCastException e) {
            System.err.println(map);
            throw e;
        }
    }

}
