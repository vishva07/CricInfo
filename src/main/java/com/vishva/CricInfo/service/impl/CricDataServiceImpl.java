package com.vishva.CricInfo.service.impl;

import com.vishva.CricInfo.exchange.*;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.model.TossEntity;
import com.vishva.CricInfo.repository.MatchRepository;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

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
    public List<MatchEntity> getMatchEntities(MatchRequest matchRequest) {

        return matchRepository.getMatchEntity(matchRequest.getVenue(), matchRequest.getStartDate(), matchRequest.getEndDate());
    }

    @Override
    public List<List<String>> getPlayerOfMatch(PlayerOfMatchRequest playerOfMatchRequest) {

        return matchRepository.getPlayerOfMatch(playerOfMatchRequest.getMatch_type(), playerOfMatchRequest.getStartDate(), playerOfMatchRequest.getTeams());
    }

    @Override
    public  List<OutcomeEntity> getOutcome(OutcomeRequest outcomeRequest) {

        return matchRepository.getOutcome(outcomeRequest.getVenue(), outcomeRequest.getMatchType());
    }

    @Override
    public List<TossEntity> getToss(TossRequest tossRequest) {

        return matchRepository.getToss(tossRequest.getCity(), tossRequest.getVenue());
    }

    @Override
    public Set<PlayerEntity> getTeam(TeamRequest teamRequest) {

        return matchRepository.getTeam(teamRequest.getVenue(), teamRequest.getMatchType());
    }
}
