package com.vishva.CricInfo.service.impl;

import com.vishva.CricInfo.entity.DeliveryEntity;
import com.vishva.CricInfo.entity.InningEntity;
import com.vishva.CricInfo.entity.OverEntity;
import com.vishva.CricInfo.entity.PlayerEntity;
import com.vishva.CricInfo.repository.DeliveryRepository;
import com.vishva.CricInfo.repository.InningRepository;
import com.vishva.CricInfo.repository.OverRepository;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CricDataServiceImpl implements CricDataService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    InningRepository inningRepository;

    @Autowired
    OverRepository overRepository;

    @Autowired
    PlayerRepository playerRepository;


    @Override
    public void saveDelivery(DeliveryEntity deliveryEntity) {

        deliveryRepository.save(deliveryEntity);
    }

    @Override
    public void saveInning(InningEntity inningEntity) {

        inningRepository.save(inningEntity);
    }

    @Override
    public void saveOver(OverEntity overEntity) {

        overRepository.save(overEntity);
    }

    @Override
    public void savePlayer(PlayerEntity playerEntity) {

        playerRepository.save(playerEntity);
    }
}
