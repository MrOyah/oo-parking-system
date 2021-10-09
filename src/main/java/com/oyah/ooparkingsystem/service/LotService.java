package com.oyah.ooparkingsystem.service;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingEntryData;
import com.oyah.ooparkingsystem.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LotService {
    
    @Autowired
    private LotRepository lotRepository;

    public Lot findClosestLotFromEntranceId(Long entrance_id, ParkingSize parkingSize) {
        return lotRepository.findClosestLotFromEntrance(entrance_id, parkingSize);
    }

    public Lot save(Lot lot) {
        return lotRepository.save(lot);
    }

    public Lot occupyClosestLotFromEntrance(ParkingEntryData parkingEntryData) {
        return occupyClosestLotFromEntrance(parkingEntryData.getEntranceId(), parkingEntryData.getParkingSize());
    }

    public Lot occupyClosestLotFromEntrance(Long entrance_id, ParkingSize parkingSize) {
        Lot lot = findClosestLotFromEntranceId(entrance_id, parkingSize);
        if (lot == null) {
            throw new ResponseStatusException(HttpStatus.OK, "Parking is full.");
        }

        lot.setOccupied(true);
        save(lot);
        return lot;
    }
}
