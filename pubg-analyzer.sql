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
    `update_count`  INT         NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);

CREATE UNIQUE INDEX player_name_shard_index ON player (name, shard_id);

-- ShortLink
CREATE TABLE short_link
(
    id               VARCHAR(255) NOT NULL,
    link             VARCHAR(255) NULL,
    created_datetime datetime     NULL,
    CONSTRAINT pk_short_link PRIMARY KEY (id)
);