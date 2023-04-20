package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubgAPI.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
}