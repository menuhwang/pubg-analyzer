package com.menu.pubganalyzer.share.controller;

import com.menu.pubganalyzer.share.service.ShortLinkService;
import com.menu.pubganalyzer.share.dto.request.ShareLinkRequest;
import com.menu.pubganalyzer.share.dto.response.ShareLinkResponse;
import com.menu.pubganalyzer.common.dto.response.ApiResult;
import com.menu.pubganalyzer.common.dto.response.ApiResultUtil;
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
public class ShareRestController {
    private final ShortLinkService shortLinkService;

    @GetMapping("{id}")
    @Operation(
            summary = "원본 주소 조회"
    )
    public ResponseEntity<ApiResult<ShareLinkResponse>> findOriginalPath(@PathVariable String id) {
        String original = shortLinkService.findOriginalPath(id);
        ShareLinkResponse result = ShareLinkResponse.originalOf(original);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }

    @PostMapping
    @Operation(
            summary = "링크 단축"
    )
    public ResponseEntity<ApiResult<ShareLinkResponse>> createShortLink(@RequestBody ShareLinkRequest req) {
        String shortLink = shortLinkService.create(req);
        ShareLinkResponse result = ShareLinkResponse.shortLinkOf("/share", shortLink);
        return ResponseEntity.ok(ApiResultUtil.success(result));
    }
}
