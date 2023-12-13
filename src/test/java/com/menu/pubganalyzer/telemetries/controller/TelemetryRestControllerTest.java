package com.menu.pubganalyzer.telemetries.controller;

import com.menu.pubganalyzer.telemetries.dto.response.DamageLogResponse;
import com.menu.pubganalyzer.telemetries.dto.response.KillLogResponse;
import com.menu.pubganalyzer.telemetries.model.TelemetryEntity;
import com.menu.pubganalyzer.telemetries.service.TelemetryService;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerKillV2;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS;
import static com.menu.pubganalyzer.support.fixture.TelemetryFixture.OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TelemetryRestController.class)
class TelemetryRestControllerTest {
    private static final String TELEMETRY_API_URL = "/telemetries";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TelemetryService telemetryService;

    @Test
    void findDamagesOfKill() throws Exception {
        given(telemetryService.findDamagesOfKill(MATCH_ID, PLAYER_NAME))
                .willReturn(
                        OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES.stream()
                                .map(TelemetryEntity::getTelemetry)
                                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                                .map(DamageLogResponse::of)
                                .collect(Collectors.toList())
                );

        ResultActions result = mockMvc.perform(
                get(TELEMETRY_API_URL + "/" + MATCH_ID + "/player/" + PLAYER_NAME + "/kills/damages")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TelemetryRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result").isArray())
        ;
    }

    @Test
    void findDamageLogByPlayer() throws Exception {
        given(telemetryService.findDamageLogByPlayer(MATCH_ID, PLAYER_NAME))
                .willReturn(
                        OFFICIAL_TELEMETRIES_LOG_PLAYER_TAKE_DAMAGES.stream()
                                .map(TelemetryEntity::getTelemetry)
                                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                                .map(DamageLogResponse::of)
                                .collect(Collectors.toList())
                );

        ResultActions result = mockMvc.perform(
                get(TELEMETRY_API_URL + "/" + MATCH_ID + "/player/" + PLAYER_NAME + "/damages")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TelemetryRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result").isArray())
        ;
    }

    @Test
    void findKillLogs() throws Exception {
        given(telemetryService.findKillLogs(MATCH_ID, PLAYER_NAME))
                .willReturn(
                        OFFICIAL_TELEMETRIES_LOG_PLAYER_KILLS.stream()
                                .map(TelemetryEntity::getTelemetry)
                                .map(telemetryResponse -> (LogPlayerKillV2) telemetryResponse)
                                .map(KillLogResponse::of)
                                .collect(Collectors.toList())
                );

        ResultActions result = mockMvc.perform(
                get(TELEMETRY_API_URL + "/" + MATCH_ID + "/player/" + PLAYER_NAME + "/kills")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TelemetryRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result").isArray())
        ;
    }
}