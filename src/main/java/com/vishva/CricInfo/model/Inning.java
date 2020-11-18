package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "inning")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class Inning {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int balls;
    private String team;
    private Long overs;
    private int total_score;
    private int extras;
    private int wickets;
}
