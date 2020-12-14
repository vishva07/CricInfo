package com.vishva.CricInfo.controller;

import com.vishva.CricInfo.exchange.*;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.model.TossEntity;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class CricInfoController {

    @Autowired
    private CricDataService cricDataService;

    @GetMapping("/home")
    public String index() {

        return "Welcome to CricInfo!";
    }

    @PostMapping("/match")
    public ResponseEntity<?> getMatch(@Valid @RequestBody MatchRequest matchRequest) {

        List<MatchEntity> matchEntities = cricDataService.getMatchEntities(matchRequest);

        return ResponseEntity.ok().body(matchEntities);
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> getOutcome(@RequestBody OutcomeRequest outcomeRequest) {

        List<OutcomeEntity> outcomeEntities = cricDataService.getOutcome(outcomeRequest);

        return ResponseEntity.ok().body(outcomeEntities);
    }

    @PostMapping("/toss")
    public ResponseEntity<?> getToss(@RequestBody TossRequest tossRequest) {

        List<TossEntity> tossEntities = cricDataService.getToss(tossRequest);

        return ResponseEntity.ok().body(tossEntities);
    }

    @PostMapping("/team")
    public ResponseEntity<?> getTeam(@RequestBody TeamRequest teamRequest) {

        Set<PlayerEntity> playerEntitySet = cricDataService.getTeam(teamRequest);

        return ResponseEntity.ok().body(playerEntitySet);
    }

    @PostMapping("/playerofmatch")
    public ResponseEntity<?> getPlayer(@Valid @RequestBody PlayerOfMatchRequest playerOfMatchRequest) {

        List<List<String>> playerOfMatch = cricDataService.getPlayerOfMatch(playerOfMatchRequest);

        return  ResponseEntity.ok().body(playerOfMatch);
    }
}
