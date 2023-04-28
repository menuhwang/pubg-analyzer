package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.domain.model.LogPlayerKillV2;
import com.menu.pubganalyzer.domain.model.LogPlayerTakeDamage;
import com.menu.pubganalyzer.util.pubgAPI.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PubgAPIConfiguration.class})
@ActiveProfiles("test")
class PubgAPITest {
    @Value("${util.pubg.api.test.data.match.id}")
    private String MATCH_ID;

    @Value("${util.pubg.api.test.data.match.ids}")
    private List<String> MATCH_IDS;

    @Value("${util.pubg.api.test.data.player.nickname}")
    private String PLAYER_NICKNAME;

    @Value("${util.pubg.api.test.data.player.nicknames}")
    private List<String> PLAYER_NICKNAMES;

    @Value("${util.pubg.api.test.data.telemetry.url}")
    private String TELEMETRY_URL;

    @Autowired
    private PubgAPI pubgAPI;

    @Test
    void match() {
        MatchResponse m = pubgAPI.match(MATCH_ID);
        for (MatchResponse.Element element : m.getIncluded()) System.out.println(element);
        assertEquals(MATCH_ID, m.getId());
    }

    @Test
    void match_not_found() {
        String wrongId = "wrong_match_id";
        Exception e = assertThrows(MatchNotFoundException.class, () -> pubgAPI.match(wrongId));
        assertEquals(String.format(MatchNotFoundException.MESSAGE_PATTERN, wrongId), e.getMessage());
    }

    @Test
    void matchParallel() {
        List<String> result = MATCH_IDS.stream()
                .parallel()
                .map(pubgAPI::match)
                .map(MatchResponse::getId)
                .collect(Collectors.toList());

        for (String id : result) {
            assertTrue(MATCH_IDS.contains(id));
        }
    }

    @Test
    void player() {
        PlayersResponse players = pubgAPI.player(PLAYER_NICKNAMES);
        for (PlayersResponse.Player player : players.getPlayers()) {
            assertTrue(PLAYER_NICKNAMES.contains(player.getAttributes().getName()));
            System.out.println(player.getMatchIds());
            assertFalse(player.getRelationships().getMatches().isEmpty());
        }
    }

    @Test
    void telemetry() {
        List<TelemetryResponse> telemetry = pubgAPI.telemetry(TELEMETRY_URL);
        System.out.println(telemetry.size());
        int logPlayerKillV2 = 0;
        int logPlayerTakeDamage = 0;

        List<LogPlayerKillV2> logPlayerKillObj = new ArrayList<>();
        List<LogPlayerTakeDamage> logPlayerTakeDamageObj = new ArrayList<>();
        for (TelemetryResponse log : telemetry) {
            try {
                if (log.getType().equals("LogPlayerKillV2")) {
                    logPlayerKillObj.add(LogPlayerKillV2.of(log, "dummy"));
                    logPlayerKillV2++;
                } else if (log.getType().equals("LogPlayerTakeDamage")) {
                    logPlayerTakeDamageObj.add(LogPlayerTakeDamage.of(log, "dummy"));
                    logPlayerTakeDamage++;
                }
            } catch (Exception e) {
                System.out.println(log);
                throw e;
            }
        }

        System.out.printf("logPlayerKillV2 : %d\n", logPlayerKillV2);
        System.out.printf("logPlayerTakeDamage : %d\n", logPlayerTakeDamage);
    }
}