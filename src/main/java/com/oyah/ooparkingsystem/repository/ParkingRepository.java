package com.oyah.ooparkingsystem.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.Lot.Park.Size;

import org.springframework.stereotype.Component;

@Component
public class ParkingRepository {
    
    private static List<Parking> parkingList = new ArrayList<>();
    private static Long id = 0L;

    static {
        LocalDateTime previousTime = LocalDateTime.now().minus(5, ChronoUnit.HOURS);

        Lot smallParkingLot = new Lot(1L, Size.SP);
        Lot largeParkingLot = new Lot(2L, Size.LP);

        parkingList.add(new Parking(1L, "ABC", previousTime, smallParkingLot, null));
        parkingList.add(new Parking(2L, "DEF", LocalDateTime.now(), null, null));
        parkingList.add(new Parking(3L, "GHI", LocalDateTime.now(), null, null));
        parkingList.add(new Parking(4L, "JKL", LocalDateTime.now(), largeParkingLot, null));
        id = parkingList.size() + 1L;
    }

    public List<Parking> findAll() {
        return parkingList;
    }

    public Parking findById(Long id) {
        return parkingList.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public Parking findByPlateNo(String plateNo) {
        return parkingList.stream().filter(p -> p.getPlateNo().equals(plateNo) && !p.getPaid()).findAny().orElse(null);
    }

    public Parking save(Parking parking) {
        if (parking.getId() != null) {
            parking = findById(parking.getId());
            parking.setTimeOut(parking.getTimeOut());
            parking.setPaid(parking.getPaid());
        } else {
            parking.setId(id);
            id++;
            parkingList.add(parking);
        }
        return parking;
    }
 }
