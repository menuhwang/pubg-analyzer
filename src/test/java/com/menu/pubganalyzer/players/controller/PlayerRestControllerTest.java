package com.menu.pubganalyzer.players.controller;

import com.menu.pubganalyzer.players.controller.PlayerRestController;
import com.menu.pubganalyzer.players.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PlayerRestController.class)
class PlayerRestControllerTest {
    private static final String PLAYER_API_URL = "/players";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;

    @Test
    void updateMatchHistory() throws Exception {
        ResultActions result = mockMvc.perform(
                patch(PLAYER_API_URL + "/" + PLAYER_NAME)
        );

        verify(playerService).updateMatchHistory(PLAYER_NAME);

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PlayerRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
        ;
    }
}