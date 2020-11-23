package com.vishva.CricInfo.service;

import com.vishva.CricInfo.entity.InningEntity;
import com.vishva.CricInfo.entity.PlayerEntity;

public interface CricDataService {

    void saveInning(InningEntity inningEntity);
    void savePlayer(PlayerEntity playerEntity);
}
