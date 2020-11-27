package com.vishva.CricInfo.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Toss {
    private String decision;
    private String winner;
}
