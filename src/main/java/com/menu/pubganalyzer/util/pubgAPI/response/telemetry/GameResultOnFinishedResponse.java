package com.menu.pubganalyzer.util.pubgAPI.response.telemetry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResultOnFinishedResponse {
    private List<GameResultResponse> results;
}
