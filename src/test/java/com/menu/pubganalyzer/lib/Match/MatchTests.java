package com.menu.pubganalyzer.lib.Match;

import com.menu.pubganalyzer.service.MatchesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MatchTests {
    private Match match;

    public MatchTests() {
        String matchId = "b906a6b7-9d49-48a2-8c55-84328c879757";
        this.match = new MatchesService().getMatch(matchId);
    }

    @Test
    public void getterTest() {
        assertThat(this.match.getCreatedAt()).isEqualTo("2022-06-04T13:39:02Z");
        assertThat(this.match.getGameMode()).isEqualTo("squad");
        assertThat(this.match.getMapName()).isEqualTo("DihorOtok_Main");
        assertThat(this.match.getTelemetryURL()).isEqualTo("https://telemetry-cdn.pubg.com/bluehole-pubg/steam/2022/06/04/14/09/f5547b49-e40f-11ec-ab58-02ff2badd8f4-telemetry.json");
        List<Roster> rosters = this.match.getRosters();
        List<Participant> participants = this.match.getParticipants();
        System.out.println("Roster(s) : " + rosters.size() + " item(s)");
        for (int i = 0; i < rosters.size(); i++) {
            System.out.println(rosters.get(i).getTeamId());
        }
        System.out.println("Participant(s) : " + participants.size() + " item(s)");
        for (int i = 0; i < participants.size(); i++) {
            System.out.println(participants.get(i).getName() + " : " + participants.get(i).getWinPlace());
        }
    }
}

//    assertThat(this.match.getId()).isEqualTo();
//        assertThat(this.match.getAssists()).isEqualTo();
//        assertThat(this.match.getDamageDealt()).isEqualTo();
//        assertThat(this.match.getKills()).isEqualTo();
//        assertThat(this.match.getName()).isEqualTo();
//        assertThat(this.match.getPlayerId()).isEqualTo();
//        assertThat(this.match.getWinPlace()).isEqualTo();