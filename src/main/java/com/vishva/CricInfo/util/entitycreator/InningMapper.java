package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.model.OverEntity;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.dto.innings.Runs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InningMapper {

    @Autowired
    private OverMapper overMapper;

    public InningEntity createInning(Inning inn, List<Delivery> deliveryArray) {
        int ballsInInning = deliveryArray.size(), extrasInInning = 0, runsInInning = 0, wicketsInInning = 0;
        for (Delivery del : deliveryArray) {
            Runs runs = del.getRuns();
            extrasInInning += runs.getExtras();
            runsInInning += runs.getTotal();
            if (del.getWicket() != null)
                wicketsInInning++;
        }

        InningEntity inningEntity = new InningEntity();
        inningEntity.setTeam(inn.getTeam());
        inningEntity.setBalls(ballsInInning);
        inningEntity.setExtras(extrasInInning);
        inningEntity.setTotal_score(runsInInning);
        inningEntity.setWickets(wicketsInInning);
        List<OverEntity> overEntityList = overMapper.createOver(deliveryArray);
        inningEntity.setOvers(overEntityList);
        return inningEntity;
    }
}
