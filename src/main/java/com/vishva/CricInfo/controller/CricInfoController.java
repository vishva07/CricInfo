package com.vishva.CricInfo.controller;

import com.vishva.CricInfo.exchange.CricInfoRequest;
import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.service.CricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> getMatch(@Valid @RequestBody CricInfoRequest cricInfoRequest) {

        MatchEntity matchEntity = cricDataService.getMatch(cricInfoRequest);

        return ResponseEntity.ok().body(cricInfoRequest);
    }
}
