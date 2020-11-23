package com.vishva.CricInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "inning")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class InningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int balls;
    private String team;
    /*@OneToMany(mappedBy = "inning")
    private List<Over> overs;*/
    private int total_score;
    private int extras;
    private int wickets;
}
