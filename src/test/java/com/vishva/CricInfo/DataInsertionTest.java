package com.vishva.CricInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.InningMapper;
import com.vishva.CricInfo.util.entitycreator.MatchMapper;
import com.vishva.CricInfo.util.entitycreator.PlayerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DataInsertionTest {

    @Mock
    private CricDataService cricDataService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private InningMapper inningMapper;

    @Mock
    private MatchMapper matchMapper;

    @Mock
    private PlayerMapper playerMapper;

    @Mock
    private Gson gson;

    @InjectMocks
    private DataInsertion dataInsertion;

    String json;

    Match match = new Match();

    @BeforeEach
    public void setup() throws Exception {
        String yaml = new String(Files.readAllBytes(Paths.get("src/test/resources/sample/727927.yaml")));
        json = YamlConverter.convertYamlToJson(yaml);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Inning.class, new InningDeserializer());
        Gson gson = builder.create();
        match = gson.fromJson(json, Match.class);
    }

    @Test
    public void checkDataInserted() {
        InningEntity inningEntity = new InningEntity(1, 870, "Pakistan", null, 678, 16, 5);
        List<String> list1 = new ArrayList<>();
        list1.add("vishv");
        List<String> list2 = new ArrayList<>();
        list2.add("deepak");
        Set<PlayerEntity> playerEntities = new HashSet<>();
        playerEntities.add(new PlayerEntity("msd", "India", null));
        MatchEntity matchEntity = new MatchEntity( (long) 1, "Etah", new Date(2014-10-22), new Date(2014-10-26), "male", "test", null, list1, null, list2, "JLN", playerEntities, null, null);

        doReturn(match).when(gson).fromJson(json, Match.class);
        doReturn(inningEntity).when(inningMapper).createInning(match.getInnings().get(0).values().iterator().next(), match.getInnings().get(0).values().iterator().next().getDeliveries());
        doReturn(matchEntity).when(matchMapper).createMatchEntityFromData(match);
        doReturn(playerEntities).when(playerMapper).getPlayerEntities(match.getInfo().getTeams(), match.getInnings());
        doReturn(playerEntities.iterator().next()).when(playerRepository).findByName(playerEntities.iterator().next().getName());
        dataInsertion.insertData(json);
        verify(cricDataService).saveMatch(matchEntity);
    }
}
