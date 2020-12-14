package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "Player", indexes = {@Index(name = "playerIndex", columnList = "name")})
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private String team;

    private String type;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "player_match",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id"))
    private Set<MatchEntity> matchEntitySet = new HashSet<>();

    public PlayerEntity(String playerName, String team, String type) {
        this.name = playerName;
        this.team = team;
        this.type = type;
    }
}
