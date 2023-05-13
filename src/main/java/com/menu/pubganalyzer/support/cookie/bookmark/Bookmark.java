package com.menu.pubganalyzer.support.cookie.bookmark;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Bookmark {
    private String player;
    private String shard;

    public String getPlayer() {
        return player;
    }

    public String getShard() {
        return shard;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }

    public static Bookmark of(String player, String shard) {
        Bookmark bookmark = new Bookmark();
        bookmark.setPlayer(player);
        bookmark.setShard(shard);
        return bookmark;
    }

    public static String toJson(List<Bookmark> bookmarkList) {
        Gson gson = new Gson();
        return gson.toJson(bookmarkList);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "player='" + player + '\'' +
                ", shard='" + shard + '\'' +
                '}';
    }
}
