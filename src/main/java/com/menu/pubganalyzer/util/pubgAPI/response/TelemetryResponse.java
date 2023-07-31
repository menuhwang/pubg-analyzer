package com.menu.pubganalyzer.util.pubgAPI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.*;
import lombok.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"PingQuality"}, ignoreUnknown = true)
public class TelemetryResponse {
    @JsonProperty(value = "MatchId")
    String _id;
    @CommonField
    @JsonProperty(value = "_D")
    LocalDateTime timestamp;
    @CommonField
    @JsonProperty(value = "_T")
    String type;
    @CommonField
    CommonResponse common;
    Integer attackId;
    CharacterResponse attacker;
    CharacterResponse victim;
    String damageTypeCategory;
    String damageReason;
    String damageCauserName;
    ItemResponse item;
    Float distance;
    List<CharacterResponse> survivors;
    ItemPackageResponse itemPackage;
    CharacterResponse character;
    String carryState;
    CharacterResponse instigator;
    List<CharacterResponse> riders;
    GameStateResponse gameState;
    Float healAmount;
    ItemResponse parentItem;
    ItemResponse childItem;
    Float carePackageUniqueId;
    Integer ownerTeamId;
    String creatorAccountId;
    VehicleResponse vehicle;
    String matchId;
    String seasonState;
    List<CharacterWrapperResponse> characters;
    GameResultOnFinishedResponse gameResultOnFinished;
    String mapName;
    String weatherId;
    String cameraViewBehaviour;
    Integer teamSize;
    Boolean isCustomGame;
    Boolean isEventMode;
    String blueZoneCustomOptions;
    String objectType;
    LocationResponse objectLocation;
    String objectTypeStatus;
    List<Object> objectTypeAdditionalInfo;
    Integer phase;
    Float elapsedTime;
    Integer fireWeaponStackCount;
    String attackType;
    ItemResponse weapon;
    CharacterResponse killer;
    CharacterResponse assistant;
    @JsonProperty(value = "dBNOId")
    Integer dBNOId;
    List<String> damageCauserAdditionalInfo;
    String victimWeapon;
    List<String> victimWeaponAdditionalInfo;
    GameResultResponse victimGameResult;
    Boolean isThroughPenetrableWall;
    @JsonProperty(value = "dBNOMaker")
    CharacterResponse dBNOMaker;
    @JsonProperty(value = "dBNODamageInfo")
    DamageInfoResponse dBNODamageInfo;
    CharacterResponse finisher;
    DamageInfoResponse finishDamageInfo;
    DamageInfoResponse killerDamageInfo;
    List<String> assists_AccountId;
    List<String> teamKillers_AccountId;
    Boolean isSuicide;
    String accountId;
    Boolean isAttackerInVehicle;
    Integer numAlivePlayers;
    CharacterResponse reviver;
    Double damage;
    List<CharacterResponse> drivers;
    Float swimDistance;
    Float maxSwimDepthOfWater;
    Boolean isLedgeGrab;
    Float rideDistance;
    Integer seatIndex;
    Float maxSpeed;
    List<CharacterResponse> fellowPassengers;
    String weaponId;
    Integer fireCount;
    List<String> playersInWhiteCircle;
    String carePackageName;
    Integer wheelIndex;
    List<WeaponStatsWrapperResponse> allWeaponStats;

    public String getDamageTypeCategory() {
        return damageTypeCategory == null ? null : damageTypeCategory.toUpperCase();
    }

    public String getDamageReason() {
        return damageReason == null ? null : damageReason.toUpperCase();
    }

    public String getDamageCauserName() {
        return damageCauserName == null ? null : damageCauserName.replaceAll("-", "_").toUpperCase();
    }

    public Map<String, Object> getAttribute() {
        Map<String, Object> attribute = new HashMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isCommonField(field)) continue;

            Object value = getValue(field);
            if (Objects.isNull(value)) continue;

            attribute.put(field.getName(), getValue(field));
        }

        return attribute;
    }

    private boolean isCommonField(Field field) {
        CommonField commonField = field.getAnnotation(CommonField.class);
        return Objects.nonNull(commonField);
    }

    private Object getValue(Field field) {
        try {
            return field.get(this);
        } catch (IllegalAccessException ignore) {
            // self access
        }
        return null;
    }
}
