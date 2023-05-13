package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.SearchPlayer;
import com.menu.pubganalyzer.domain.dto.SearchPlayerReq;
import com.menu.pubganalyzer.service.SearchPlayerService;
import com.menu.pubganalyzer.support.cookie.bookmark.Bookmark;
import com.menu.pubganalyzer.support.cookie.bookmark.BookmarkCookie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final SearchPlayerService searchPlayerService;

    @GetMapping()
    public String search(
            @BookmarkCookie List<Bookmark> bookmarks,
            SearchPlayerReq req,
            Model model,
            @PageableDefault(size = 20) Pageable pageable) {
        SearchPlayer searchPlayer = searchPlayerService.searchPlayer(req, pageable);

        model.addAttribute("viewTitle", req.getNickname());
        model.addAttribute("player", searchPlayer.getPlayer());
        model.addAttribute("stats", searchPlayer.getParticipants());
        model.addAttribute("bookmarkState", bookmarks.contains(Bookmark.of(req.getNickname(), req.getShard().name())));

        return "player";
    }

    @GetMapping("/bookmark/add")
    public String addBookmark(
            @BookmarkCookie List<Bookmark> bookmarks,
            SearchPlayerReq req,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        String nickname = req.getNickname();
        String shardId = req.getShard().name();
        Bookmark bookmark = Bookmark.of(nickname, shardId);
        if (!bookmarks.contains(bookmark)) {
            bookmarks.add(bookmark);
            Cookie cookie = new Cookie("bookmark", URLEncoder.encode(Bookmark.toJson(bookmarks), StandardCharsets.UTF_8));
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        redirectAttributes.addAttribute("shard", shardId);
        redirectAttributes.addAttribute("nickname", nickname);
        return "redirect:/players";
    }

    @GetMapping("/bookmark/delete")
    public String deleteBookmark(
            @BookmarkCookie List<Bookmark> bookmarks,
            SearchPlayerReq req,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        String nickname = req.getNickname();
        String shardId = req.getShard().name();
        bookmarks.remove(Bookmark.of(nickname, shardId));
        Cookie cookie = new Cookie("bookmark", URLEncoder.encode(Bookmark.toJson(bookmarks), StandardCharsets.UTF_8));
        cookie.setPath("/");
        response.addCookie(cookie);

        redirectAttributes.addAttribute("shard", shardId);
        redirectAttributes.addAttribute("nickname", nickname);
        return "redirect:/players";
    }
}
