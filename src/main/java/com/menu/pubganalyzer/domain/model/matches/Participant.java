package com.menu.pubganalyzer.domain.model.matches;

import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@AllArgsConstructor
@Getter
public class Participant {
    private String id;
    private String shardId;
    private int dbnos;
    private int assists;
    private int boosts;
    private int heals;
    private float damageDealt;
    private String deathType;
    private int headshotKills;
    private int killPlace;
    private int killStreaks;
    private int kills;
    private float longestKill;
    private int revives;
    private float rideDistance;
    private int roadKills;
    private float swimDistance;
    private int teamKills;
    private float timeSurvived;
    private int vehicleDestroys;
    private float walkDistance;
    private int weaponsAcquired;
    private int winPlace;
    @Indexed
    private String name;
    private String playerId;

    protected Participant() {
    }

    public static Participant of(MatchResponse.Element participant) {
        MatchResponse.Attribute attribute = participant.getAttributes();
        MatchResponse.Stat stat = participant.getAttributes().getStats();

        return Participant.builder()
                .id(participant.getId())
                .shardId(attribute.getShardId())
                .dbnos(stat.getDBNOs())
                .assists(stat.getAssists())
                .boosts(stat.getBoosts())
                .heals(stat.getHeals())
                .damageDealt(stat.getDamageDealt())
                .deathType(stat.getDeathType())
                .headshotKills(stat.getHeadshotKills())
                .killPlace(stat.getKillPlace())
                .killStreaks(stat.getKillStreaks())
                .kills(stat.getKills())
                .longestKill(stat.getLongestKill())
                .revives(stat.getRevives())
                .rideDistance(stat.getRideDistance())
                .roadKills(stat.getRoadKills())
                .swimDistance(stat.getSwimDistance())
                .teamKills(stat.getTeamKills())
                .timeSurvived(stat.getTimeSurvived())
                .vehicleDestroys(stat.getVehicleDestroys())
                .walkDistance(stat.getWalkDistance())
                .weaponsAcquired(stat.getWeaponsAcquired())
                .winPlace(stat.getWinPlace())
                .name(stat.getName())
                .playerId((stat.getPlayerId().startsWith("account") ? stat.getPlayerId() : "ai"))
                .build();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", shardId=" + shardId +
                ", DBNOs=" + dbnos +
                ", assists=" + assists +
                ", boosts=" + boosts +
                ", heals=" + heals +
                ", damageDealt=" + damageDealt +
                ", deathType=" + deathType +
                ", headshotKills=" + headshotKills +
                ", killPlace=" + killPlace +
                ", killStreaks=" + killStreaks +
                ", kills=" + kills +
                ", longestKill=" + longestKill +
                ", revives=" + revives +
                ", rideDistance=" + rideDistance +
                ", roadKills=" + roadKills +
                ", swimDistance=" + swimDistance +
                ", teamKills=" + teamKills +
                ", timeSurvived=" + timeSurvived +
                ", vehicleDestroys=" + vehicleDestroys +
                ", walkDistance=" + walkDistance +
                ", weaponsAcquired=" + weaponsAcquired +
                ", winPlace=" + winPlace +
                ", name='" + name + '\'' +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
