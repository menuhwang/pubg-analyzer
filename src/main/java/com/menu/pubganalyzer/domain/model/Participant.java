package com.menu.pubganalyzer.domain.model;

import com.menu.pubganalyzer.domain.model.enums.Shard;
import com.menu.pubganalyzer.domain.model.enums.match.DeathType;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
CREATE TABLE participant
        (
        `id`               CHAR(36)  NOT NULL,
        `shard_id`         VARCHAR(255) NULL,
        `dbnos`            INT          NOT NULL,
        `assists`          INT          NOT NULL,
        `boosts`           INT          NOT NULL,
        `heals`            INT          NOT NULL,
        `damage_dealt`     FLOAT        NOT NULL,
        `death_type`       VARCHAR(255) NULL,
        `headshot_kills`   INT          NOT NULL,
        `kill_place`       INT          NOT NULL,
        `kill_streaks`     INT          NOT NULL,
        `kills`            INT          NOT NULL,
        `longest_kill`     FLOAT        NOT NULL,
        `revives`          INT          NOT NULL,
        `ride_distance`    FLOAT        NOT NULL,
        `road_kills`       INT          NOT NULL,
        `swim_distance`    FLOAT        NOT NULL,
        `team_kills`       INT          NOT NULL,
        `time_survived`    FLOAT        NOT NULL,
        `vehicle_destroys` INT          NOT NULL,
        `walk_distance`    FLOAT        NOT NULL,
        `weapons_acquired` INT          NOT NULL,
        `win_place`        INT          NOT NULL,
        `name`             VARCHAR(255) NULL,
        `player_id`        CHAR(40)  NULL,
        `roster_id`        CHAR(36)  NULL,
        `match_id`         CHAR(36)  NULL,
        CONSTRAINT pk_participant PRIMARY KEY (id)
        );

        CREATE INDEX name_match_id_index ON participant (name, match_id);

        ALTER TABLE participant
        ADD CONSTRAINT FK_PARTICIPANT_ON_MATCH FOREIGN KEY (match_id) REFERENCES matches (id);

        ALTER TABLE participant
        ADD CONSTRAINT FK_PARTICIPANT_ON_ROSTER FOREIGN KEY (roster_id) REFERENCES roster (id);
*/

@Builder
@AllArgsConstructor
@Getter
@Entity(name = "participant")
@Table(indexes = {@Index(name = "name_match_id_index", columnList = "name, match_id")})
public class Participant {
    @Id
    @Column(length = 36)
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
    @Column(length = 40)
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

    public List<String> getMember() {
        List<String> member = new ArrayList<>(roster.extractParticipantNameWithout(name));
        member.sort(Comparator.naturalOrder());

        return member;
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
