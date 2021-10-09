package com.oyah.ooparkingsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingEntryData;
import com.oyah.ooparkingsystem.repository.ParkingRepository;
import com.oyah.ooparkingsystem.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParkingService {
    
    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private LotService lotService;

    public List<ParkingData> getAll() {
        return parkingRepository.findAll() .stream()
            .map(p -> ParkingData.fromEntity(p))
            .collect(Collectors.toList());
    }

    public ParkingData getById(Long id) {
        return ParkingData.fromEntity(parkingRepository.findById(id).orElse(null));
    }

    public Parking saveEntry(ParkingEntryData parkingEntryData) {
        Lot lot = lotService.occupyClosestLotFromEntrance(parkingEntryData);
        Parking previousParking = parkingRepository.findPreviousParking(parkingEntryData.getPlateNo());

        Parking parking = parkingEntryData.toEntity();
        parking.setLot(lot);
        parking.setPreviousParking(previousParking);
        return parkingRepository.save(parking);
    }
    
    public ParkingData unpark(Long id) {
        Parking parking = parkingRepository.findById(id).orElse(null);
        return unpark(parking);
    }

    public ParkingData unpark(Parking parking) {
        if (parking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking not found.");
        }
        parking.setTimeOut(LocalDateTime.now());
        parking.setPaid(true);
        parking.setTotalCharge(getTotalCharge(parking));
        parkingRepository.save(parking);
        
        return ParkingData.fromEntity(parking);
    }

    private Double getSuceedRate(Parking parking) {
        Lot lot = parking.getLot();
        if (lot == null) {
            return 0.0;
        }
        
        switch (lot.getSize()) {
            case SP:
                return Parking.SP_SUCCEED_RATE;
            case MP:
                return Parking.MP_SUCCEED_RATE;
            default:
                return Parking.LP_SUCCEED_RATE;
        }
    }

    private Long getSuceedHours(Parking parking) {
        if (parking.getTimeIn() == null || parking.getTimeOut() == null) {
            return 0L;
        }
        
        Long totalHours = DateUtils.getDateDiffInHours(parking.getTimeIn(), parking.getTimeOut());
        totalHours %= 24;
        return totalHours > Parking.FLAT_HOUR ? (totalHours - Parking.FLAT_HOUR) : 0L;
    }

    private Double getSuceedCharge(Parking parking) {
        return getSuceedHours(parking) * getSuceedRate(parking);
    }

    private Double getFullDayCharge(Parking parking) {
        if (parking.getTimeIn() == null || parking.getTimeOut() == null) {
            return 0.0;
        }

        Long totalDays = DateUtils.getDateDiffInDays(parking.getTimeIn(), parking.getTimeOut());
        return totalDays * Parking.FULL_DAY_RATE;
    }

    private Double getTotalCharge(Parking parking) {
        return Parking.FLAT_RATE + getSuceedCharge(parking) + getFullDayCharge(parking);
    }
}
