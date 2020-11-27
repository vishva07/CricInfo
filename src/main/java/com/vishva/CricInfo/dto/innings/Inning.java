package com.vishva.CricInfo.dto.innings;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Inning {

    private String team;
    private List<Delivery> deliveries;
}
