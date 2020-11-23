package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery {

    private String deliveryName;
    private String batsman;
    private String bowler;
    private Extras extras;
    private String non_striker;
    private Runs runs;
    private Wicket wicket;
}
