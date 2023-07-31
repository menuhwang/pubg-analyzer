package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleResponse {
    private String vehicleType;
    private String vehicleId;
    private int vehicleUniqueId;
    private float healthPercent;
    @JsonProperty(value = "feulPercent")
    private float fuelPercent;
    private float altitudeAbs;
    private float altitudeRel;
    private float velocity;
    private int seatIndex;
    private Boolean isWheelsInAir;
    private Boolean isInWaterVolume;
    private Boolean isEngineOn;
}
