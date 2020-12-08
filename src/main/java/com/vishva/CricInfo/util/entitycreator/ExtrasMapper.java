package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Extras;
import com.vishva.CricInfo.model.ExtrasEntity;
import org.springframework.stereotype.Component;

@Component
public class ExtrasMapper {

    public ExtrasEntity createExtras(Delivery delivery) {
        ExtrasEntity extrasEntity = new ExtrasEntity();
        Extras extras = delivery.getExtras();
        extrasEntity.setWides(extras.getWides());
        extrasEntity.setNoballs(extras.getNoballs());
        extrasEntity.setLegbyes(extras.getLegbyes());
        return extrasEntity;
    }
}
