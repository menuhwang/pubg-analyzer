package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.DamageLogRes;
import com.menu.pubganalyzer.service.TelemetryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Collectors;

import static com.menu.pubganalyzer.support.fixture.LogPlayerTakeDamageFixture.OFFICIAL_LOG_PLAYER_TAKE_DAMAGES;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
                        OFFICIAL_LOG_PLAYER_TAKE_DAMAGES.stream()
                                .map(DamageLogRes::of)
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
}