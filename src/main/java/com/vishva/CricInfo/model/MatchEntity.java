package com.vishva.CricInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.*;

@Data
@Table(name = "match")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "list-array",
        typeClass = ListArrayType.class)
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    private String city;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private String category;

    @Column(name = "match_type")
    private String matchType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_id")
    private OutcomeEntity outcome;

    @Type(type = "list-array")
    @Column(name = "player_of_match",
            columnDefinition = "text[]")
    private List<String> playerOfMatch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "toss_id")
    private TossEntity toss;

    @Type(type = "list-array")
    @Column(name = "umpires",
            columnDefinition = "text[]")
    private List<String> umpires;

    private String venue;

    @ToString.Exclude
    @ManyToMany(mappedBy = "matchEntitySet")
    private Set<PlayerEntity> playerEntitySet = new HashSet<>();

    private String tournament;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", nullable = false)
    private List<InningEntity> inningEntities = new ArrayList<>();


    @Type(type = "list-array")
    @Column(
            name = "teams",
            columnDefinition = "text[]"
    )
    private List<String> teams;

}
