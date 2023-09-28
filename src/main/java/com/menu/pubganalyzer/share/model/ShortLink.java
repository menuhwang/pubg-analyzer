package com.menu.pubganalyzer.share.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

/*
CREATE TABLE short_link
(
    id               VARCHAR(255) NOT NULL,
    link             VARCHAR(255) NULL,
    created_datetime datetime     NULL,
    CONSTRAINT pk_short_link PRIMARY KEY (id)
);
*/
@Entity(name = "short_link")
@EntityListeners(AuditingEntityListener.class)
public class ShortLink {
    @Id
    private String id;
    private String link;
    @CreatedDate
    private LocalDateTime createdDatetime;

    protected ShortLink() {}

    public ShortLink(String link) {
        this.id = Integer.toHexString(link.hashCode());
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
