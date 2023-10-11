package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.menu.pubganalyzer.util.LocalDateTimeParser;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events.*;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.CommonResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class TelemetryResponse {
    private final LocalDateTime timestamp;
    private final String type;
    private final CommonResponse common;

    protected TelemetryResponse(Map<String, Object> origin) {
        this.timestamp = LocalDateTimeParser.parse((String) origin.get("_D"));
        this.type = (String) origin.get("_T");
        this.common = CommonResponse.mappedBy(origin.get("common"));
    }

    public static TelemetryResponse from(Object o) {
        if (!(o instanceof Map))
            throw new IllegalArgumentException("Failed to convert. Object is not map type.");

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) o;

        if (!validated(map))
            throw new IllegalArgumentException("Object is not telemetry event. \n" + map);

        String type = (String) map.get("_T");
        TelemetryResponse result;
        try {
            switch (type) {
                case "LogArmorDestroy":
                    result = LogArmorDestroy.mappedBy(map);
                    break;

                case "LogBlackZoneEnded":
                    result = LogBlackZoneEnded.mappedBy(map);
                    break;

                case "LogCarePackageLand":
                    result = LogCarePackageLand.mappedBy(map);
                    break;

                case "LogCarePackageSpawn":
                    result = LogCarePackageSpawn.mappedBy(map);
                    break;

                case "LogCharacterCarry":
                    result = LogCharacterCarry.mappedBy(map);
                    break;

                case "LogEmPickupLiftOff":
                    result = LogEmPickupLiftOff.mappedBy(map);
                    break;

                case "LogGameStatePeriodic":
                    result = LogGameStatePeriodic.mappedBy(map);
                    break;

                case "LogHeal":
                    result = LogHeal.mappedBy(map);
                    break;

                case "LogItemAttach":
                    result = LogItemAttach.mappedBy(map);
                    break;

                case "LogItemDetach":
                    result = LogItemDetach.mappedBy(map);
                    break;

                case "LogItemDrop":
                    result = LogItemDrop.mappedBy(map);
                    break;

            case "LogItemEquip":
                result = LogItemEquip.mappedBy(map);
                break;

            case "LogItemPickup":
                result = LogItemPickup.mappedBy(map);
                break;

            case "LogItemPickupFromCarepackage":
                result = LogItemPickupFromCarepackage.mappedBy(map);
                break;

            case "LogItemPickupFromCustomPackage":
                result = LogItemPickupFromCustomPackage.mappedBy(map);
                break;

            case "LogItemPickupFromLootBox":
                result = LogItemPickupFromLootBox.mappedBy(map);
                break;

            case "LogItemPickupFromVehicleTrunk":
                result = LogItemPickupFromVehicleTrunk.mappedBy(map);
                break;

            case "LogItemPutToVehicleTrunk":
                result = LogItemPutToVehicleTrunk.mappedBy(map);
                break;

            case "LogItemUnequip":
                result = LogItemUnequip.mappedBy(map);
                break;

            case "LogItemUse":
                result = LogItemUse.mappedBy(map);
                break;

            case "LogMatchDefinition":
                result = LogMatchDefinition.mappedBy(map);
                break;

            case "LogMatchEnd":
                result = LogMatchEnd.mappedBy(map);
                break;

            case "LogMatchStart":
                result = LogMatchStart.mappedBy(map);
                break;

            case "LogObjectDestroy":
                result = LogObjectDestroy.mappedBy(map);
                break;

            case "LogObjectInteraction":
                result = LogObjectInteraction.mappedBy(map);
                break;

            case "LogParachuteLanding":
                result = LogParachuteLanding.mappedBy(map);
                break;

            case "LogPhaseChange":
                result = LogPhaseChange.mappedBy(map);
                break;

            case "LogPlayerAttack":
                result = LogPlayerAttack.mappedBy(map);
                break;

            case "LogPlayerCreate":
                result = LogPlayerCreate.mappedBy(map);
                break;

            case "LogPlayerDestroyBreachableWall":
                result = LogPlayerDestroyBreachableWall.mappedBy(map);
                break;

            case "LogPlayerDestroyProp":
                result = LogPlayerDestroyProp.mappedBy(map);
                break;

            case "LogPlayerKill (tournament matches)":
                result = LogPlayerKill.mappedBy(map);
                break;

            case "LogPlayerKillV2":
                result = LogPlayerKillV2.mappedBy(map);
                break;

            case "LogPlayerLogin":
                result = LogPlayerLogin.mappedBy(map);
                break;

            case "LogPlayerLogout":
                result = LogPlayerLogout.mappedBy(map);
                break;

            case "LogPlayerMakeGroggy":
                result = LogPlayerMakeGroggy.mappedBy(map);
                break;

            case "LogPlayerPosition":
                result = LogPlayerPosition.mappedBy(map);
                break;

            case "LogPlayerRedeploy":
                result = LogPlayerRedeploy.mappedBy(map);
                break;

            case "LogPlayerRedeployBRStart":
                result = LogPlayerRedeployBRStart.mappedBy(map);
                break;

            case "LogPlayerRevive":
                result = LogPlayerRevive.mappedBy(map);
                break;

            case "LogPlayerTakeDamage":
                result = LogPlayerTakeDamage.mappedBy(map);
                break;

            case "LogPlayerUseFlareGun":
                result = LogPlayerUseFlareGun.mappedBy(map);
                break;

            case "LogPlayerUseThrowable":
                result = LogPlayerUseThrowable.mappedBy(map);
                break;

            case "LogRedZoneEnded":
                result = LogRedZoneEnded.mappedBy(map);
                break;

            case "LogSwimEnd":
                result = LogSwimEnd.mappedBy(map);
                break;

            case "LogSwimStart":
                result = LogSwimStart.mappedBy(map);
                break;

            case "LogVaultStart":
                result = LogVaultStart.mappedBy(map);
                break;

            case "LogVehicleDamage":
                result = LogVehicleDamage.mappedBy(map);
                break;

            case "LogVehicleDestroy":
                result = LogVehicleDestroy.mappedBy(map);
                break;

            case "LogVehicleLeave":
                result = LogVehicleLeave.mappedBy(map);
                break;

            case "LogVehicleRide":
                result = LogVehicleRide.mappedBy(map);
                break;

            case "LogWeaponFireCount":
                result = LogWeaponFireCount.mappedBy(map);
                break;

            case "LogWheelDestroy":
                result = LogWheelDestroy.mappedBy(map);
                break;

                default:
                    throw new IllegalArgumentException("Not Found Telemetry Event. [" + type + "]");
            }
        } catch (ClassCastException | NullPointerException e) {
            System.err.println(map);
            throw e;
        }

        return result;
    }

    private static boolean validated(Map<String, Object> map) {
        return map.containsKey("_T")
                && map.containsKey("_D");
    }
}
