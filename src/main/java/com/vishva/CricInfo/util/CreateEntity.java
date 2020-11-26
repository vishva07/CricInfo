package com.vishva.CricInfo.util;

import com.vishva.CricInfo.entity.*;
import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Extras;
import com.vishva.CricInfo.model.Inning;
import com.vishva.CricInfo.model.Runs;
import com.vishva.CricInfo.repository.PlayerRepository;
import java.util.List;

public class CreateEntity {

    public static DeliveryEntity delivery(Delivery delivery, PlayerRepository playerRepository) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        if(getPlayerId(delivery.getBatsman(), playerRepository) != 0)
            deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman(), playerRepository));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getBatsman());
            playerRepository.save(playerEntity);
            deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman(), playerRepository));
        }
        if(getPlayerId(delivery.getBowler(), playerRepository) != 0)
            deliveryEntity.setBowler(getPlayerId(delivery.getBowler(), playerRepository));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getBowler());
            playerRepository.save(playerEntity);
            deliveryEntity.setBowler(getPlayerId(delivery.getBowler(), playerRepository));
        }
        if(getPlayerId(delivery.getNon_striker(), playerRepository) != 0)
            deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker(), playerRepository));
        else {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setPlayerName(delivery.getNon_striker());
            playerRepository.save(playerEntity);
            deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker(), playerRepository));
        }
        Runs runs = delivery.getRuns();
        Extras extras = delivery.getExtras();
        if(extras != null)
            deliveryEntity.setExtrasEntity(extras(delivery));
        deliveryEntity.setTotal_runs(runs.getTotal());
        deliveryEntity.setBatsman_runs(runs.getBatsman());
        return deliveryEntity;
    }

    public static InningEntity inning(Inning inning, int balls, int extinn, int runsinn, int wicket, List<OverEntity> overEntities) {
        InningEntity inningEntity = new InningEntity();
        inningEntity.setTeam(inning.getTeam());
        inningEntity.setBalls(balls);
        inningEntity.setExtras(extinn);
        inningEntity.setTotal_score(runsinn);
        inningEntity.setWickets(wicket);
        inningEntity.setOvers(overEntities);
        return inningEntity;
    }

    public static ExtrasEntity extras(Delivery delivery) {
        ExtrasEntity extrasEntity = new ExtrasEntity();
        Extras extras = delivery.getExtras();
        if(extras.getWides() != 0)
            extrasEntity.setWides(extras.getWides());
        if(extras.getNoballs() != 0)
            extrasEntity.setNoballs(extras.getNoballs());
        if(extras.getLegbyes() != 0)
            extrasEntity.setLegbyes(extras.getLegbyes());
        return extrasEntity;
    }

    public static OverEntity over(int extras, int t_runs, List<DeliveryEntity> deliveryEntities) {
        OverEntity overEntity = new OverEntity();
        overEntity.setRuns(t_runs);
        overEntity.setExtras(extras);
        overEntity.setDeliveryEntityList(deliveryEntities);
        return overEntity;
    }

    public static int getPlayerId(String player, PlayerRepository playerRepository) {
        if(playerRepository != null) {
            PlayerEntity playerEntity = playerRepository.findByPlayerName(player);
            if(playerEntity != null)
                return playerEntity.getId();
        }
        return 0;
    }
}
