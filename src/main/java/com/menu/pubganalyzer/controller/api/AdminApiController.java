package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.dto.MatchDeleteRequest;
import com.menu.pubganalyzer.domain.model.enums.DeleteCondition;
import com.menu.pubganalyzer.service.MatchService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @DeleteMapping("/matches/{condition}/{created}")
    public ApiResult<?> deleteMatchesConditional(@PathVariable String condition, @PathVariable String created) {
        DeleteCondition deleteCondition = DeleteCondition.valueOf(condition);
        LocalDateTime createdAt = LocalDate.parse(created).atTime(LocalTime.MIN);
        matchService.deleteByCreatedAt(deleteCondition, createdAt);
        return ApiResultUtil.success("msg");
    }

}
