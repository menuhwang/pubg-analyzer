package com.menu.pubganalyzer.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MatchApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/matches")
                        .param("page", "0")
                        .param("size", "20")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchApiController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.content[0].id").isString())
                .andExpect(jsonPath("$.result.content[0].gameMode").isString())
                .andExpect(jsonPath("$.result.content[0].duration").isNumber())
                .andExpect(jsonPath("$.result.content[0].mapName").isString())
                .andExpect(jsonPath("$.result.content[0].customMatch").isBoolean())
                .andExpect(jsonPath("$.result.content[0].matchType").isString())
                .andExpect(jsonPath("$.result.content[0].createdAt").isString())
        ;
    }

    @Transactional
    @Test
    void deleteById() throws Exception {
        String id = "069990ee-ce9a-43b4-9621-8da0339b4adf";
        ResultActions result = mockMvc.perform(
                delete("/api/matches/" + id)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(MatchApiController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.id").value(id))
        ;
    }
}