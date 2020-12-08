package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Extras;
import com.vishva.CricInfo.dto.innings.Runs;
import com.vishva.CricInfo.model.DeliveryEntity;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    /*@Autowired
    private CricDataService cricDataService;

    @Autowired
    private PlayerRepository playerRepository;*/

    @Autowired
    private ExtrasMapper extrasMapper;

    public DeliveryEntity createDelivery(Delivery delivery) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        /*if(getPlayerId(delivery.getBatsman()) != 0) {
            deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman()));
        }
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
        }*/

        deliveryEntity.setBatsman(delivery.getBatsman());
        deliveryEntity.setBowler(delivery.getBowler());
        deliveryEntity.setNon_striker(delivery.getNon_striker());

        Runs runs = delivery.getRuns();
        Extras extras = delivery.getExtras();
        if(extras != null)
            deliveryEntity.setExtrasEntity(extrasMapper.createExtras(delivery));
        deliveryEntity.setTotal_runs(runs.getTotal());
        deliveryEntity.setBatsman_runs(runs.getBatsman());
        return deliveryEntity;
    }

    /*public int getPlayerId(String player) {
        if(playerRepository != null) {
            PlayerEntity playerEntity = playerRepository.findByPlayerName(player);
            if(playerEntity != null)
                return playerEntity.getId();
        }
        return 0;
    }*/
}
