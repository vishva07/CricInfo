package com.vishva.CricInfo.util;

import com.google.gson.JsonDeserializer;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Inning;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InningDeserializer implements JsonDeserializer<Inning> {

    private final static Gson gson = new Gson();
    private final static Type mapType = new TypeToken<Map<String, Delivery>>() {}.getType();

    @Override
    public Inning deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        List<Delivery> deliveries = new ArrayList<>();

        String team = jsonObject.get("team").getAsString();
        JsonArray deliveryArray = jsonObject.getAsJsonArray("deliveries");

        for(JsonElement deliveryElement : deliveryArray) {
            Map<String, Delivery> deliveryMap = gson.fromJson(deliveryElement, mapType);
            deliveryMap.forEach((deliveryNumber, delivery) -> {
                delivery.setDeliveryName(deliveryNumber);
                deliveries.add(delivery);
            });
        }
        return new Inning(team, deliveries);
    }
}