package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.domain.dto.ShareLinkReq;
import com.menu.pubganalyzer.domain.model.ShortLink;
import com.menu.pubganalyzer.domain.repository.ShortLinkRepository;
import com.menu.pubganalyzer.exception.LinkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;

    public String findOriginalPath(String id) {
        ShortLink shortLink = shortLinkRepository.findById(id)
                .orElseThrow(LinkNotFoundException::new);

        return shortLink.getLink();
    }

    public String create(ShareLinkReq req) {
        ShortLink shortLink = shortLinkRepository.save(req.toShortLink());

        return shortLink.getId();
    }
}
