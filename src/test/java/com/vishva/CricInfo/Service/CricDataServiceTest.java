package com.vishva.CricInfo.Service;

import com.vishva.CricInfo.repository.MatchRepository;
import com.vishva.CricInfo.service.impl.CricDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CricDataServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private CricDataServiceImpl cricDataService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void matchSavedSuccessfully() {

    }

}
