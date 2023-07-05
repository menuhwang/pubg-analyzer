package com.menu.pubganalyzer.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PlayerApiV2ControllerTest {
    private static final String PLAYER_API_URL = "/api/admin/v2/players";
    private static final String PLAYER_NICKNAME = "WackyJacky101";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void search() throws Exception {
        ResultActions result = mockMvc.perform(
                get(PLAYER_API_URL + "/" + PLAYER_NICKNAME)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PlayerApiV2Controller.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.player").value(PLAYER_NICKNAME))
                .andExpect(jsonPath("$.result.matches.content.length()", lessThanOrEqualTo(20)))
                .andExpect(jsonPath("$.result.matches.content[0].match.id").isString())
                .andExpect(jsonPath("$.result.matches.content[0].match.mode").isString())
                .andExpect(jsonPath("$.result.matches.content[0].match.duration").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].match.map").isString())
                .andExpect(jsonPath("$.result.matches.content[0].match.createdAt").isString())
                .andExpect(jsonPath("$.result.matches.content[0].stat.rank").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].stat.rosters").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].stat.kills").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].stat.assists").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].stat.damageDealt").isNumber())
                .andExpect(jsonPath("$.result.matches.content[0].stat.revives").isNumber())
        ;
    }
}