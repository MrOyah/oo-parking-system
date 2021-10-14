package com.oyah.ooparkingsystem.service;

import java.util.List;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingCreateData;
import com.oyah.ooparkingsystem.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LotService {
    
    @Autowired
    private LotRepository lotRepository;

    public List<Lot> getAll() {
        return lotRepository.findAll();
    }
    
    public Lot findById(Long id) {
        return lotRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lot id not found.")
        );
    }

    public Lot findClosestLotFromEntranceId(Long entrance_id, ParkingSize parkingSize) {
        Lot lot = lotRepository.findClosestLotFromEntrance(entrance_id, parkingSize).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.OK, "Parking is full.")
        );

        lot.setOccupied(true);
        return lotRepository.save(lot);
    }

    public Lot occupyClosestLotFromEntrance(ParkingCreateData parkingEntryData) {
        return findClosestLotFromEntranceId(
            parkingEntryData.getEntranceId(), 
            parkingEntryData.getParkingSize()
        );
    }

    public Lot unoccupy(Lot lot) {
        lot.setOccupied(false);
        return lotRepository.save(lot);
    }

    public Lot create(Lot lot) {
        lot.setId(null);
        lot.setOccupied(false);
        return lotRepository.save(lot);
    }

    public void delete(Long id) {
        Lot lot = findById(id);
        lotRepository.delete(lot);
    }

    public Lot update(Long id, Lot lotRequest) {
        Lot lot = findById(id);
        lot.setParkingSize(lotRequest.getParkingSize());
        lot.setOccupied(lotRequest.isOccupied());
        return lotRepository.save(lot);
    }
}
