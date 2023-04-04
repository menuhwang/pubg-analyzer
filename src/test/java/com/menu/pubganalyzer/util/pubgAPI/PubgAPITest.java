package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.domain.model.Match;
import com.menu.pubganalyzer.domain.model.Player;
import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.util.pubgAPI.exception.MatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.exception.PlayerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;
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
        Match m = pubgAPI.match(MATCH_ID);
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
                .map(Match::getId)
                .collect(Collectors.toList());

        for (String id : result) {
            assertTrue(MATCH_IDS.contains(id));
        }
    }

    @Test
    void player() {
        Player player = pubgAPI.player(PLAYER_NICKNAME);

        assertEquals(PLAYER_NICKNAME, player.getName());
        assertFalse(player.getMatchIds().isEmpty());
    }

    @Test
    void multiPlayer() {
        Set<Player> players = pubgAPI.player(PLAYER_NICKNAMES);

        for (Player player : players) {
            assertTrue(PLAYER_NICKNAMES.contains(player.getName()));
            assertFalse(player.getMatchIds().isEmpty());
        }
    }

    @Test
    void player_wrong_shard() {
        pubgAPI.setShard(Shard.KAKAO);

        assertThrows(PlayerNotFoundException.class, () -> pubgAPI.player(PLAYER_NICKNAME));

        pubgAPI.setShard(Shard.STEAM);
    }
}