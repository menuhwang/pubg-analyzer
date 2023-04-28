package com.menu.pubganalyzer.util.pubgAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class TelemetryResponse {
    @JsonProperty(value = "_D")
    LocalDateTime timestamp;
    @JsonProperty(value = "_T")
    String type;
    Common common;
    Integer attackId;
    Characters attacker;
    Characters victim;
    String damageTypeCategory;
    String damageReason;
    String damageCauserName;
    Item item;
    Float distance;
    List<Characters> survivors;
    ItemPackage itemPackage;
    Characters character;
    String carryState;
    Characters instigator;
    List<Characters> riders;
    GameState gameState;
    Float healAmount;
    Item parentItem;
    Item childItem;
    Float carePackageUniqueId;
    Integer ownerTeamId;
    String creatorAccountId;
    Vehicle vehicle;
    String matchId;
    String seasonState;
    List<CharacterWrapper> characters;
    GameResultOnFinished gameResultOnFinished;
    String mapName;
    String weatherId;
    String cameraViewBehaviour;
    Integer teamSize;
    Boolean isCustomGame;
    Boolean isEventMode;
    String blueZoneCustomOptions;
    String objectType;
    Location objectLocation;
    String objectTypeStatus;
    List<String> objectTypeAdditionalInfo;
    Integer phase;
    Float elapsedTime;
    Integer fireWeaponStackCount;
    String attackType;
    Item weapon;
    Characters killer;
    Characters assistant;
    @JsonProperty(value = "dBNOId")
    Integer dBNOId;
    List<String> damageCauserAdditionalInfo;
    String victimWeapon;
    List<String> victimWeaponAdditionalInfo;
    GameResult victimGameResult;
    Boolean isThroughPenetrableWall;
    @JsonProperty(value = "dBNOMaker")
    Characters dBNOMaker;
    @JsonProperty(value = "dBNODamageInfo")
    DamageInfo dBNODamageInfo;
    Characters finisher;
    DamageInfo finishDamageInfo;
    DamageInfo killerDamageInfo;
    List<String> assists_AccountId;
    List<String> teamKillers_AccountId;
    Boolean isSuicide;
    String accountId;
    Boolean isAttackerInVehicle;
    Integer numAlivePlayers;
    Characters reviver;
    Float damage;
    List<Characters> drivers;
    Float swimDistance;
    Float maxSwimDepthOfWater;
    Boolean isLedgeGrab;
    Float rideDistance;
    Integer seatIndex;
    Float maxSpeed;
    List<Characters> fellowPassengers;
    String weaponId;
    Integer fireCount;

    @Getter
    @ToString
    public static class Characters {
        String name;
        Integer teamId;
        Float health;
        Location location;
        Integer ranking;
        String accountId;
        Boolean isInBlueZone;
        Boolean isInRedZone;
        List<String> zone;
    }

    @Getter
    @ToString
    public static class CharacterWrapper {
        Characters character;
        String primaryWeaponFirst;
        String primaryWeaponSecond;
        String secondaryWeapon;
        Integer spawnKitIndex;
    }

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
    @ToString
    public static class Common {
        Float isGame;
    }

    @Getter
    @ToString
    public static class DamageInfo {
        String damageReason;
        String damageTypeCategory;
        String damageCauserName;
        List<String> additionalInfo;
        Float distance;
        Boolean isThroughPenetrableWall;
    }

    @Getter
    @ToString
    public static class GameResult {
        Integer rank;
        String gameResult;
        Integer teamId;
        Stats stats;
        String accountId;
    }

    @Getter
    @ToString
    public static class GameResultOnFinished {
        List<GameResult> results;
    }

    @Getter
    @ToString
    public static class GameState {
        Integer elapsedTime;
        Integer numAliveTeams;
        Integer numJoinPlayers;
        Integer numStartPlayers;
        Integer numAlivePlayers;
        Location safetyZonePosition;
        Float safetyZoneRadius;
        Location poisonGasWarningPosition;
        Float poisonGasWarningRadius;
        Location redZonePosition;
        Float redZoneRadius;
        Location blackZonePosition;
        Float blackZoneRadius;
    }

    @Getter
    @ToString
    public static class Item {
        String itemId;
        Integer stackCount;
        String category;
        String subCategory;
        List<String> attachedItems;
    }

    @Getter
    @ToString
    public static class ItemPackage {
        String itemPackageId;
        Location location;
        List<Item> items;
    }

    /**
     * Location values are measured in centimeters.
     * (0,0) is at the top-left of each map.
     * The range for the X and Y axes is 0 - 816,000 for Erangel, Miramar, Taego and Deston.
     * The range for the X and Y axes is 0 - 612,000 for Vikendi.
     * The range for the X and Y axes is 0 - 408,000 for Sanhok.
     * The range for the X and Y axes is 0 - 306,000 for Paramo.
     * The range for the X and Y axes is 0 - 204,000 for Karakin and Range.
     * The range for the X and Y axes is 0 - 102,000 for Haven.
     **/
    @Getter
    @ToString
    public static class Location {
        Float x;
        Float y;
        Float z;
    }

    @Getter
    @ToString
    public static class Stats {
        Integer killCount;
        Float distanceOnFoot;
        Float distanceOnSwim;
        Float distanceOnVehicle;
        Float distanceOnParachute;
        Float distanceOnFreefall;
    }

    @Getter
    @ToString
    public static class Vehicle {
        String vehicleType;
        String vehicleId;
        Integer vehicleUniqueId;
        Float healthPercent;
        Float feulPercent;
        Float altitudeAbs;
        Float altitudeRel;
        Float velocity;
        Integer seatIndex;
        Boolean isWheelsInAir;
        Boolean isInWaterVolume;
        Boolean isEngineOn;
    }
}
