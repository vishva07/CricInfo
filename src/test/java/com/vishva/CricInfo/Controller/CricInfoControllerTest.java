package com.vishva.CricInfo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishva.CricInfo.CricInfoApplication;
import com.vishva.CricInfo.controller.CricInfoController;
import com.vishva.CricInfo.exchange.MatchRequest;
import com.vishva.CricInfo.repository.MatchRepository;
import com.vishva.CricInfo.service.impl.CricDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CricInfoApplication.class})
public class CricInfoControllerTest {

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @Mock
    private CricDataServiceImpl cricDataService;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private CricInfoController cricInfoController;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cricInfoController).build();
    }

    @Test
    public void invalidStartDateBadRequest() throws Exception {
        MatchRequest matchRequest = new MatchRequest("Dubai International Cricket Stadium", new Date(30-11-2014), new Date(2014-11-30));
        String jsonRequest = objectMapper.writeValueAsString(matchRequest);
        mockMvc.perform(post("/match").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidEndDateBadRequest() throws Exception {
        MatchRequest matchRequest = new MatchRequest("Dubai International Cricket Stadium", new Date(2016-10-07), new Date(07-10-2016));
        String jsonRequest = objectMapper.writeValueAsString(matchRequest);
        mockMvc.perform(post("/match").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
