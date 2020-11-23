package com.vishva.CricInfo.service;

import com.vishva.CricInfo.entity.DeliveryEntity;
import com.vishva.CricInfo.entity.InningEntity;
import com.vishva.CricInfo.entity.OverEntity;
import com.vishva.CricInfo.entity.PlayerEntity;

public interface CricDataService {

    void saveDelivery(DeliveryEntity deliveryEntity);
    void saveInning(InningEntity inningEntity);
    void saveOver(OverEntity overEntity);
    void savePlayer(PlayerEntity playerEntity);
}
