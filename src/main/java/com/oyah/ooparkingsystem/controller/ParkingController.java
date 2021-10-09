package com.oyah.ooparkingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingEntryData;
import com.oyah.ooparkingsystem.service.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public ResponseEntity<List<ParkingData>> retriveParkingList() {
        List<ParkingData> parkingData = parkingService.getAll();
        if (parkingData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(parkingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<ParkingData> retrieveParking(@PathVariable long id) {
        ParkingData parkingData = parkingService.getById(id);
        if (parkingData == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking id not found.");
        }
        return new ResponseEntity<>(parkingData, HttpStatus.OK);
    }

    @PostMapping("/parking")
    public ResponseEntity<Parking> createParking(@Valid @RequestBody ParkingEntryData parkingEntryData) {
        Parking parking = parkingService.saveEntry(parkingEntryData);
        return new ResponseEntity<>(parking, HttpStatus.CREATED);
    }

    @PostMapping("/parking/{id}/unpark") 
    public ResponseEntity<ParkingData> unpark(@PathVariable long id) {
        ParkingData parkingData = parkingService.unpark(id);
        return new ResponseEntity<>(parkingData, HttpStatus.OK);
    }
}
