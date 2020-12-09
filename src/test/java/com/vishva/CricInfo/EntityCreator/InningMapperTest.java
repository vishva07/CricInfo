package com.vishva.CricInfo.EntityCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Runs;
import com.vishva.CricInfo.util.entitycreator.InningMapper;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.OverMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class InningMapperTest {

    Match match = new Match();

    @InjectMocks
    private InningMapper inningMapper;

    @Mock
    private OverMapper overMapper;

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
    public void checkInningEntity() {
        List<HashMap<String, Inning>> inningList = match.getInnings();
        Inning inning = inningList.get(0).values().iterator().next();
        List<Integer> list = aggregator(inning.getDeliveries());
        doReturn(null).when(overMapper).createOver(inning.getDeliveries());
        InningEntity inningEntity = inningMapper.createInning(inning, inning.getDeliveries());
        assertEquals("Pakistan", inningEntity.getTeam()) ;
        assertEquals(list.get(0), inningEntity.getBalls());
        assertEquals(list.get(1), inningEntity.getTotal_score());
        assertEquals(list.get(2), inningEntity.getExtras());
        assertEquals(list.get(3), inningEntity.getWickets());
        verify(overMapper, times(1)).createOver(inning.getDeliveries());
        verifyNoMoreInteractions(overMapper);
    }

    private List<Integer> aggregator(List<Delivery> deliveries) {
        List<Integer> list = new ArrayList<>();
        int ballsInInning = deliveries.size(), extrasInInning = 0, runsInInning = 0, wicketsInInning = 0;
        for (Delivery del : deliveries) {
            Runs runs = del.getRuns();
            extrasInInning += runs.getExtras();
            runsInInning += runs.getTotal();
            if (del.getWicket() != null)
                wicketsInInning++;
        }
        list.add(ballsInInning);
        list.add(runsInInning);
        list.add(extrasInInning);
        list.add(wicketsInInning);
        return list;
    }
}
