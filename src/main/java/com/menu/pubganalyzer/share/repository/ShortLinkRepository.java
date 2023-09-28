package com.menu.pubganalyzer.share.repository;

import com.menu.pubganalyzer.share.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLink, String> {
}