package com.vishva.CricInfo.service.impl;

import com.vishva.CricInfo.exchange.CricInfoRequest;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.repository.MatchRepository;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CricDataServiceImpl implements CricDataService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void saveMatch(MatchEntity matchEntity) {

        matchRepository.save(matchEntity);
    }

    @Override
    public void savePlayer(PlayerEntity playerEntity) {

        playerRepository.save(playerEntity);
    }

    @Override
    public MatchEntity getMatch(CricInfoRequest cricInfoRequest) {

        return matchRepository.getMatchEntity(cricInfoRequest.getVenue(), cricInfoRequest.getStartDate(), cricInfoRequest.getEndDate());
    }
}
