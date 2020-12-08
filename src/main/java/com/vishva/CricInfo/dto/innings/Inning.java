package com.vishva.CricInfo.dto.innings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inning {

    private String team;
    private List<Delivery> deliveries;
}
