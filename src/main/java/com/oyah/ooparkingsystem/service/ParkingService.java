package com.oyah.ooparkingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingEntryData;
import com.oyah.ooparkingsystem.repository.ParkingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {
    
    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private LotService lotService;

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(Long id) {
        return parkingRepository.getById(id);
    }

    public Parking saveEntry(ParkingEntryData parkingEntryData) {
        Lot lot = lotService.occupyClosestLotFromEntrance(parkingEntryData.getEntranceId());
        Parking previousParking = parkingRepository.findPreviousParking(parkingEntryData.getPlateNo());
        return parkingRepository.save(
            new Parking(
                parkingEntryData.getPlateNo(),
                lot,
                previousParking
            )
        );
    }
    
    public ParkingData unpark(Long id) {
        Parking parking = findById(id);
        parking.setTimeOut(LocalDateTime.now());
        parking.setPaid(true);
        parkingRepository.save(parking);
        return ParkingData.fromEntity(parking);
    }
}
