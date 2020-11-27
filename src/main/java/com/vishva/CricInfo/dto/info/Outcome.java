package com.vishva.CricInfo.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Outcome {
    private By by;
    private String winner;
    private String method;
    private String result;
}
