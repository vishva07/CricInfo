package com.vishva.CricInfo.service;

import com.vishva.CricInfo.exchange.CricInfoRequest;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import java.util.List;

public interface CricDataService {

    void saveMatch(MatchEntity matchEntity);
    void savePlayer(PlayerEntity playerEntity);
    List<MatchEntity> getMatchEntities(CricInfoRequest cricInfoRequest);
}
