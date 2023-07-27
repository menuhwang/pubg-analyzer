package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH;
import static com.menu.pubganalyzer.support.fixture.MatchFixture.MATCH_ID;
import static com.menu.pubganalyzer.support.fixture.PlayerFixture.PLAYER_NAME;
import static com.menu.pubganalyzer.support.fixture.ReportFixture.REPORT;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReportRestController.class)
class ReportRestControllerTest {
    private static final String REPORT_API_URL = "/report";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;
    @MockBean
    private ReportService reportService;

    @Test
    void getMatchReport() throws Exception {
        given(matchService.findById(eq(MATCH_ID)))
                .willReturn(MATCH);

        given(reportService.getMatchReport(eq(MATCH), eq(PLAYER_NAME)))
                .willReturn(REPORT);

        ResultActions result = mockMvc.perform(
                get(REPORT_API_URL + "/match/" + MATCH_ID + "/player/" + PLAYER_NAME)
        );

        verify(matchService).findById(eq(MATCH_ID));
        verify(reportService).getMatchReport(eq(MATCH), eq(PLAYER_NAME));

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ReportRestController.class))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.result.matchInfo").exists())
                .andExpect(jsonPath("$.result.matchResult").exists())
                .andExpect(jsonPath("$.result.data").exists())
                .andExpect(jsonPath("$.result.data.charts").exists())
        ;
    }
}