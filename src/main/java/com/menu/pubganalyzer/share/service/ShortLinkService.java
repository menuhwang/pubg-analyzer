package com.menu.pubganalyzer.share.service;

import com.menu.pubganalyzer.common.exception.LinkNotFoundException;
import com.menu.pubganalyzer.share.dto.request.ShareLinkRequest;
import com.menu.pubganalyzer.share.model.ShortLink;
import com.menu.pubganalyzer.share.repository.ShortLinkRepository;
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

    public String create(ShareLinkRequest req) {
        ShortLink shortLink = shortLinkRepository.save(req.toShortLink());

        return shortLink.getId();
    }
}
