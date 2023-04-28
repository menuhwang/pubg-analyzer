package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.enums.match.DeathType;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(indexes = {@Index(name = "name_match_id_index", columnList = "name, match_id")})
public class Participant {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Shard shardId;
    private int dbnos;
    private int assists;
    private int boosts;
    private int heals;
    private float damageDealt;
    @Enumerated(EnumType.STRING)
    private DeathType deathType;
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
    private String name;
    private String playerId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Roster roster;
    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;

    protected Participant() {
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public void setMatch(Match match) {
        this.match = match;
    }


    public static Participant of(MatchResponse.Element participant) {
        MatchResponse.Attribute attribute = participant.getAttributes();
        MatchResponse.Stat stat = participant.getAttributes().getStats();

        return Participant.builder()
                .id(participant.getId())
                .shardId(Shard.valueOf((attribute.getShardId()).toUpperCase()))
                .dbnos(stat.getDBNOs())
                .assists(stat.getAssists())
                .boosts(stat.getBoosts())
                .heals(stat.getHeals())
                .damageDealt(stat.getDamageDealt())
                .deathType(DeathType.valueOf(stat.getDeathType()))
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
                ", roster='" + roster.getId() + '\'' +
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
