package com.vishva.CricInfo.EntityCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.Match;
import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.DeliveryEntity;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import com.vishva.CricInfo.util.entitycreator.DeliveryMapper;
import com.vishva.CricInfo.util.entitycreator.ExtrasMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DeliveryMapperTest {

    Match match = new Match();

    @InjectMocks
    private DeliveryMapper deliveryMapper;

    @Mock
    private ExtrasMapper extrasMapper;

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
    public void checkDeliveryEntity() {
        List<HashMap<String, Inning>> inningList = match.getInnings();
        Inning inning = inningList.get(0).values().iterator().next();
        Delivery delivery = inning.getDeliveries().get(2);
        doReturn(null).when(extrasMapper).createExtras(any(Delivery.class));
        DeliveryEntity deliveryEntity = deliveryMapper.createDelivery(delivery);
        assertEquals(0, deliveryEntity.getBatsman_runs());
        assertEquals(1, deliveryEntity.getTotal_runs());
        assertEquals("Ahmed Shehzad", deliveryEntity.getBatsman());
        assertEquals("MG Johnson", deliveryEntity.getBowler());
        assertEquals("Mohammad Hafeez", deliveryEntity.getNon_striker());
        verify(extrasMapper, times(1)).createExtras(delivery);
        verifyNoMoreInteractions(extrasMapper);
    }
}
