package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Table(name = "delivery")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String batsman;
    private String bowler;
    private String non_striker;
    private int batsman_runs;
    @OneToOne(cascade = CascadeType.ALL)
    private ExtrasEntity extrasEntity;
    private int total_runs;
}
