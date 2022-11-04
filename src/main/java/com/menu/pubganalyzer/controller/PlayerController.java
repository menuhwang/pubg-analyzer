package com.menu.pubganalyzer.controller;

import com.menu.pubganalyzer.service.PlayersService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PlayerController {
    private final PlayersService playersService;

    public PlayerController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping(value = "/player/accountId/{playerNames}", produces = "application/json; charset=UTF-8")
    public String getAccountId(@PathVariable String playerNames) {
        String playerNameStr = playerNames;
        playerNameStr = playerNameStr.replaceAll(" ", "");
        String[] playerNameArry = playerNameStr.split(",");
        return this.playersService.getAccountId(playerNameArry);
    }

    @GetMapping(value = "/player/matches/{playerNames}", produces = "application/json; charset=UTF-8")
    public String getMatches(@PathVariable String playerNames) {
        String playerNameStr = playerNames;
        playerNameStr = playerNameStr.replaceAll(" ", "");
        String[] playerNameArry = playerNameStr.split(",");
        return this.playersService.getMatches(playerNameArry);
    }
    @GetMapping(value = "/player/matches/together/{playerNames}", produces = "application/json; charset=UTF-8")
    public String getTogetherMatches(@PathVariable String playerNames) {
        String playerNameStr = playerNames;
        playerNameStr = playerNameStr.replaceAll(" ", "");
        String[] playerNameArry = playerNameStr.split(",");
        List<String> retainMatch = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(this.playersService.getMatches(playerNameArry));
            String regex = "[\\[\\]\"\s]";
            for (int i = 1; i < data.length(); i++) {
                if (retainMatch.isEmpty()) {
                    String jsonStr1 = data.getJSONObject(i-1).getString("matches");
                    String jsonStr2 = data.getJSONObject(i).getString("matches");
                    String[] buf1 = jsonStr1.replaceAll(regex, "").split(",");
                    String[] buf2 = jsonStr2.replaceAll(regex, "").split(",");
                    List<String> arry1 = new ArrayList<>(Arrays.asList(buf1));
                    List<String> arry2 = new ArrayList<>(Arrays.asList(buf2));
                    retainMatch = arry1;
                    retainMatch.retainAll(arry2);
                } else {
                    String jsonStr1 = data.getJSONObject(i).getString("matches");
                    String[] buf1 = jsonStr1.replaceAll(regex, "").split(",");
                    List<String> arry1 = new ArrayList<>(Arrays.asList(buf1));
                    retainMatch.retainAll(arry1);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retainMatch.toString();
    }
}
