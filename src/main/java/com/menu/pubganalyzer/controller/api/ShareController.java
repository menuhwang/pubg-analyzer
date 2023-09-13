package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.ShareLinkReq;
import com.menu.pubganalyzer.domain.dto.ShareLinkRes;
import com.menu.pubganalyzer.service.ShortLinkService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "링크 공유",
        description = "링크 공유 API"
)
@RestController
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShortLinkService shortLinkService;

    @GetMapping("{id}")
    @Operation(
            summary = "원본 주소 조회"
    )
    public ResponseEntity<ApiResult<ShareLinkRes>> findOriginalPath(@PathVariable String id) {
        String original = shortLinkService.findOriginalPath(id);
        ShareLinkRes result = ShareLinkRes.originalOf(original);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }

    @PostMapping
    @Operation(
            summary = "링크 단축"
    )
    public ResponseEntity<ApiResult<ShareLinkRes>> createShortLink(@RequestBody ShareLinkReq req) {
        String shortLink = shortLinkService.create(req);
        ShareLinkRes result = ShareLinkRes.shortLinkOf("/share", shortLink);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }
}
