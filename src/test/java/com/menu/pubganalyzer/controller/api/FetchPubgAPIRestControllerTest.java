package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.fetch.controller.FetchPubgAPIRestController;
import com.menu.pubganalyzer.fetch.service.FetchAPIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.pubgapi.MatchResponseFixture.MATCH_RESPONSE;
import static com.menu.pubganalyzer.support.fixture.pubgapi.PlayerResponseFixture.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = FetchPubgAPIRestController.class)
class FetchPubgAPIRestControllerTest {
    private static final String FETCH_API_URL = "/fetch";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FetchAPIService fetchAPIService;

    @Test
    void fetchPlayer() throws Exception {
        given(fetchAPIService.player(PLAYER_SHARD, PLAYER_NAME))
                .willReturn(PLAYERS_RESPONSE);

        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/player")
                        .param("shard", PLAYER_SHARD)
                        .param("nickname", PLAYER_NAME)
        );

        verify(fetchAPIService).player(PLAYER_SHARD, PLAYER_NAME);

        result.andDo(print())
                .andExpect(handler().handlerType(FetchPubgAPIRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.data").isArray())
                .andExpect(jsonPath("$.result.data.length()").value(1))
                .andExpect(jsonPath("$.result.data[0].attributes.name").value(PLAYER_NAME))
                .andExpect(jsonPath("$.result.data[0].relationships.matches.data").isArray())
        ;
    }

    @Test
    void fetchMatch() throws Exception {
        given(fetchAPIService.match(PLAYER_SHARD, MATCH_ID))
                .willReturn(MATCH_RESPONSE);

        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/match")
                        .param("shard", PLAYER_SHARD)
                        .param("id", MATCH_ID)
        );

        verify(fetchAPIService).match(PLAYER_SHARD, MATCH_ID);

        result.andDo(print())
                .andExpect(handler().handlerType(FetchPubgAPIRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.data.id").value(MATCH_ID))
                .andExpect(jsonPath("$.result.included").isArray())
        ;
    }
}