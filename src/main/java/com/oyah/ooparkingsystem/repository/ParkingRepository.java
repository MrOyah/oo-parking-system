package com.oyah.ooparkingsystem.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oyah.ooparkingsystem.entity.Parking;

import org.springframework.stereotype.Component;

@Component
public class ParkingRepository {
    
    private static List<Parking> parkingList = new ArrayList<>();
    private static Long id = 0L;

    static {
        parkingList.add(new Parking(1L, "ABC", new Date(), null, false));
        parkingList.add(new Parking(2L, "DEF", new Date(), new Date(), true));
        parkingList.add(new Parking(3L, "GHI", new Date(), new Date(), true));
        parkingList.add(new Parking(4L, "JKL", new Date(), null, false));
        id = parkingList.size() + 1L;
    }

    public List<Parking> findAll() {
        return parkingList;
    }

    public Parking findById(Long id) {
        return parkingList.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public Parking save(Parking parking) {
        parking.setId(id);
        parkingList.add(parking);
        id++;
        return parking;
    }
 }
