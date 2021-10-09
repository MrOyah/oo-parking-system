package com.oyah.ooparkingsystem.controller;

import java.util.List;

import com.oyah.ooparkingsystem.entity.datamodel.EntranceData;
import com.oyah.ooparkingsystem.service.EntranceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EntranceController {

    @Autowired
    private EntranceService entranceService;

    @GetMapping("/entrances")
    public ResponseEntity<List<EntranceData>> retriveEntrances() {
        List<EntranceData> entrances = entranceService.getAll();
        if (entrances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entrances, HttpStatus.OK);
    }
    
}
