package com.vishva.CricInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonDeserializer {
    public static JsonObject deserializeJson(String json) {
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(json);
        return rootElement.getAsJsonObject();
    }
}