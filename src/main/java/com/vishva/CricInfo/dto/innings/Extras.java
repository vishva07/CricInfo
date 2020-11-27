package com.vishva.CricInfo.dto.innings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Extras {

    private int wides;
    private int legbyes;
    private int noballs;
}
