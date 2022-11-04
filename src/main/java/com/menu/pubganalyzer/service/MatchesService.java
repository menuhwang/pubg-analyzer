package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.lib.Match.Match;
import com.menu.pubganalyzer.lib.Match.MatchParser;
import com.menu.pubganalyzer.utile.FileHandler;
import com.menu.pubganalyzer.utile.MyHttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MatchesService {
    @Value("${path.match.data}")
    private String matchDataPath;

    public Match getMatch(String matchId) {
//        matchRawData = 파일 찾기 또는 api 요청
        String matchRawData = "";
        String matchDataPath = this.matchDataPath + "\\" + matchId;
        try {
            FileHandler matchFile = new FileHandler(matchDataPath);
            if (matchFile.isFile("match.json")) { // Found match.json and Read the File.
                matchRawData = matchFile.readToString("match.json");
            } else { // Not Found match.json and Request PUBG API.
                MyHttpRequest request = new MyHttpRequest("https://api.pubg.com/shards/steam/matches/" + matchId);
                request.setHeader("Accept", "application/vnd.api+json");
                matchRawData = request.get();
                matchFile.save("match.json", matchRawData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        MatchParser (matchRawData) 생성
        if (matchRawData.equals("")) { // matchRawData 값 정상 확인
            return null;
        }
        MatchParser matchParser = new MatchParser(matchRawData);
//        match.set~
        Match match = new Match();
        match.setCreatedAt(matchParser.getCreatedAt());
        match.setId(matchParser.getId());
        match.setGameMode(matchParser.getGameMode());
        match.setMapName(matchParser.getMapName());
        match.setTelemetryURL(matchParser.getTelemetryURL());
        match.setRosters(matchParser.getRosters());
        match.setParticipants(matchParser.getParticipants());

        return match;
    }
}
