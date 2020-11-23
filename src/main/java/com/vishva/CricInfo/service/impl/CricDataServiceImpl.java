package com.vishva.CricInfo.service.impl;

import com.vishva.CricInfo.entity.InningEntity;
import com.vishva.CricInfo.entity.PlayerEntity;
import com.vishva.CricInfo.repository.InningRepository;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CricDataServiceImpl implements CricDataService {

    @Autowired
    InningRepository inningRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void saveInning(InningEntity inningEntity) {

        inningRepository.save(inningEntity);
    }

    @Override
    public void savePlayer(PlayerEntity playerEntity) {

        playerRepository.save(playerEntity);
    }
}
