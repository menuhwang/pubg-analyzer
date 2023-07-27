package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VehicleResponse {
    private String vehicleType;
    private String vehicleId;
    private int vehicleUniqueId;
    private float healthPercent;
    private float fuelPercent;
    private float altitudeAbs;
    private float altitudeRel;
    private float velocity;
    private int seatIndex;
    private boolean isWheelsInAir;
    private boolean isInWaterVolume;
    private boolean isEngineOn;
}
