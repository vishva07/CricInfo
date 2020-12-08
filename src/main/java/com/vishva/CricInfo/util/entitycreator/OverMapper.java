package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Runs;
import com.vishva.CricInfo.model.DeliveryEntity;
import com.vishva.CricInfo.model.OverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OverMapper {

    @Autowired
    private DeliveryMapper deliveryMapper;

    public List<OverEntity> createOver(List<Delivery> deliveryArray) {
        List<DeliveryEntity> deliveryEntities = new ArrayList<>();
        List<OverEntity> overEntityList = new ArrayList<>();
        int preOver = 0, extrasInOver = 0, runsInOver=0;
        for(Delivery delivery : deliveryArray) {
            DeliveryEntity deliveryEntity = deliveryMapper.createDelivery(delivery);
            int[] deliveryDetails = getDeliveryDetails(delivery.getDeliveryName());
            if(deliveryDetails != null) {
                int currOver = deliveryDetails[0];
                if(currOver > preOver) {
                    OverEntity overEntity = new OverEntity();
                    overEntity.setRuns(runsInOver);
                    overEntity.setExtras(extrasInOver);
                    overEntity.setDeliveryEntityList(deliveryEntities);
                    overEntityList.add(overEntity);
                }
                else{
                    Runs runs = delivery.getRuns();
                    extrasInOver += runs.getExtras();
                    runsInOver += runs.getTotal();
                    deliveryEntities.add(deliveryEntity);
                }
            }
        }
        return overEntityList;
    }

    private int[] getDeliveryDetails(String delivery) {
        Pattern pattern = Pattern.compile("^(\\d*)\\.?(\\d)$");
        Matcher matcher = pattern.matcher(delivery);
        if (matcher.find()) {
            return new int[]{
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            };
        }
        return null;
    }
}
