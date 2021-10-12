package com.oyah.ooparkingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.ParkingDistance;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingDistanceData;
import com.oyah.ooparkingsystem.repository.ParkingDistanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingDistanceService {

    @Autowired
    private ParkingDistanceRepository parkingDistanceRepository;

    @Autowired
    private LotService lotService;

    @Autowired
    private EntranceService entranceService;
    
    public List<ParkingDistance> getAllByEntranceId(Long entranceId) {
        return parkingDistanceRepository.findByEntranceId(entranceId);
    }

    public Optional<ParkingDistance> getByEntranceIdAndLotId(Long entranceId, Long lotId) {
        return parkingDistanceRepository.findByEntranceIdAndLotId(entranceId, lotId);
    }

    public List<ParkingDistance> saveAll(Long entranceId, List<ParkingDistanceData> parkingDistanceRequest) {
        List<ParkingDistance> newParkingDistances = new ArrayList<>();

        Optional<Entrance> entranceOptional = entranceService.findById(entranceId);
        if (entranceOptional.isEmpty()) {
            return newParkingDistances;
        }

        List<Lot> lots = lotService.findAll();
        if (lots.isEmpty()) {
            return newParkingDistances;
        }
        
        for (ParkingDistanceData parkingDistanceData : parkingDistanceRequest) {
            Optional<Lot> lotOptional = lots.stream().filter(l -> l.getId() == parkingDistanceData.getLotId()).findFirst();
            if (lotOptional.isEmpty()) {
                continue;
            }

            newParkingDistances.add(
                new ParkingDistance(
                    entranceOptional.get(), 
                    lotOptional.get(), 
                    parkingDistanceData.getDistance()
                )
            );
        }
        return parkingDistanceRepository.saveAll(newParkingDistances);
    }

    public List<ParkingDistance> saveAll(List<ParkingDistance> parkingDistances) {
        return parkingDistanceRepository.saveAll(parkingDistances);
    }
}
