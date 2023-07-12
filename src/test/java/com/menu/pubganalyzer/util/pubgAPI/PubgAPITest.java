package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIMatchNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.exception.PubgAPIPlayerNotFoundException;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.PlayersResponse;
import com.menu.pubganalyzer.util.pubgAPI.response.TelemetryResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PubgAPIConfiguration.class})
@ActiveProfiles("test")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class PubgAPITest {
    @Autowired
    private PubgAPI pubgAPI;

    private static final String SHARD = "steam";
    private static final String PLAYER_NICKNAME = "WackyJacky101";

    private static List<String> MATCH_IDS;
    private static String TELEMETRY_URL;

    @Nested
    @Order(1)
    @DisplayName("플레이어 조회")
    class PlayerTest {
        @Test
        @Order(1)
        @DisplayName("플랫폼과 닉네임 리스트를 받아 PlayerResponse 객체를 반환한다.")
        void player() {
            PlayersResponse players = assertDoesNotThrow(() -> pubgAPI.player(SHARD, List.of(PLAYER_NICKNAME)));
            assertTrue(players.getPlayers().size() > 0);
            PlayersResponse.Player player = players.getPlayers().get(0);
            assertEquals(PLAYER_NICKNAME, player.getAttributes().getName());
            assertFalse(player.getRelationships().getMatches().isEmpty());
            MATCH_IDS = player.getMatchIds();
            assertTrue(MATCH_IDS.size() > 0);
            System.out.println("Match id");
            for (int i = 0; i < MATCH_IDS.size(); i++) {
                System.out.printf("%d:%s\n", i + 1, MATCH_IDS.get(i));
            }
        }

        @Test
        @Order(2)
        @DisplayName("조회 결과가 없는 경우 PubgAPIPlayerNotFoundException 예외를 반환한다.")
        void player_not_found() {
            String wrongNickname = "w";
            Exception e = assertThrows(PubgAPIPlayerNotFoundException.class, () -> pubgAPI.player(SHARD, List.of(wrongNickname)));
            assertEquals(PubgAPIPlayerNotFoundException.DEFAULT_MSG, e.getMessage());
        }
    }

    @Nested
    @Order(2)
    @DisplayName("매치 조회")
    class MatchTest {
        @Test
        @Order(1)
        @DisplayName("플랫폼과 매치 id를 받아 MatchResponse 객체를 반환한다.")
        void match() {
            String matchId = MATCH_IDS.get(0);
            MatchResponse m = pubgAPI.match(SHARD, matchId);
            assertEquals(matchId, m.getId());
            assertTrue(m.getIncluded().size() > 0);
            List<MatchResponse.Element> elements = m.getIncluded();
            System.out.println("Included");
            for (int i = 0; i < elements.size(); i++) {
                MatchResponse.Element element = elements.get(i);
                System.out.printf("%d:%s\n", i + 1, element);
                if (element.getType().equals("asset")) TELEMETRY_URL = element.getAttributes().getURL();
            }
        }

        @Test
        @Order(2)
        @DisplayName("잘못된 매치 id 조회시 PubgAPIMatchNotFoundException 예외를 반환한다.")
        void match_not_found() {
            String wrongId = "wrong_match_id";
            Exception e = assertThrows(PubgAPIMatchNotFoundException.class, () -> pubgAPI.match(SHARD, wrongId));
            assertEquals(String.format(PubgAPIMatchNotFoundException.MESSAGE_PATTERN, wrongId), e.getMessage());
        }
    }

    @Nested
    @Order(3)
    @DisplayName("텔레메트리 조회")
    class TelemetryTest {
        @Test
        @Order(31)
        @DisplayName("텔레메트리 URL를 받아 TelemetryResponse 리스트를 반환한다.")
        void telemetry() {
            List<TelemetryResponse> telemetry = assertDoesNotThrow(() -> pubgAPI.telemetry(TELEMETRY_URL));
            assertTrue(telemetry.size() > 0);
            System.out.printf("telemetry size:%d\n", telemetry.size());
        }
    }
}