package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.support.cookie.bookmark.Bookmark;
import com.menu.pubganalyzer.support.cookie.bookmark.BookmarkCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping
    public String home(@BookmarkCookie List<Bookmark> bookmarks, Model model) {
        System.out.println(bookmarks);
        model.addAttribute("viewTitle", "Home");
        model.addAttribute("shards", Arrays.stream(Shard.values()).map(Shard::name).collect(Collectors.toList()));
        model.addAttribute("bookmarks", bookmarks);
        return "index";
    }
}
