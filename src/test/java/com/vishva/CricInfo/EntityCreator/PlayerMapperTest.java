package com.vishva.CricInfo.EntityCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.PlayerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerMapperTest {

    Match match = new Match();

    PlayerMapper playerMapper = new PlayerMapper();

    @BeforeEach
    public void setup() throws Exception {
        String yaml = new String(Files.readAllBytes(Paths.get("src/test/resources/sample/playerTest.yaml")));
        String json = YamlConverter.convertYamlToJson(yaml);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Inning.class, new InningDeserializer());
        Gson gson = builder.create();
        match = gson.fromJson(json, Match.class);
    }

    @Test
    public void checkPlayerEntity() {
        Set<PlayerEntity> playerEntities = playerMapper.getPlayerEntities(match.getInfo().getTeams(), match.getInnings());
        assertEquals(7, playerEntities.size());
        PlayerEntity playerEntity1 = new PlayerEntity("Ahmed Shehzad", "Pakistan", null);
        PlayerEntity playerEntity2 = new PlayerEntity("MG Johnson", "Australia", null);
        PlayerEntity playerEntity3 = new PlayerEntity("Mohammad Hafeez", "Pakistan", null);
        PlayerEntity playerEntity4 = new PlayerEntity("Azhar Ali", "Pakistan", null);
        PlayerEntity playerEntity5 = new PlayerEntity("CJL Rogers", "Australia", null);
        PlayerEntity playerEntity6 = new PlayerEntity("DA Warner", "Australia", null);
        PlayerEntity playerEntity7 = new PlayerEntity("Rahat Ali", "Pakistan", null);
        for(PlayerEntity p : playerEntities) {
            if(p.getName().equals(playerEntity1.getName()))
                assertEquals(playerEntity1.getTeam(), p.getTeam());
            else if(p.getName().equals(playerEntity2.getName()))
                assertEquals(playerEntity2.getTeam(), p.getTeam());
            else if(p.getName().equals(playerEntity3.getName()))
                assertEquals(playerEntity3.getTeam(), p.getTeam());
            else if(p.getName().equals(playerEntity4.getName()))
                assertEquals(playerEntity4.getTeam(), p.getTeam());
            else if(p.getName().equals(playerEntity5.getName()))
                assertEquals(playerEntity5.getTeam(), p.getTeam());
            else if(p.getName().equals(playerEntity6.getName()))
                assertEquals(playerEntity6.getTeam(), p.getTeam());
            else {
                assertEquals(playerEntity7.getTeam(), p.getTeam());
                assertEquals(playerEntity7.getName(), p.getName());
            }
        }
    }
}
