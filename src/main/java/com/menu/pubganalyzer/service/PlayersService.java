package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.utile.MyHttpRequest;
import com.menu.pubganalyzer.utile.PlayerParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {
    @Value("${pubg.api.key}")
    private String API_KEY;
    public String getAccountId(String[] playerNames) {
        String URL = "https://api.pubg.com/shards/steam/players?filter[playerNames]=";
        for (int i = 0; i < playerNames.length; i++) {
            URL += playerNames[i];
            if (i == playerNames.length) {
                break;
            }
            URL += ",";
        }
        MyHttpRequest request = new MyHttpRequest(URL);
        request.setHeader("Accept", "application/vnd.api+json");
        request.setHeader("Authorization", API_KEY);
        return new PlayerParser(request.get()).parseAccountId();
    }

    public String getMatches(String[] playerNames) {
        String URL = "https://api.pubg.com/shards/steam/players?filter[playerNames]=";
        for (int i = 0; i < playerNames.length; i++) {
            URL += playerNames[i];
            if (i == playerNames.length) {
                break;
            }
            URL += ",";
        }
        MyHttpRequest request = new MyHttpRequest(URL);
        request.setHeader("Accept", "application/vnd.api+json");
        request.setHeader("Authorization", API_KEY);
        return new PlayerParser(request.get()).parseMatches();
    }
}
