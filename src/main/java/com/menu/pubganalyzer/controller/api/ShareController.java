package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ShareLinkReq;
import com.menu.pubganalyzer.domain.dto.ShareLinkRes;
import com.menu.pubganalyzer.service.ShortLinkService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShortLinkService shortLinkService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResult<ShareLinkRes>> findOriginalPath(@PathVariable String id) {
        String original = shortLinkService.findOriginalPath(id);
        ShareLinkRes result = ShareLinkRes.originalOf(original);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }

    @PostMapping
    public ResponseEntity<ApiResult<ShareLinkRes>> createShortLink(@RequestBody ShareLinkReq req) {
        String shortLink = shortLinkService.create(req);
        ShareLinkRes result = ShareLinkRes.shortLinkOf("/share", shortLink);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }
}
