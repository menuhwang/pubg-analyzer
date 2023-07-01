package com.menu.pubganalyzer.controller.api;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FetchPubgApiControllerTest {
    private static final String FETCH_API_URL = "/api/admin/v2/fetch";
    @Autowired
    private MockMvc mockMvc;

    private static final String SHARD = "STEAM";
    private static final String PLAYER_NICKNAME = "WackyJacky101";
    private static String MATCH_ID;

    @Test
    @Order(1)
    void fetchPlayer() throws Exception {
        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/player")
                        .param("shard", SHARD)
                        .param("nickname", PLAYER_NICKNAME)
        );

        result.andDo(print())
                .andExpect(handler().handlerType(FetchPubgApiController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.data").isArray())
                .andExpect(jsonPath("$.result.data.length()").value(1))
                .andExpect(jsonPath("$.result.data[0].attributes.name").value(PLAYER_NICKNAME))
                .andExpect(jsonPath("$.result.data[0].relationships.matches.data").isArray())
        ;

        MATCH_ID = JsonPath.read(result.andReturn().getResponse().getContentAsString(), "$.result.data[0].relationships.matches.data[0].id");
    }

    @Test
    @Order(2)
    void fetchMatch() throws Exception {
        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/match")
                        .param("shard", SHARD)
                        .param("id", MATCH_ID)
        );

        result.andDo(print())
                .andExpect(handler().handlerType(FetchPubgApiController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.data.id").value(MATCH_ID))
                .andExpect(jsonPath("$.result.included").isArray())
        ;
    }
}