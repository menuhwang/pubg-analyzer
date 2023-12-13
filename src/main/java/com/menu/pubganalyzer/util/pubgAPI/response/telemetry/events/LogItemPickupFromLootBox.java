package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class LogItemPickupFromLootBox extends TelemetryResponse {
    private CharacterResponse character;
    private ItemResponse item;
    private int ownerTeamId;
    private String creatorAccountId;

    private LogItemPickupFromLootBox(Map<String, Object> origin) {
        super(origin);
        this.character = CharacterResponse.mappedBy(origin.get("character"));
        this.item = ItemResponse.mappedBy(origin.get("item"));
        this.ownerTeamId = (int) origin.get("ownerTeamId");
        this.creatorAccountId = (String) origin.get("creatorAccountId");
    }

    public static LogItemPickupFromLootBox mappedBy(Map<String, Object> origin) {
        return new LogItemPickupFromLootBox(origin);
    }
}

