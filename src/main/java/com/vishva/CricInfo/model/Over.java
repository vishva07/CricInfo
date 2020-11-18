package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "over")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class Over {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int runs;
    private int extras;
    //@OneToMany(targetEntity = Delivery.class)
    //@JoinColumn(name = "delivery_id")
    private long deliveries;

}
