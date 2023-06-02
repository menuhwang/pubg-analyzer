package com.menu.pubganalyzer.domain.dao;

import com.menu.pubganalyzer.domain.model.Participant;

public interface ParticipantDAO {
    Participant findByMatchIdAndPlayerName(String matchId, String playerName);
}
