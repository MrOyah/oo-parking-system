package com.oyah.ooparkingsystem.service;

import java.time.LocalDateTime;
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
        Parking lastParking = parkingRepository.findByPlateNo(parking.getPlateNo());
        parking.setLastParking(lastParking);
        return parkingRepository.save(parking);
    }
    
    public Parking unpark(Long id) {
        Parking parking = findById(id);
        parking.setTimeOut(LocalDateTime.now());
        parking.setPaid(true);
        return save(parking);
    }
}
