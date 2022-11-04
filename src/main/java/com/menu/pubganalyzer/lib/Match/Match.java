package com.menu.pubganalyzer.lib.Match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Match {
    private String createdAt;
    private String id;
    private String gameMode;
    private String mapName;
    private String telemetryURL;
    private List<Roster> rosters;
    private List<Participant> participants;

    public Participant findParticipantByName(String playerName) {
        Participant participant = null;
        for (int i = 0; i < this.participants.size(); i++) {
            if (playerName.equals(this.participants.get(i).getName())) {
                participant = this.participants.get(i);
                break;
            }
        }
        return participant;
    }
    public String getInformation() {
        JSONObject information = new JSONObject();
        try {
            information.put("createdAt", this.createdAt);
            information.put("gameMode", this.gameMode);
            information.put("mapName", this.mapName);
            information.put("matchId", this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return information.toString();
    }
}
