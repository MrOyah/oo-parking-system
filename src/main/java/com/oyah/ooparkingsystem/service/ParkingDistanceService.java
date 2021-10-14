package com.oyah.ooparkingsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.ParkingDistance;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingDistanceData;
import com.oyah.ooparkingsystem.repository.ParkingDistanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParkingDistanceService {

    @Autowired
    private ParkingDistanceRepository parkingDistanceRepository;

    @Autowired
    private LotService lotService;

    @Autowired
    private EntranceService entranceService;
    
    public List<ParkingDistance> getAllByEntranceId(Long entranceId) {
        return parkingDistanceRepository.findByEntranceId(entranceId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrance id not found.")
        );
    }

    public ParkingDistance getByEntranceIdAndLotId(Long entranceId, Long lotId) {
        entranceService.findById(entranceId);
        return parkingDistanceRepository.findByEntranceIdAndLotId(entranceId, lotId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lot id not found.")
        );
    }

    public List<ParkingDistance> saveAll(
        Long entranceId, 
        List<ParkingDistanceData> parkingDistanceRequest) {

        Entrance entrance = entranceService.findById(entranceId);
        
        //TODO: Change if lots will be too large;
        List<Lot> lots = lotService.getAll();

        List<ParkingDistance> newParkingDistances = new ArrayList<>();
        for (ParkingDistanceData parkingDistanceData : parkingDistanceRequest) {
            Lot lot = lots.stream()
                .filter(l -> l.getId() == parkingDistanceData.getLotId())
                .findFirst()
                .get();
            newParkingDistances.add(
                new ParkingDistance(
                    entrance, lot, parkingDistanceData.getDistance()
                )
            );
        }
        return parkingDistanceRepository.saveAll(newParkingDistances);
    }

    public List<ParkingDistance> saveAll(List<ParkingDistance> parkingDistances) {
        return parkingDistanceRepository.saveAll(parkingDistances);
    }
}
