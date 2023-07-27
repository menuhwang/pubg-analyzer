package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GameResultOnFinishedResponse {
    private List<GameResultResponse> results;
}
