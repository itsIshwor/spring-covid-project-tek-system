package com.hackerrank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.service.CovidService;

@RestController
@RequestMapping("/covid")
public class CovidController {
    private final CovidService covidService;

    @Autowired
    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Covid> getAllCovid() {
        return covidService.getAllCovid();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Covid createCovid(@RequestBody Covid covid) {
        return covidService.createNewCovid(covid);
    }

    @GetMapping("/total")
    public ResponseEntity<Object> getTotalBy(@RequestParam String by) {
        return ResponseEntity.ok("total:" + covidService.totalBy(by));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Covid> findCovidById(@PathVariable Long id) {
        return ResponseEntity.ok(covidService.getCovidById(id));

    }

    @GetMapping("/top5")
    public ResponseEntity<List<Covid>> getTopFiveBy(@RequestParam String by) {
        return ResponseEntity.ok(covidService.top5By(by));
    }

}