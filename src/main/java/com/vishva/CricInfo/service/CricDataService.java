package com.vishva.CricInfo.service;

import com.vishva.CricInfo.exchange.*;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.model.TossEntity;

import java.util.List;
import java.util.Set;

public interface CricDataService {

    void saveMatch(MatchEntity matchEntity);
    void savePlayer(PlayerEntity playerEntity);
    List<MatchEntity> getMatchEntities(MatchRequest matchRequest);
    List<List<String>> getPlayerOfMatch(PlayerOfMatchRequest playerOfMatchRequest);
    List<OutcomeEntity> getOutcome(OutcomeRequest outcomeRequest);
    List<TossEntity> getToss(TossRequest tossRequest);
    Set<PlayerEntity> getTeam(TeamRequest teamRequest);
}
