package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.info.Info;
import com.vishva.CricInfo.dto.info.Outcome;
import com.vishva.CricInfo.dto.info.Toss;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.TossEntity;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class MatchMapper {

    private MatchEntity matchEntity;

    public MatchEntity createMatchEntityFromData(Match match) {
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
        //matchEntity.setInningEntities(inningEntities);
        return matchEntity;
    }

    public void setDates(List<Date> dates) {
        matchEntity.setStartDate(dates.get(0));
        matchEntity.setEndDate(dates.get(dates.size() - 1));
    }

    public OutcomeEntity getOutcomeFromInfo(Outcome outcome) {
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

    public TossEntity getTossFromInfo(Toss toss) {
        TossEntity tossEntity = new TossEntity();
        tossEntity.setWinner(toss.getWinner());
        tossEntity.setDecision(toss.getDecision());
        return tossEntity;
    }
}
