package com.menu.pubganalyzer.lib.Match;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ParticipantTests {
    private JSONObject mock_participant;
    private Participant participant;

    public ParticipantTests() {
        try {
            this.mock_participant = new JSONObject("{\n" +
                    "      \"type\": \"participant\",\n" +
                    "      \"id\": \"ec8ccd0b-e8b1-47a7-ae3d-efc18b2f364b\",\n" +
                    "      \"attributes\": {\n" +
                    "        \"stats\": {\n" +
                    "          \"DBNOs\": 2,\n" +
                    "          \"assists\": 0,\n" +
                    "          \"boosts\": 5,\n" +
                    "          \"damageDealt\": 178.37692,\n" +
                    "          \"deathType\": \"byplayer\",\n" +
                    "          \"headshotKills\": 0,\n" +
                    "          \"heals\": 1,\n" +
                    "          \"killPlace\": 15,\n" +
                    "          \"killStreaks\": 1,\n" +
                    "          \"kills\": 2,\n" +
                    "          \"longestKill\": 87.29852,\n" +
                    "          \"name\": \"Patriot_Engineer\",\n" +
                    "          \"playerId\": \"account.b5b9f342e8824021853f3d34308589d5\",\n" +
                    "          \"revives\": 1,\n" +
                    "          \"rideDistance\": 0,\n" +
                    "          \"roadKills\": 0,\n" +
                    "          \"swimDistance\": 132.98006,\n" +
                    "          \"teamKills\": 0,\n" +
                    "          \"timeSurvived\": 1187,\n" +
                    "          \"vehicleDestroys\": 1,\n" +
                    "          \"walkDistance\": 2675.4702,\n" +
                    "          \"weaponsAcquired\": 4,\n" +
                    "          \"winPlace\": 2\n" +
                    "        },\n" +
                    "        \"actor\": \"\",\n" +
                    "        \"shardId\": \"steam\"\n" +
                    "      }\n" +
                    "    }");
            this.participant = new Participant(this.mock_participant);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getterTest() {
        assertThat(this.participant.getId()).isEqualTo("ec8ccd0b-e8b1-47a7-ae3d-efc18b2f364b");
        assertThat(this.participant.getAssists()).isEqualTo(0);
        assertThat(this.participant.getDamageDealt()).isEqualTo(178.37692);
        assertThat(this.participant.getKills()).isEqualTo(2);
        assertThat(this.participant.getName()).isEqualTo("Patriot_Engineer");
        assertThat(this.participant.getPlayerId()).isEqualTo("account.b5b9f342e8824021853f3d34308589d5");
        assertThat(this.participant.getWinPlace()).isEqualTo(2);
    }
}
