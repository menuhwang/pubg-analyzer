package com.menu.pubganalyzer.model;

import com.menu.pubganalyzer.model.enums.Shard;
import com.menu.pubganalyzer.model.enums.match.DeathType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Map;

@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(indexes = {
        @Index(name = "participant_playerId_match_index", columnList = "playerId, match_id")
})
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
    private int timeSurvived;
    private int vehicleDestroys;
    private float walkDistance;
    private int weaponsAcquired;
    private int winPlace;
    private String name;
    private String playerId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Roster roster;
    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;

    protected Participant() {
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public static Participant of(Map<String, Object> raw, Match match) {
        String type = (String) raw.getOrDefault("type", null);
        if (type == null || !type.equals("participant")) throw new IllegalArgumentException("정상적인 로스터 데이터를 입력해주세요.");

        Map<String, Object> attributes = (Map<String, Object>) raw.get("attributes");
        Map<String, Object> stats = (Map<String, Object>) attributes.get("stats");

        return Participant.builder()
                .id((String) raw.get("id"))
                .shardId(Shard.valueOf(((String) attributes.get("shardId")).toUpperCase()))
                .dbnos((int) stats.get("DBNOs"))
                .assists((int) stats.get("assists"))
                .boosts((int) stats.get("boosts"))
                .heals((int) stats.get("heals"))
                .damageDealt(stats.get("damageDealt") instanceof Integer ? Float.valueOf((int) stats.get("damageDealt")): ((Double) stats.get("damageDealt")).floatValue())
                .deathType(DeathType.valueOf((String) stats.get("deathType")))
                .headshotKills((int) stats.get("headshotKills"))
                .killPlace((int) stats.get("killPlace"))
                .killStreaks((int) stats.get("killStreaks"))
                .kills((int) stats.get("kills"))
                .longestKill(stats.get("longestKill") instanceof Integer ? (float) (int) stats.get("longestKill") : ((Double) stats.get("longestKill")).floatValue())
                .revives((int) stats.get("revives"))
                .rideDistance(stats.get("rideDistance") instanceof Integer ? (float) (int) stats.get("rideDistance") : ((Double) stats.get("rideDistance")).floatValue())
                .roadKills((int) stats.get("roadKills"))
                .swimDistance(stats.get("swimDistance") instanceof Integer ? (float) (int) stats.get("swimDistance") : ((Double) stats.get("swimDistance")).floatValue())
                .teamKills((int) stats.get("teamKills"))
                .timeSurvived((int) stats.get("timeSurvived"))
                .vehicleDestroys((int) stats.get("vehicleDestroys"))
                .walkDistance(stats.get("walkDistance") instanceof Integer ? (float) (int) stats.get("walkDistance") : ((Double) stats.get("walkDistance")).floatValue())
                .weaponsAcquired((int) stats.get("weaponsAcquired"))
                .winPlace((int) stats.get("winPlace"))
                .name((String) stats.get("name"))
                .playerId(((String) stats.get("playerId")).startsWith("account") ? (String) stats.get("playerId") : null)
                .match(match)
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
                ", match='" + match.getId() + '\'' +
                '}';
    }
}
