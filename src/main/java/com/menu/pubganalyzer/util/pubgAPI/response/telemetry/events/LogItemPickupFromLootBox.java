package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;

import java.util.Map;

@Getter
public class LogItemPickupFromLootBox extends TelemetryResponse {
    private final CharacterResponse character;
    private final ItemResponse item;
    private final int ownerTeamId;
    private final String creatorAccountId;

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

