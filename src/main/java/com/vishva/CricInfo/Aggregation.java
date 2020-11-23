package com.vishva.CricInfo;

import com.vishva.CricInfo.entity.DeliveryEntity;
import com.vishva.CricInfo.entity.InningEntity;
import com.vishva.CricInfo.entity.OverEntity;
import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Inning;
import com.vishva.CricInfo.model.Runs;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import com.vishva.CricInfo.util.CreateEntity;
import java.util.ArrayList;
import java.util.List;

public class Aggregation {

    public static InningEntity populateData(Inning inn, List<Delivery> deliveryArray, PlayerRepository playerRepository, CricDataService cricDataService) {
        List<DeliveryEntity> deliveryEntities = new ArrayList<>();
        List<OverEntity> overEntities = new ArrayList<>();
        double pre = 0.0;
        int extras=0, t_runs=0, balls=0, extinn=0, runsinn=0, wicket=0;
        for (Delivery del : deliveryArray) {
            double curr = Double.parseDouble(del.getDeliveryName());
            DeliveryEntity deliveryEntity = CreateEntity.delivery(del, playerRepository);
            if (pre + 0.1 == curr) {
                pre = curr;
                Runs runs = del.getRuns();
                extras += runs.getExtras();
                t_runs += runs.getTotal();
                deliveryEntities.add(deliveryEntity);
            } else {
                pre = curr;
                OverEntity overEntity = CreateEntity.over(del, extras, t_runs, deliveryEntities);
                overEntities.add(overEntity);
                extras = 0;
                t_runs = 0;
                deliveryEntities = new ArrayList<>();
            }
            Runs runs = del.getRuns();
            extinn += runs.getExtras();
            runsinn += runs.getTotal();
            if (del.getWicket() != null)
                wicket++;
        }
        balls = deliveryArray.size();
        InningEntity inningEntity = CreateEntity.inning(inn, balls, extinn, runsinn, wicket, overEntities);
        return inningEntity;
    }
}

