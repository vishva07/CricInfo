package com.vishva.CricInfo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vishva.CricInfo.dto.info.Info;
import com.vishva.CricInfo.dto.innings.Inning;
import lombok.Data;
import java.util.HashMap;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    private Info info;
    private List<HashMap<String, Inning>> innings;
}
