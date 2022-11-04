package com.menu.pubganalyzer.service;

import com.menu.pubganalyzer.lib.Telemetry.Telemetry;
import com.menu.pubganalyzer.utile.FileHandler;
import com.menu.pubganalyzer.utile.MyHttpRequest;
import com.menu.pubganalyzer.lib.Telemetry.TelemetryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class TelemetryService {
    @Value("${path.telemetry.data}")
    private String telemetryDataPath;
    @Autowired
    private MatchesService matchesService;

    public Telemetry getTelemetry(String matchId) {
        String telemetryRawData = "";
        String logPlayerKillRawData = "";
        String logPlayerTakeDamageRawData = "";
        TelemetryParser telemetryParser = null;
        String telemetryDataPath = this.telemetryDataPath + "\\" + matchId;
        try {
            FileHandler telemetryFolder = new FileHandler(telemetryDataPath);
            if (telemetryFolder.isFile("LogPlayerKillV2.json") && telemetryFolder.isFile("LogPlayerTakeDamage.json")) { // Found files and Read Files.
                logPlayerKillRawData = telemetryFolder.readToString("LogPlayerKillV2.json");
                logPlayerTakeDamageRawData = telemetryFolder.readToString("LogPlayerTakeDamage.json");
                telemetryParser = new TelemetryParser(logPlayerKillRawData, logPlayerTakeDamageRawData);
            } else {
                if (telemetryFolder.isFile("telemetry.json")) {
                    telemetryParser = new TelemetryParser(telemetryFolder.readToString("telemetry.json"));
                } else {
                    String telemetryURL = matchesService.getMatch(matchId).getTelemetryURL();
                    MyHttpRequest request = new MyHttpRequest(telemetryURL);
                    request.setHeader("Accept", "application/vnd.api+json");
                    // Accept-Encoding: gzip
                    request.setHeader("Accept-Encoding", "gzip");
                    telemetryRawData = request.get();
                    telemetryFolder.save("telemetry.json", telemetryRawData);
                    telemetryParser = new TelemetryParser(telemetryRawData);
                }
                telemetryFolder.save("LogPlayerKillV2.json", telemetryParser.getLogPlayerKillRaw());
                telemetryFolder.save("LogPlayerTakeDamage.json", telemetryParser.getLogPlayerTakeDamageRaw());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Telemetry telemetry = new Telemetry();
        telemetry.setLogPlayerKillList(telemetryParser.getLogPlayerKillList());
        telemetry.setLogPlayerTakeDamageList(telemetryParser.getLogPlayerTakeDamageList());
        return telemetry;
    }
}
