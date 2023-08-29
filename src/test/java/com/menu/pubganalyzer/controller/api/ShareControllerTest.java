package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ShareLinkReq;
import com.menu.pubganalyzer.domain.model.ShortLink;
import com.menu.pubganalyzer.exception.LinkNotFoundException;
import com.menu.pubganalyzer.service.ShortLinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ShareController.class)
class ShareControllerTest {
    private static final String SHARE_API_URL = "/share";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortLinkService shortLinkService;

    @Test
    void createShortLink() throws Exception {
        String link = "/matches/0001/player/abcde";
        ShareLinkReq req = new ShareLinkReq(link);
        ShortLink shortLink = req.toShortLink();

        when(shortLinkService.create(any(ShareLinkReq.class)))
                .thenReturn(shortLink.getId());

        ResultActions result = mockMvc.perform(
                post(SHARE_API_URL)
                        .contentType("application/json")
                        .content("{\"link\":  \"/matches/0001/player/abcde\"}")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(ShareController.class))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.path").value(SHARE_API_URL + "/" + shortLink.getId()))
        ;

        verify(shortLinkService).create(any(ShareLinkReq.class));
    }

    @Test
    void findOriginalPath() throws Exception {
        String link = "/matches/0001/player/abcde";
        ShortLink shortLink = new ShortLink(link);

        when(shortLinkService.findOriginalPath(shortLink.getId()))
                .thenReturn(link);

        ResultActions result = mockMvc.perform(
                get(SHARE_API_URL + "/" + shortLink.getId())
        );

        result.andDo(print())
                .andExpect(handler().handlerType(ShareController.class))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.path").value(link))
        ;

        verify(shortLinkService).findOriginalPath(shortLink.getId());
    }

    @Test
    void findOriginalPath_not_found() throws Exception {
        when(shortLinkService.findOriginalPath(anyString()))
                .thenThrow(new LinkNotFoundException());

        ResultActions result = mockMvc.perform(
                get(SHARE_API_URL + "/wrongId")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(ShareController.class))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
        ;
    }
}