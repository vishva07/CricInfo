package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "outcome")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class OutcomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outcome_id")
    private int outcomeId;

    /*
    @Column(name = "win_by")
    @Enumerated(EnumType.STRING)
    private WinBy winBy;

    private Integer value;
     */
    private Integer runs;

    private Integer wickets;

    private Integer innings;

    private String method;

    private String result;

    private String winner;
}
