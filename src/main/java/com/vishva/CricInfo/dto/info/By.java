package com.vishva.CricInfo.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class By {
    private int runs;
    private int wickets;
    private int innings;
}
