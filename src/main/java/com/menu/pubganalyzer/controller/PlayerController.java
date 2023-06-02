package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.service.SearchPlayerService;
import com.menu.pubganalyzer.support.cookie.bookmark.Bookmark;
import com.menu.pubganalyzer.support.cookie.bookmark.BookmarkCookie;
import com.menu.pubganalyzer.support.cookie.bookmark.BookmarkCookieParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final SearchPlayerService searchPlayerService;

    @GetMapping()
    public String search(
            @BookmarkCookie BookmarkCookieParameter bookmarkCookieParameter,
            SearchPlayerReq req,
            Model model,
            @PageableDefault(size = 20, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchPlayer searchPlayer = searchPlayerService.searchPlayer(req, pageable);

        model.addAttribute("viewTitle", req.getNickname());
        model.addAttribute("player", searchPlayer.getPlayer());
        model.addAttribute("stats", searchPlayer.getParticipants());
        model.addAttribute("bookmarkState", bookmarkCookieParameter.getBookmarks().contains(Bookmark.of(req.getNickname(), req.getShard().name())));

        return "player";
    }

    @GetMapping("/bookmark/add")
    public String addBookmark(
            @BookmarkCookie BookmarkCookieParameter bookmarkCookieParameter,
            SearchPlayerReq req,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        String nickname = req.getNickname();
        String shardId = req.getShard().name();

        Bookmark bookmark = Bookmark.of(nickname, shardId);
        if (!bookmarkCookieParameter.contains(bookmark)) {
            bookmarkCookieParameter.add(bookmark);
            response.addCookie(bookmarkCookieParameter.getCookie());
        }

        redirectAttributes.addAttribute("shard", shardId);
        redirectAttributes.addAttribute("nickname", nickname);
        return "redirect:/players";
    }

    @GetMapping("/bookmark/delete")
    public String deleteBookmark(
            @BookmarkCookie BookmarkCookieParameter bookmarkCookieParameter,
            SearchPlayerReq req,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        String nickname = req.getNickname();
        String shardId = req.getShard().name();

        bookmarkCookieParameter.remove(Bookmark.of(nickname, shardId));
        response.addCookie(bookmarkCookieParameter.getCookie());

        redirectAttributes.addAttribute("shard", shardId);
        redirectAttributes.addAttribute("nickname", nickname);
        return "redirect:/players";
    }
}
