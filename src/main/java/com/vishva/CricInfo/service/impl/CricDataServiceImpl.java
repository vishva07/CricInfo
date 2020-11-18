package com.vishva.CricInfo.service.impl;

import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Extras;
import com.vishva.CricInfo.model.Inning;
import com.vishva.CricInfo.model.Over;
import com.vishva.CricInfo.repository.DeliveryRepository;
import com.vishva.CricInfo.repository.ExtrasRepository;
import com.vishva.CricInfo.repository.InningRepository;
import com.vishva.CricInfo.repository.OverRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CricDataServiceImpl implements CricDataService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ExtrasRepository extrasRepository;

    @Autowired
    InningRepository inningRepository;

    @Autowired
    OverRepository overRepository;

    @Override
    public void saveDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public void saveExtras(Extras extras) {
        extrasRepository.save(extras);
    }

    @Override
    public void saveInning(Inning inning) {
        inningRepository.save(inning);
    }

    @Override
    public void saveOver(Over over) {
        overRepository.save(over);
    }
}
