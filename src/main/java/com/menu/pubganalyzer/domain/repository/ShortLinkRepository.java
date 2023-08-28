package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLink, String> {
}