package com.menu.pubganalyzer.matches.controller;

import com.menu.pubganalyzer.matches.dto.request.MatchFindCondition;
import com.menu.pubganalyzer.matches.dto.response.MatchInfoResponse;
import com.menu.pubganalyzer.matches.dto.response.MatchResultResponse;
import com.menu.pubganalyzer.matches.dto.response.RosterResponse;
import com.menu.pubganalyzer.matches.controller.MatchRestController;
import com.menu.pubganalyzer.matches.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.*;
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
        given(matchService.findByPlayerName(eq(PLAYER_NAME), any(Pageable.class), any(MatchFindCondition.class)))
                .willReturn(MATCH_PAGE);

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL + "/player/" + PLAYER_NAME)
        );

        verify(matchService).findByPlayerName(eq(PLAYER_NAME), any(Pageable.class), any(MatchFindCondition.class));

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.player").value(PLAYER_NAME))
                .andExpect(jsonPath("$.result.matches.content").isArray())
        ;
    }

    @Test
    void findMatchInfo() throws Exception {
        given(matchService.findMatchInfo(eq(MATCH_ID)))
                .willReturn(MatchInfoResponse.from(MATCH));

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL + "/" + MATCH_ID + "/info")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.id").isString())
                .andExpect(jsonPath("$.result.matchType").isMap())
                .andExpect(jsonPath("$.result.matchType.kor").isString())
                .andExpect(jsonPath("$.result.matchType.eng").isString())
                .andExpect(jsonPath("$.result.mapName").isMap())
                .andExpect(jsonPath("$.result.mapName.kor").isString())
                .andExpect(jsonPath("$.result.mapName.eng").isString())
                .andExpect(jsonPath("$.result.gameMode").isMap())
                .andExpect(jsonPath("$.result.gameMode.kor").isString())
                .andExpect(jsonPath("$.result.gameMode.eng").isString())
                .andExpect(jsonPath("result.createdAt").isString())
                .andExpect(jsonPath("result.duration").isNumber())
        ;
    }

    @Test
    void findMatchResultByPlayer() throws Exception {
        given(matchService.findMatchResultByPlayer(eq(MATCH_ID), eq(PLAYER_NAME)))
                .willReturn(MatchResultResponse.of(MATCH, ROSTER, PARTICIPANT));

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL + "/" + MATCH_ID + "/player/" + PLAYER_NAME + "/result")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.rank").isNumber())
                .andExpect(jsonPath("$.result.rosters").isNumber())
                .andExpect(jsonPath("$.result.kills").isNumber())
                .andExpect(jsonPath("$.result.assists").isNumber())
                .andExpect(jsonPath("$.result.damageDealt").isNumber())
                .andExpect(jsonPath("$.result.revives").isNumber())
        ;
    }

    @Test
    void findRosterWhenJustSelf() throws Exception {
        given(matchService.findRoster(eq(MATCH_ID), eq(PLAYER_NAME)))
                .willReturn(RosterResponse.from(ROSTER));

        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL + "/" + MATCH_ID + "/player/" + PLAYER_NAME + "/roster")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.won").isBoolean())
                .andExpect(jsonPath("$.result.participants").isArray())
        ;
    }
}