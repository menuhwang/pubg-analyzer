package com.menu.pubganalyzer.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MatchRestControllerTest {
    private static final String MATCH_API_URL = "/matches";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL)
                        .param("page", "0")
                        .param("size", "5")
        );

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
    void findAllDefaultPageable() throws Exception {
        ResultActions result = mockMvc.perform(
                get(MATCH_API_URL)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.content.length()", lessThanOrEqualTo(20)))
                .andExpect(jsonPath("$.result.content[0].id").isString())
                .andExpect(jsonPath("$.result.content[0].gameMode").isMap())
                .andExpect(jsonPath("$.result.content[0].duration").isNumber())
                .andExpect(jsonPath("$.result.content[0].mapName").isMap())
                .andExpect(jsonPath("$.result.content[0].customMatch").isBoolean())
                .andExpect(jsonPath("$.result.content[0].matchType").isMap())
                .andExpect(jsonPath("$.result.content[0].createdAt").isString())
        ;
    }

    @Transactional
    @Test
    void deleteById() throws Exception {
        String id = "069990ee-ce9a-43b4-9621-8da0339b4adf";
        ResultActions result = mockMvc.perform(
                delete(MATCH_API_URL + "/" + id)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.id").value(id))
        ;
    }
}