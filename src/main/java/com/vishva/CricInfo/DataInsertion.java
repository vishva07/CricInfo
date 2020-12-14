package com.vishva.CricInfo;

import com.google.gson.Gson;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import com.vishva.CricInfo.util.entitycreator.InningMapper;
import com.vishva.CricInfo.util.entitycreator.MatchMapper;
import com.vishva.CricInfo.util.entitycreator.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DataInsertion {

    @Autowired
    private CricDataService cricDataService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InningMapper inningMapper;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private Gson gson;

    private final ReentrantLock reentrantLock = new ReentrantLock();


    public void insertData(String json) {
        Match match = gson.fromJson(json, Match.class);
        List<HashMap<String, Inning>> inningArray = match.getInnings();
        List<InningEntity> inningEntities = new ArrayList<>();
        for (HashMap<String, Inning> inningObject : inningArray) {
            Inning inn = inningObject.values().iterator().next();
            List<Delivery> deliveryArray = inn.getDeliveries();
            InningEntity inningEntity = inningMapper.createInning(inn, deliveryArray);
            inningEntities.add(inningEntity);
        }
        MatchEntity matchEntity = matchMapper.createMatchEntityFromData(match);
        matchEntity.setInningEntities(inningEntities);

        Set<PlayerEntity> playerEntities = playerMapper.getPlayerEntities(match.getInfo().getTeams(),
                match.getInnings());

        reentrantLock.lock();
        playerEntities.forEach(playerEntity -> {
            PlayerEntity playerEntityFromDb = playerRepository.findByName(playerEntity.getName());
            if(playerEntityFromDb != null) {
                playerEntityFromDb.getMatchEntitySet().add(matchEntity);
                matchEntity.getPlayerEntitySet().add(playerEntityFromDb);
                cricDataService.saveMatch(matchEntity);
                cricDataService.savePlayer(playerEntityFromDb);
            }
            else {
                playerEntity.getMatchEntitySet().add(matchEntity);
                matchEntity.getPlayerEntitySet().add(playerEntity);
                cricDataService.saveMatch(matchEntity);
                cricDataService.savePlayer(playerEntity);
            }
        });
        reentrantLock.unlock();
    }
}