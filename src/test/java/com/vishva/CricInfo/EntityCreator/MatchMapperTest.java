package com.vishva.CricInfo.EntityCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.TossEntity;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.MatchMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchMapperTest {

    Match match = new Match();

    MatchMapper matchMapper = new MatchMapper();

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
    public void checkMatchEntity() {
        List<HashMap<String, Inning>> inningList = match.getInnings();
        MatchEntity matchEntity = matchMapper.createMatchEntityFromData(match);
        assertEquals("Dubai International Cricket Stadium", matchEntity.getVenue());
        assertEquals("Test", matchEntity.getMatchType());
        assertEquals("male", matchEntity.getCategory());
    }

    @Test
    public void checkOutcomeEntity() {
        OutcomeEntity outcomeEntity = matchMapper.getOutcomeFromInfo(match.getInfo().getOutcome());
        assertEquals(221, outcomeEntity.getRuns());
        assertEquals("Pakistan", outcomeEntity.getWinner());
    }

    @Test
    public void checkTossEntity() {
        TossEntity tossEntity = matchMapper.getTossFromInfo(match.getInfo().getToss());
        assertEquals("Pakistan", tossEntity.getWinner());
        assertEquals("bat", tossEntity.getDecision());
    }
}
