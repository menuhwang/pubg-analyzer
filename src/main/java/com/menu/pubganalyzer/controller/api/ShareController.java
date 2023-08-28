package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ShareLinkReq;
import com.menu.pubganalyzer.domain.dto.ShareLinkRes;
import com.menu.pubganalyzer.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShortLinkService shortLinkService;

    @GetMapping("{id}")
    public ShareLinkRes findOriginalPath(@PathVariable String id) {
        String original = shortLinkService.findOriginalPath(id);

        return ShareLinkRes.originalOf(original);
    }

    @PostMapping
    public ShareLinkRes createShortLink(ShareLinkReq req) {
        String shortLink = shortLinkService.create(req);

        return ShareLinkRes.shortLinkOf("/share", shortLink);
    }
}
