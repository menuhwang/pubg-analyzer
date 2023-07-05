package com.menu.pubganalyzer.controller.api;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ReportApiControllerTest {
    private static final String REPORT_API_URL = "/api/admin/v2/report";
    private static final String MATCH_ID = "e8955382-1ff1-44ea-a836-3c1b1e0412cf";
    private static final String PLAYER_NICKNAME = "WackyJacky101";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMatchReport() throws Exception {
        ResultActions result = mockMvc.perform(
                get(REPORT_API_URL + "/match/" + MATCH_ID + "/player/" + PLAYER_NICKNAME)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ReportApiController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.matchInfo.id").isString())
                .andExpect(jsonPath("$.result.matchInfo.mode").isString())
                .andExpect(jsonPath("$.result.matchInfo.duration").isNumber())
                .andExpect(jsonPath("$.result.matchInfo.map").isString())
                .andExpect(jsonPath("$.result.matchInfo.createdAt").isString())
                .andExpect(jsonPath("$.result.matchResult.rank").isNumber())
                .andExpect(jsonPath("$.result.matchResult.rosters").isNumber())
                .andExpect(jsonPath("$.result.matchResult.kills").isNumber())
                .andExpect(jsonPath("$.result.matchResult.assists").isNumber())
                .andExpect(jsonPath("$.result.matchResult.damageDealt").isNumber())
                .andExpect(jsonPath("$.result.matchResult.revives").isNumber())
                .andExpect(jsonPath("$.result.data.killLog").isArray())
                .andExpect(jsonPath("$.result.data.damageLog").isArray())
                .andExpect(jsonPath("$.result.data.player").isNumber())
                .andExpect(jsonPath("$.result.data.bot").isNumber())
                .andExpect(jsonPath("$.result.data.victimPlayerDamageDealt").isMap())
                .andExpect(jsonPath("$.result.data.victimDamageLog").isMap())
                .andExpect(jsonPath("$.result.data.charts").isMap())
        ;
    }
}