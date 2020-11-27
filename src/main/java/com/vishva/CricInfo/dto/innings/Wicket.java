package com.vishva.CricInfo.dto.innings;

import lombok.Data;

import java.util.List;

@Data
public class Wicket {

    private List<String> fielders;
    private String kind;
    private String player_out;
}
