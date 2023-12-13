package com.menu.pubganalyzer.util.pubgAPI.response.telemetry.events;

import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.TelemetryResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.telemetry.objects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class LogPlayerKillV2 extends TelemetryResponse {
    private int attackId;
    private int dBNOId;
    private GameResultResponse victimGameResult;
    private CharacterResponse victim;
    private String victimWeapon;
    private List<String> victimWeaponAdditionalInfo;
    private CharacterResponse dBNOMaker;
    private DamageInfoResponse dBNODamageInfo;
    private CharacterResponse finisher;
    private DamageInfoResponse finishDamageInfo;
    private CharacterResponse killer;
    private DamageInfoResponse killerDamageInfo;
    private List<String> assists_AccountId;
    private List<String> teamKillers_AccountId;
    private boolean isSuicide;

    @SuppressWarnings("unchecked")
    private LogPlayerKillV2(Map<String, Object> origin) {
        super(origin);
        this.attackId = (int) origin.get("attackId");
        this.dBNOId = (int) origin.get("dBNOId");
        this.victimGameResult = GameResultResponse.mappedBy(origin.get("victimGameResult"));
        this.victim = CharacterResponse.mappedBy(origin.get("victim"));
        this.victimWeapon = (String) origin.get("victimWeapon");
        this.victimWeaponAdditionalInfo = (List<String>) origin.get("victimWeaponAdditionalInfo");
        this.dBNOMaker = CharacterResponse.mappedBy(origin.get("dBNOMaker"));
        this.dBNODamageInfo = DamageInfoResponse.mappedBy(origin.get("dBNODamageInfo"));
        this.finisher = CharacterResponse.mappedBy(origin.get("finisher"));
        this.finishDamageInfo = DamageInfoResponse.mappedBy(origin.get("finishDamageInfo"));
        this.killer = CharacterResponse.mappedBy(origin.get("killer"));
        this.killerDamageInfo = DamageInfoResponse.mappedBy(origin.get("killerDamageInfo"));
        this.assists_AccountId = (List<String>) origin.get("assists_AccountId");
        this.teamKillers_AccountId = (List<String>) origin.get("teamKillers_AccountId");
        this.isSuicide = (boolean) origin.get("isSuicide");
    }

    public static LogPlayerKillV2 mappedBy(Map<String, Object> origin) {
        return new LogPlayerKillV2(origin);
    }
}
