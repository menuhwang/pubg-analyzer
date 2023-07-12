-- Player
CREATE TABLE player
(
    `id`            CHAR(40)  NOT NULL,
    `name`          VARCHAR(255) NOT NULL,
    `title_id`      VARCHAR(255) NOT NULL,
    `shard_id`      VARCHAR(255) NOT NULL,
    `patch_version` VARCHAR(255) NULL,
    `ban_type`      VARCHAR(20) NOT NULL,
    `clan_id`       VARCHAR(40) NULL,
    `created_datetime` datetime    NULL,
    `updated_datetime` datetime    NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);

CREATE UNIQUE INDEX player_name_shard_index ON player (name, shard_id);

-- Asset
CREATE TABLE asset
(
    `id`            CHAR(36)  NOT NULL,
    `name`          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    `url`           CHAR(150) NULL,
    `created_at`    datetime     NULL,
    CONSTRAINT pk_asset PRIMARY KEY (id)
);

-- Match
CREATE TABLE matches
(
    `id`              CHAR(36)  NOT NULL,
    `game_mode`       VARCHAR(255) NULL,
    `season_state`    VARCHAR(255) NULL,
    `duration`        INT          NOT NULL,
    `title_id`        VARCHAR(255) NULL,
    `shard_id`        VARCHAR(255) NULL,
    `map_name`        VARCHAR(255) NULL,
    `is_custom_match` BIT(1)       NOT NULL,
    `match_type`      VARCHAR(255) NULL,
    `created_at`      datetime     NULL,
    `asset_id`        CHAR(36)  NULL,
    CONSTRAINT pk_matches PRIMARY KEY (id)
);

CREATE INDEX match_id_shard_index ON matches (id, shard_id);

ALTER TABLE matches
    ADD CONSTRAINT FK_MATCHES_ON_ASSET FOREIGN KEY (asset_id) REFERENCES asset (id);

-- Roster
CREATE TABLE roster
(
    `id`        CHAR(36)  NOT NULL,
    `won`       BIT(1)       NOT NULL,
    `shard_id`  VARCHAR(255) NULL,
    `win_place` INT          NULL,
    `team_id`   INT          NOT NULL,
    `match_id`  CHAR(36)  NULL,
    CONSTRAINT pk_roster PRIMARY KEY (id)
);

ALTER TABLE roster
    ADD CONSTRAINT FK_ROSTER_ON_MATCH FOREIGN KEY (match_id) REFERENCES matches (id);

-- Participant
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

-- Player_Match
CREATE TABLE player_match
(
    `id`                BIGINT AUTO_INCREMENT NOT NULL,
    `player_id`         CHAR(40)           NULL,
    `match_id`          CHAR(36)           NULL,
    `created_datetime`  datetime             NULL,
    CONSTRAINT pk_player_match PRIMARY KEY (id)
);

CREATE INDEX created_date_time_index ON player_match (created_datetime);

CREATE UNIQUE INDEX player_match_index ON player_match (player_id, match_id);

ALTER TABLE player_match
    ADD CONSTRAINT FK_PLAYER_MATCH_ON_PLAYER FOREIGN KEY (player_id) REFERENCES player (id);

-- LogPlayerKillV2
CREATE TABLE log_player_kill_v2
(
    `id`                            INT AUTO_INCREMENT NOT NULL,
    `match_id`                      CHAR(36)        NULL,
    `timestamp`                     datetime           NULL,
    `attack_id`                     INT                NULL,
    `dbnoid`                        INT                NULL,
    `victim_name`                   VARCHAR(255)       NULL,
    `victim_account_id`             CHAR(40)        NULL,
    `dbnoname`                      VARCHAR(255)       NULL,
    `dbnoaccount_id`                CHAR(40)        NULL,
    `dbnodamage_reason`             VARCHAR(255)       NULL,
    `dbnodamage_type_category`      VARCHAR(255)       NULL,
    `dbnodamage_causer_name`        VARCHAR(255)       NULL,
    `dbnodistance`                  FLOAT              NULL,
    `finisher_name`                 VARCHAR(255)       NULL,
    `finisher_account_id`           CHAR(40)        NULL,
    `finisher_damage_reason`        VARCHAR(255)       NULL,
    `finisher_damage_type_category` VARCHAR(255)       NULL,
    `finisher_damage_causer_name`   VARCHAR(255)       NULL,
    `finisher_distance`             FLOAT              NULL,
    `killer_name`                   VARCHAR(255)       NULL,
    `killer_account_id`             CHAR(40)        NULL,
    `killer_damage_reason`          VARCHAR(255)       NULL,
    `killer_damage_type_category`   VARCHAR(255)       NULL,
    `killer_damage_causer_name`     VARCHAR(255)       NULL,
    `killer_distance`               FLOAT              NULL,
    `suicide`                       BIT(1)             NULL,
    CONSTRAINT pk_log_player_kill_v2 PRIMARY KEY (id)
);

CREATE INDEX killerName_matchId_index ON log_player_kill_v2 (killer_name, match_id);

CREATE INDEX matchId_index ON log_player_kill_v2 (match_id);

-- LogPlayerTakeDamage
CREATE TABLE log_player_take_damage
(
    `id`                   INT AUTO_INCREMENT NOT NULL,
    `match_id`             CHAR(36)        NULL,
    `timestamp`            datetime           NULL,
    `attack_id`            INT                NULL,
    `attacker_name`        VARCHAR(255)       NULL,
    `attacker_team_id`     INT                NULL,
    `attacker_health`      FLOAT              NULL,
    `attacker_ranking`     INT                NULL,
    `attacker_account_id`  CHAR(40)        NULL,
    `victim_name`          VARCHAR(255)       NULL,
    `victim_team_id`       INT                NULL,
    `victim_health`        FLOAT              NULL,
    `victim_ranking`       INT                NULL,
    `victim_account_id`    CHAR(40)        NULL,
    `damage_type_category` VARCHAR(255)       NULL,
    `damage_reason`        VARCHAR(255)       NULL,
    `damage`               FLOAT              NULL,
    `damage_causer_name`   VARCHAR(255)       NULL,
    `phase`                INT                NULL,
    CONSTRAINT pk_logplayertakedamage PRIMARY KEY (id)
);

CREATE INDEX attackerName_victimName_matchId_index ON log_player_take_damage (attacker_name, victim_name, match_id);

CREATE INDEX matchId_index ON log_player_take_damage (match_id);