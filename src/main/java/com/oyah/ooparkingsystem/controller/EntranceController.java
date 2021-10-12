package com.oyah.ooparkingsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.datamodel.EntranceData;
import com.oyah.ooparkingsystem.service.EntranceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EntranceController {

    @Autowired
    private EntranceService entranceService;

    @GetMapping("/entrances")
    public ResponseEntity<List<EntranceData>> getAll() {
        List<Entrance> entrances = entranceService.getAll();
        if (entrances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EntranceData> entranceDataList = entrances.stream()
            .map(e -> entityToData(e))
            .collect(Collectors.toList());
        return new ResponseEntity<>(entranceDataList, HttpStatus.OK);
    }

    @GetMapping("/entrances/{entranceId}")
    public ResponseEntity<EntranceData> update(@PathVariable Long entranceId) {
        Optional<Entrance> entranceOptional = entranceService.findById(entranceId);
        if (entranceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entityToData(entranceOptional.get()), HttpStatus.OK);
    }

    @PostMapping("/entrances")
    public ResponseEntity<EntranceData> create(@Valid @RequestBody EntranceData entranceData) {
        Entrance entranceRequest = dataToEntity(entranceData);
        Entrance newEntrance = entranceService.create(entranceRequest);
        return new ResponseEntity<>(entityToData(newEntrance), HttpStatus.CREATED);
    }

    @PutMapping("/entrances/{id}")
    public ResponseEntity<EntranceData> update(@PathVariable Long id, @Valid @RequestBody EntranceData entranceData) {
        Entrance entranceRequest = dataToEntity(entranceData);
        Entrance updatedEntrance = entranceService.update(id, entranceRequest);
        return new ResponseEntity<>(entityToData(updatedEntrance), HttpStatus.OK);
    }

    private EntranceData entityToData(Entrance entrance) {
        return EntranceData.builder()
            .id(entrance.getId())
            .name(entrance.getName())
            .build();
    }

    private Entrance dataToEntity(EntranceData entranceData) {
        return Entrance.builder()
            .id(entranceData.getId())
            .name(entranceData.getName())
            .build();
    }
}
