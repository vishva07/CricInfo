package com.vishva.CricInfo.EntityCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.ExtrasEntity;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.ExtrasMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtrasMapperTest {

    Match match = new Match();

    ExtrasMapper extrasMapper = new ExtrasMapper();

    @BeforeEach
    public void setup() throws Exception {
        String yaml = new String(Files.readAllBytes(Paths.get("src/test/resources/sample/727927.yaml")));
        String json = YamlConverter.convertYamlToJson(yaml);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Inning.class, new InningDeserializer());
        Gson gson = builder.create();
        match = gson.fromJson(json, Match.class);
    }

    @Test
    public void checkExtrasEntity() {
        List<HashMap<String, Inning>> inningList = match.getInnings();
        Inning inning = inningList.get(0).values().iterator().next();
        Delivery delivery = inning.getDeliveries().get(2);
        ExtrasEntity extrasEntity = extrasMapper.createExtras(delivery);
        assertEquals(1, extrasEntity.getLegbyes());
        assertEquals(0, extrasEntity.getNoballs());
        assertEquals(0, extrasEntity.getWides());
    }
}
