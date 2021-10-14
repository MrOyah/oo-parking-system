package com.oyah.ooparkingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingCreateData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingUpdateData;
import com.oyah.ooparkingsystem.repository.ParkingRepository;
import com.oyah.ooparkingsystem.util.DateUtils;

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

    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(Long id) {
        return parkingRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking not found.")
        );
    }

    public Parking create(ParkingCreateData parkingCreateData) {
        Lot lot = lotService.occupyClosestLotFromEntrance(parkingCreateData);
        Parking previousParking = parkingRepository.findPreviousParking(
            parkingCreateData.getPlateNo(),
            LocalDateTime.now().minusHours(1)
        );
        
        Parking parking = new Parking();
        parking.setPlateNo(parkingCreateData.getPlateNo());
        parking.setVehicleSize(parkingCreateData.getVehicleSize());
        parking.setLot(lot);
        parking.setPreviousParking(previousParking);
        return parkingRepository.save(parking);
    }

    public Parking update(Long id, ParkingUpdateData parkingUpdateData) {
        Parking parking = findById(id);
        parking.setPlateNo(parkingUpdateData.getPlateNo());
        return parkingRepository.save(parking);
    }

    public void delete(Long id) {
        Parking parking = findById(id);
        parkingRepository.delete(parking);
    }
    
    public Parking unparkAndSave(Long id) {
        Parking parking = findById(id);
        parking = unpark(parking);
        Lot lot = lotService.unoccupy(parking.getLot());
        parking.setLot(lot);
        return parkingRepository.save(parking);
    }

    public Parking unpark(Parking parking) {
        parking.setTimeOut(LocalDateTime.now());
        parking.setPaid(true);
        parking.setTotalCharge(getTotalCharge(parking));
        return parking;
    }

    public LocalDateTime getPreviousTimeIn(Parking parking) {
        while (parking.getPreviousParking() != null) {
            parking = parking.getPreviousParking();
        }
        LocalDateTime previousTimeIn = parking.getTimeIn();
        return previousTimeIn;
    }

    public Long getPreivousSucceedHours(Parking parking) {
        if (parking.getPreviousParking() == null) {
            return 0L;
        }

        LocalDateTime previousTimeIn = getPreviousTimeIn(parking);
        LocalDateTime previousTimeOut = parking.getPreviousParking().getTimeOut();
        
        return getSucceedHours(previousTimeIn, previousTimeOut);
    }

    public LocalDateTime getTimeInOrPreivousTimeIn(Parking parking) {
        return parking.getPreviousParking() == null ? 
            parking.getTimeIn() : 
            getPreviousTimeIn(parking);
    }

    public Double getSucceedRate(Parking parking) {
        Lot lot = parking.getLot();
        if (lot == null) {
            return 0.0;
        }
        
        switch (lot.getParkingSize()) {
            case SP:
                return Parking.SP_SUCCEED_RATE;
            case MP:
                return Parking.MP_SUCCEED_RATE;
            default:
                return Parking.LP_SUCCEED_RATE;
        }
    }

    public Long getSucceedHours(Parking parking) {
        if (parking.getTimeOut() == null) {
            return 0L;
        }
        
        LocalDateTime timeIn = getTimeInOrPreivousTimeIn(parking);
        return getSucceedHours(timeIn, parking.getTimeOut());
    }

    private Long getSucceedHours(LocalDateTime timeIn, LocalDateTime timeOut) {
        Long totalHours = DateUtils.getDateDiffInHoursInCeil(timeIn, timeOut);

        if (totalHours > DateUtils.HOURS_IN_DAY) {
            totalHours %= DateUtils.HOURS_IN_DAY;
        } else if (totalHours >= Parking.FLAT_HOUR) {
            totalHours -= Parking.FLAT_HOUR;
        }
        
        return totalHours;
    }

    public Double getSucceedingCharge(Parking parking) {
        return (getSucceedHours(parking) - getPreivousSucceedHours(parking)) * getSucceedRate(parking);
    }

    public Double getFullDayCharge(Parking parking) {
        if (parking.getTimeOut() == null) {
            return 0.0;
        }

        LocalDateTime timeIn = getTimeInOrPreivousTimeIn(parking);
        Long totalDays = DateUtils.getDateDiffInDays(timeIn, parking.getTimeOut());
        return totalDays * Parking.FULL_DAY_RATE;
    }

    public Double getTotalCharge(Parking parking) {
        Double fullDayCharge = getFullDayCharge(parking);
        Double totalCharge = parking.getPreviousParking() == null && fullDayCharge == 0.0 ?
            Parking.FLAT_RATE :
            0.0;

        totalCharge += getSucceedingCharge(parking) + getFullDayCharge(parking);
        return totalCharge;
    }
}
