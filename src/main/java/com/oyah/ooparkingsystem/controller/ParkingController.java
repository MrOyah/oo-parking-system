package com.oyah.ooparkingsystem.controller;

import java.util.List;

import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.service.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public List<Parking> retriveParkingList() {
        return parkingService.findAll();
    }

    @GetMapping("/parking/{id}")
    public Parking retrieveParking(@PathVariable long id) {
        return parkingService.findById(id);
    }

    @PostMapping("/parking")
    public void createParking(@RequestBody Parking parking) {
        Parking savedParking = parkingService.save(parking);
    }
}
