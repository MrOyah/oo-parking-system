package com.oyah.ooparkingsystem.service;

import java.util.List;

import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.repository.ParkingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingService {
    
    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(Long id) {
        return parkingRepository.findById(id);
    }

    public Parking save(Parking parking) {
        return parkingRepository.save(parking);
    }
}
