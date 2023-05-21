package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.MatchDeleteRequest;
import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {
    private final MatchService matchService;

    @DeleteMapping("/matches")
    public ApiResult<?> deleteMatches(@RequestBody MatchDeleteRequest request) {
        matchService.deleteById(request.getMatches());

        return ApiResultUtil.success(204, "delete done");
    }

}
