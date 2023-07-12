package com.menu.pubganalyzer.controller.api;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerRes;
import com.menu.pubganalyzer.service.SearchPlayerService;
import com.menu.pubganalyzer.support.apiResult.ApiResult;
import com.menu.pubganalyzer.support.apiResult.ApiResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.menu.pubganalyzer.support.apiResult.ApiResultUtil.success;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerRestController {
    private final SearchPlayerService searchPlayerService;

    @GetMapping("/{nickname}")
    public ResponseEntity<ApiResult<SearchPlayerRes>> search(
            @Valid @NotBlank @PathVariable String nickname,
            @PageableDefault(size = 20, sort = "createdDatetime", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchPlayer searchPlayer = searchPlayerService.searchPlayer(nickname, pageable);

        return ResponseEntity.ok(success(SearchPlayerRes.of(searchPlayer)));
    }

    @PatchMapping("/{nickname}")
    public ResponseEntity<ApiResult<Void>> updateMatchHistory(
            @Valid @NotBlank @PathVariable String nickname) {
        searchPlayerService.updateMatchHistory(nickname);
        return ResponseEntity.ok().body(ApiResultUtil.success());
    }
}
