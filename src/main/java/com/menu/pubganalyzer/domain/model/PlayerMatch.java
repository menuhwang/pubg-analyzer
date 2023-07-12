package com.menu.pubganalyzer.domain.model;

import lombok.Builder;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/*
CREATE TABLE player_match
(
    `id`                BIGINT AUTO_INCREMENT NOT NULL,
    `player_id`         CHAR(40)           NULL,
    `match_id`          CHAR(36)           NULL,
    `created_datetime`  datetime             NULL,
    CONSTRAINT pk_player_match PRIMARY KEY (id)
);

CREATE INDEX created_date_time_index ON player_match (created_date_time);

CREATE UNIQUE INDEX player_match_index ON player_match (player_id, match_id);

ALTER TABLE player_match
ADD CONSTRAINT FK_PLAYER_MATCH_ON_PLAYER FOREIGN KEY (player_id) REFERENCES player (id);
*/

@Entity(name = "player_match")
@Table(indexes = {
        @Index(name = "created_datetime_index", columnList = "createdDatetime"),
        @Index(name = "player_match_index", columnList = "player_id,matchId", unique = true)
})
@EntityListeners(AuditingEntityListener.class)
public class PlayerMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    @Column(length = 36)
    private String matchId;
    @CreatedDate
    private LocalDateTime createdDatetime;

    public Player getPlayer() {
        return player;
    }

    public String getMatchId() {
        return matchId;
    }

    protected PlayerMatch() {
    }

    @Builder
    public PlayerMatch(Player player, String matchId) {
        this.player = player;
        this.matchId = matchId;
    }

    public static PlayerMatch of(Player player, String matchId) {
        return PlayerMatch.builder()
                .player(player)
                .matchId(matchId)
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
