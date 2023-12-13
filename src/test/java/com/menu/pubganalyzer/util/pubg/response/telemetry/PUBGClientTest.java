package com.menu.pubganalyzer.util.pubg.response.telemetry;

import com.menu.pubganalyzer.support.feign.FeignClientTest;
import com.menu.pubganalyzer.util.pubg.MatchClient;
import com.menu.pubganalyzer.util.pubg.PlayerClient;
import com.menu.pubganalyzer.util.pubg.TelemetryClient;
import com.menu.pubganalyzer.util.pubg.response.match.MatchResponse;
import com.menu.pubganalyzer.util.pubg.response.player.PlayersResponse;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.menu.pubganalyzer.util.pubg.TelemetryClient.PUBG_TELEMETRY_CDN;
import static org.junit.jupiter.api.Assertions.*;

@FeignClientTest
@ActiveProfiles("test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUBGClientTest {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String shard = "steam";
    private final List<String> matches = new ArrayList<>();
    private String telemetryURL;

    @Autowired
    private PlayerClient playerClient;
    @Autowired
    private MatchClient matchClient;
    @Autowired
    private TelemetryClient telemetryClient;

    @Test
    @Order(1)
    void fetchPlayer() {
        String playerName = "WackyJacky101";
        PlayersResponse response = playerClient.fetchPlayers(shard, playerName);

        log.info("fetch player: {}", response);
        assertEquals(1, response.getPlayers().size());

        matches.addAll(response.getPlayers().get(0).getMatchIds());
    }

    @Test
    @Order(2)
    void fetchMatch() {
        assertFalse(matches.isEmpty());

        String matchId = matches.get(0);

        MatchResponse response = matchClient.fetchMatch(shard, matchId);

//        log.info("fetch match: {}", response);
        assertEquals(matchId, response.getId());

        telemetryURL = Objects.requireNonNull(
                response.getIncluded().stream()
                        .filter(element -> "asset".equals(element.getType()))
                        .findFirst()
                        .orElseThrow(AssertionError::new)
                        .getAttributes()
                        .getURL()
                );

        log.info(telemetryURL);
    }

    @Test
    @Order(3)
    void fetchTelemetry() {
        assertNotNull(telemetryURL);

        assertTrue(telemetryURL.startsWith(PUBG_TELEMETRY_CDN));

        telemetryURL = telemetryURL.substring(PUBG_TELEMETRY_CDN.length());

        List<Object> response = telemetryClient.fetchTelemetry(telemetryURL);

        assertFalse(response.isEmpty());
        log.info("fetch telemetry element size: {}", response.size());
        log.info("fetch telemetry first element: {}", response.get(0));
    }
}
