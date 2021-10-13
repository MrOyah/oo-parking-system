package com.oyah.ooparkingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParkingServiceTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    public void testTotalCharge() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot1 = new Lot();
        lot1.setSize(ParkingSize.SP);
        
        Parking parking1 = new Parking();
        parking1.setId(1L);
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        assertEquals(2L, parkingService.getSuceedHours(parking1));
        assertEquals(80.0, parkingService.getTotalCharge(parking1));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setId(2L);
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);

        assertEquals(0L, parkingService.getSuceedHours(parking2));
        assertEquals(0.0, parkingService.getTotalCharge(parking2));

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setId(3L);
        parking3.setLot(lot1);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(1L, parkingService.getSuceedHours(parking3));
        assertEquals(20.0, parkingService.getTotalCharge(parking3));
    }
    
}
