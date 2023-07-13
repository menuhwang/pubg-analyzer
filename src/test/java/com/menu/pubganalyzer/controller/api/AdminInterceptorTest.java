package com.menu.pubganalyzer.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(properties = "application.admin.allow=admin.only.com")
public class AdminInterceptorTest {
    private static final String FETCH_API_URL = "/fetch";
    private static final String MATCH_API_URL = "/matches";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fetchPlayerNotAllowHost() throws Exception {
        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/player")
                        .param("shard", "ANY_SHARD")
                        .param("nickname", "ANY_NICKNAME")
        );

        result.andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void fetchMatchNotAllowHost() throws Exception {
        ResultActions result = mockMvc.perform(
                get(FETCH_API_URL + "/match")
                        .param("shard", "ANY_SHARD")
                        .param("id", "ANY_MATCH_ID")
        );

        result.andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void matchFindAllNotAllowHost() throws Exception {
        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL)
                        .param("page", "0")
                        .param("size", "5")
        );

        result.andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void matchDeleteNotAllowHost() throws Exception {
        ResultActions result = mockMvc.perform(
                delete(MATCH_API_URL + "/anyMatchId")
        );
        result.andDo(print())
                .andExpect(status().isForbidden());
    }
}
