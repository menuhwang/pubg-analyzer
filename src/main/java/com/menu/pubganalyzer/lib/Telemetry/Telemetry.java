package com.menu.pubganalyzer.lib.Telemetry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Telemetry telemetry = new Telemetry(telemetry_raw.json); // 텔레메트리 인스턴스 생성
// 킬로그 가져오기
// telemetry.getLogPlayerKillV2(); // 킬로그 전체 가져오기
// telemetry.getLogPlyaerKillV2(playerName); // [playerName]의 킬로그 전체 가져오기
// return : JSONArray [{logPlayerKillV2, ...}]
@Setter
@Getter
@NoArgsConstructor
public class Telemetry {
    private LogPlayerKillList logPlayerKillList;
    private LogPlayerTakeDamageList logPlayerTakeDamageList;
}