package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.domain.dto.SearchPlayerRes;
import com.menu.pubganalyzer.service.SearchPlayerService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/api/admin/v2/players")
@RequiredArgsConstructor
public class PlayerApiV2Controller {
    private final SearchPlayerService searchPlayerService;

    @GetMapping("/{nickname}")
    public ResponseEntity<ApiResult<SearchPlayerRes>> search(
            @Valid @NotBlank @PathVariable String nickname,
            @PageableDefault(size = 20, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchPlayer searchPlayer = searchPlayerService.searchPlayer(SearchPlayerReq.of(nickname), pageable);

        return ResponseEntity.ok(success(SearchPlayerRes.of(searchPlayer)));
    }
}
