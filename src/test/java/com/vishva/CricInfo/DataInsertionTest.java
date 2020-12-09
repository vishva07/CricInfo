package com.vishva.CricInfo;

import com.google.gson.Gson;
import com.vishva.CricInfo.model.InningEntity;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.InningMapper;
import com.vishva.CricInfo.util.entitycreator.MatchMapper;
import com.vishva.CricInfo.util.entitycreator.PlayerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.doReturn;

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

    @BeforeEach
    public void setup() throws Exception {
        String yaml = new String(Files.readAllBytes(Paths.get("src/test/resources/sample/727927.yaml")));
        json = YamlConverter.convertYamlToJson(yaml);
    }

    @Test
    public void checkDataInserted() {

        /*InningEntity inningEntity = new InningEntity(870, "Pakistan", 678,);

        doReturn().when(inningMapper).createInning();
        doReturn().when(matchMapper).createMatchEntityFromData();
        doReturn().when(playerMapper).getPlayerEntities();
        dataInsertion.insertData(json);*/
    }
}
