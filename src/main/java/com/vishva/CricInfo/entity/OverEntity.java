package com.vishva.CricInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "over")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class OverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int runs;
    private int extras;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DeliveryEntity> deliveryEntityList = new ArrayList<>();
    /*@OneToMany(mappedBy = "over")
    private List<Delivery> deliveries;*/
    /*@ManyToOne
    @JoinColumn(name = "inning_id")
    private Inning inning;*/
}
