package com.menu.pubganalyzer.share.service;

import com.menu.pubganalyzer.share.dto.request.ShareLinkRequest;
import com.menu.pubganalyzer.share.model.ShortLink;
import com.menu.pubganalyzer.share.repository.ShortLinkRepository;
import com.menu.pubganalyzer.common.exception.LinkNotFoundException;
import com.menu.pubganalyzer.share.service.ShortLinkService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShortLinkServiceTest {
    private final ShortLinkRepository shortLinkRepository = Mockito.mock(ShortLinkRepository.class);
    private final ShortLinkService shortLinkService = new ShortLinkService(shortLinkRepository);

    @Test
    void findOriginalPath() {
        String link = "/matches/0001/player/abcde";
        ShortLink shortLink = new ShortLink(link);
        when(shortLinkRepository.findById(shortLink.getId()))
                .thenReturn(Optional.of(shortLink));

        String original = shortLinkService.findOriginalPath(shortLink.getId());

        assertEquals(link, original);
        verify(shortLinkRepository).findById(shortLink.getId());
    }

    @Test
    void findOriginalPath_NotFound() {
        String id = "wrong";
        when(shortLinkRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(LinkNotFoundException.class, () -> shortLinkService.findOriginalPath(id));
        verify(shortLinkRepository).findById(anyString());
    }

    @Test
    void create() {
        ShareLinkRequest req = new ShareLinkRequest("/matches/0001/player/abcde");
        when(shortLinkRepository.save(any(ShortLink.class)))
                .thenReturn(req.toShortLink());
        String shortLink = shortLinkService.create(req);

        assertNotNull(shortLink);
        verify(shortLinkRepository).save(any(ShortLink.class));
    }
}