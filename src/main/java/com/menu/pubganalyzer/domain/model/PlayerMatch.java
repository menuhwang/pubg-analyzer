package com.menu.pubganalyzer.domain.model;

import lombok.Builder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "player_match")
@Table(indexes = {
        @Index(name = "created_date_time_index", columnList = "createdDateTime"),
        @Index(name = "player_match_index", columnList = "player_id,matchId", unique = true)
})
public class PlayerMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    private String matchId;
    private LocalDateTime createdDateTime;

    public Player getPlayer() {
        return player;
    }

    public String getMatchId() {
        return matchId;
    }

    protected PlayerMatch() {
    }

    @Builder
    public PlayerMatch(Player player, String matchId, LocalDateTime createdDateTime) {
        this.player = player;
        this.matchId = matchId;
        this.createdDateTime = createdDateTime;
    }

    public static PlayerMatch of(Player player, String matchId, LocalDateTime createdDateTime) {
        return PlayerMatch.builder()
                .player(player)
                .matchId(matchId)
                .createdDateTime(createdDateTime)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerMatch that = (PlayerMatch) o;
        return (id != null && Objects.equals(id, that.id))
                || (player != null && player.getId() != null && matchId != null
                && player.getId().equals(that.player.getId()) && matchId.equals(that.matchId));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
