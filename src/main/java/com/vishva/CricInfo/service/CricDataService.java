package com.vishva.CricInfo.service;


import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Extras;
import com.vishva.CricInfo.model.Inning;
import com.vishva.CricInfo.model.Over;

public interface CricDataService {

    void saveDelivery(Delivery delivery);
    void saveExtras(Extras extras);
    void saveInning(Inning inning);
    void saveOver(Over over);
}
