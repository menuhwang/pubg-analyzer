package com.menu.pubganalyzer.lib.Match;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RosterTests {
    private JSONObject mock_roster;
    private Roster roster;

    public RosterTests() {
        try {
            this.mock_roster = new JSONObject("{\n" +
                    "      \"type\": \"roster\",\n" +
                    "      \"id\": \"8e8230f4-d8b7-4496-9638-65b1eacf7365\",\n" +
                    "      \"attributes\": {\n" +
                    "        \"stats\": {\n" +
                    "          \"rank\": 2,\n" +
                    "          \"teamId\": 22\n" +
                    "        },\n" +
                    "        \"won\": \"false\",\n" +
                    "        \"shardId\": \"steam\"\n" +
                    "      },\n" +
                    "      \"relationships\": {\n" +
                    "        \"participants\": {\n" +
                    "          \"data\": [\n" +
                    "            {\n" +
                    "              \"type\": \"participant\",\n" +
                    "              \"id\": \"f523ebb8-90e6-4d5d-ad95-eb0d761172ea\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"type\": \"participant\",\n" +
                    "              \"id\": \"ec8ccd0b-e8b1-47a7-ae3d-efc18b2f364b\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"type\": \"participant\",\n" +
                    "              \"id\": \"c0ed6eb8-d4c7-44d0-b967-9ca69b845880\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"type\": \"participant\",\n" +
                    "              \"id\": \"795935b2-3480-4987-b02d-bc3fad8d7569\"\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        \"team\": {\n" +
                    "          \"data\": null\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }");
            this.roster = new Roster(mock_roster);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getterTest() {
        assertThat(this.roster.getId()).isEqualTo("8e8230f4-d8b7-4496-9638-65b1eacf7365");
        assertThat(this.roster.getRank()).isEqualTo(2);
        assertThat(this.roster.getTeamId()).isEqualTo(22);
        assertThat(this.roster.isWon()).isEqualTo(false);
    }
}
