package com.menu.pubganalyzer.domain.repository;

import com.menu.pubganalyzer.domain.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Roster, String> {
}