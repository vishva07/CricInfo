/*package com.vishva.CricInfo.util;

import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.info.Info;
import com.vishva.CricInfo.dto.info.Outcome;
import com.vishva.CricInfo.dto.info.Toss;
import com.vishva.CricInfo.model.*;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Extras;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.dto.innings.Runs;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class CreateEntity {

    @Autowired
    CricDataService cricDataService;

    @Autowired
    PlayerRepository playerRepository;

    public DeliveryEntity delivery(Delivery delivery) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        if(getPlayerId(delivery.getBatsman()) != 0)
            deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman()));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getBatsman());
            cricDataService.savePlayer(playerEntity);
            deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman()));
        }
        if(getPlayerId(delivery.getBowler()) != 0)
            deliveryEntity.setBowler(getPlayerId(delivery.getBowler()));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getBowler());
            cricDataService.savePlayer(playerEntity);
            deliveryEntity.setBowler(getPlayerId(delivery.getBowler()));
        }
        if(getPlayerId(delivery.getNon_striker()) != 0)
            deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker()));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getNon_striker());
            cricDataService.savePlayer(playerEntity);
            deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker()));
        }
        Runs runs = delivery.getRuns();
        Extras extras = delivery.getExtras();
        if(extras != null)
            deliveryEntity.setExtrasEntity(extras(delivery));
        deliveryEntity.setTotal_runs(runs.getTotal());
        deliveryEntity.setBatsman_runs(runs.getBatsman());
        return deliveryEntity;
    }

    public InningEntity inning(Inning inning, int balls, int extinn, int runsinn, int wicket, List<OverEntity> overEntities) {
        InningEntity inningEntity = new InningEntity();
        inningEntity.setTeam(inning.getTeam());
        inningEntity.setBalls(balls);
        inningEntity.setExtras(extinn);
        inningEntity.setTotal_score(runsinn);
        inningEntity.setWickets(wicket);
        inningEntity.setOvers(overEntities);
        return inningEntity;
    }

    public ExtrasEntity extras(Delivery delivery) {
        ExtrasEntity extrasEntity = new ExtrasEntity();
        Extras extras = delivery.getExtras();
        extrasEntity.setWides(extras.getWides());
        extrasEntity.setNoballs(extras.getNoballs());
        extrasEntity.setLegbyes(extras.getLegbyes());
        return extrasEntity;
    }

    public OverEntity over(int extras, int t_runs, List<DeliveryEntity> deliveryEntities) {
        OverEntity overEntity = new OverEntity();
        overEntity.setRuns(t_runs);
        overEntity.setExtras(extras);
        overEntity.setDeliveryEntityList(deliveryEntities);
        return overEntity;
    }

    public int getPlayerId(String player) {
        if(playerRepository != null) {
            PlayerEntity playerEntity = playerRepository.findByPlayerName(player);
            if(playerEntity != null)
                return playerEntity.getId();
        }
        return 0;
    }

    private MatchEntity matchEntity;

    public MatchEntity createMatchEntityFromData(Match match, List<InningEntity> inningEntities) {
        matchEntity = new MatchEntity();

        Info info = match.getInfo();
        List<HashMap<String, Inning>> innings = match.getInnings();
        matchEntity.setCity(info.getCity());
        setDates(info.getDates());
        matchEntity.setVenue(info.getVenue());
        matchEntity.setCategory(info.getCategory());
        matchEntity.setMatchType(info.getMatchType());
        matchEntity.setOutcome(getOutcomeFromInfo(info.getOutcome()));
        matchEntity.setPlayerOfMatch(info.getPlayerOfMatch());
        matchEntity.setToss(getTossFromInfo(info.getToss()));
        matchEntity.setUmpires(info.getUmpires());
        matchEntity.setVenue(info.getVenue());
        //matchEntity.setPlayerEntitySet(getPlayerEntities(info.getTeams(), innings));
        matchEntity.setInningEntities(inningEntities);
        return matchEntity;
    }

    private void setDates(List<Date> dates) {
        matchEntity.setStartDate(dates.get(0));
        matchEntity.setEndDate(dates.get(dates.size() - 1));
    }

    private OutcomeEntity getOutcomeFromInfo(Outcome outcome) {
        OutcomeEntity outcomeEntity = new OutcomeEntity();
        if(outcome.getBy() != null) {
            outcomeEntity.setRuns(outcome.getBy().getRuns());
            outcomeEntity.setWickets(outcome.getBy().getWickets());
            outcomeEntity.setInnings(outcome.getBy().getInnings());
        }
        outcomeEntity.setWinner(outcome.getWinner());
        outcomeEntity.setMethod(outcome.getMethod());
        outcomeEntity.setResult(outcome.getResult());
        return outcomeEntity;
    }

    private TossEntity getTossFromInfo(Toss toss) {
        TossEntity tossEntity = new TossEntity();
        tossEntity.setWinner(toss.getWinner());
        tossEntity.setDecision(toss.getDecision());
        return tossEntity;
    }
}
*/