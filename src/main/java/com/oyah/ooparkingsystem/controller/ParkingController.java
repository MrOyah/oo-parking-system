package com.oyah.ooparkingsystem.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public List<Parking> retriveParkingList() {
        return parkingService.findAll();
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<Parking> retrieveParking(@PathVariable long id) {
        Parking parking = parkingService.findById(id);
        if (parking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking id not found.");
        }
        return ResponseEntity.ok(parking);
    }

    @PostMapping("/parking")
    public ResponseEntity<Object> createParking(@RequestBody ParkingEntryData parkingEntryData) {
        Parking savedParking = parkingService.saveEntry(parkingEntryData);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("{id}")
            .buildAndExpand(savedParking.getId())
            .toUri();
        
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/parking/{id}/unpark") 
    public ParkingData unpark(@PathVariable long id) {
        ParkingData parkingData = parkingService.unpark(id);
        return parkingData;
    }
}
