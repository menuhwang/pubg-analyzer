package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_PAGE;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MatchRestController.class)
class MatchRestControllerTest {
    private static final String MATCH_API_URL = "/matches";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;

    @Test
    void findAll() throws Exception {
        given(matchService.findAll(any()))
                .willReturn(MATCH_PAGE);

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL)
                        .param("page", "0")
                        .param("size", "5")
        );

        verify(matchService).findAll(any());

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.content.length()", lessThanOrEqualTo(5)))
                .andExpect(jsonPath("$.result.content[0].id").isString())
                .andExpect(jsonPath("$.result.content[0].gameMode").isMap())
                .andExpect(jsonPath("$.result.content[0].duration").isNumber())
                .andExpect(jsonPath("$.result.content[0].mapName").isMap())
                .andExpect(jsonPath("$.result.content[0].customMatch").isBoolean())
                .andExpect(jsonPath("$.result.content[0].matchType").isMap())
                .andExpect(jsonPath("$.result.content[0].createdAt").isString())
        ;
    }

    @Test
    void deleteById() throws Exception {
        ResultActions result = mockMvc.perform(
                delete(MATCH_API_URL + "/" + MATCH_ID)
        );

        verify(matchService).deleteById(anyCollection());

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.id").value(MATCH_ID))
        ;
    }

    @Test
    void findByPlayerName() throws Exception {
        given(matchService.findByPlayerName(eq(PLAYER_NAME), any(Pageable.class)))
                .willReturn(MATCH_PAGE);

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL + "/player/" + PLAYER_NAME)
        );

        verify(matchService).findByPlayerName(eq(PLAYER_NAME), any(Pageable.class));

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.player").value(PLAYER_NAME))
                .andExpect(jsonPath("$.result.matches.content").isArray())
        ;
    }
}