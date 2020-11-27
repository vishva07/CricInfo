package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "team")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(unique = true)
    private String name;

    private String code;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "match_id")
//    private MatchEntity match;

}
