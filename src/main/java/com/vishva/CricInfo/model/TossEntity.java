package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "toss")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class TossEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toss_id")
    private int tossId;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "toss")
//    private MatchEntity match;

    private String decision;

    private String winner;
}
