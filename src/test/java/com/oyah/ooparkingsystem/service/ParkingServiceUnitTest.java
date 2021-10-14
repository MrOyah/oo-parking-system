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
public class ParkingServiceUnitTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    void whenParkingIsSmall_thenParkingRateIsSpSucceedRate() {
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);

        assertEquals(Parking.SP_SUCCEED_RATE, parkingService.getSucceedRate(parking));
    }

    @Test
    void whenParkingIsMedium_thenParkingRateIsMpSucceedRate() {
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);

        assertEquals(Parking.MP_SUCCEED_RATE, parkingService.getSucceedRate(parking));
    }

    @Test
    public void whenParkingIsLarge_thenParkingRateIsLpSucceedRate() {
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);

        assertEquals(Parking.LP_SUCCEED_RATE, parkingService.getSucceedRate(parking));
    }

    @Test
    void whenParkingDurationIs3Hours_thenSucceedingHoursIs0Hour() {
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking = new Parking();
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3));

        assertEquals(0L, parkingService.getSucceedHours(parking));
    }

    @Test
    void whenParkingDurationIs5Hours_thenSucceedingHoursIs2Hours() {
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking = new Parking();
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(2L, parkingService.getSucceedHours(parking));
    }

    @Test
    void whenParkingDurationIs6Hours45Minutes_thenSucceedingHoursIs3Hours() {
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking = new Parking();
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(6).minusMinutes(45));

        assertEquals(3L, parkingService.getSucceedHours(parking));
    }

    @Test
    void whenSmallParkingDurationIs4Hours_thenSucceedingChargeIs20() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(4));

        assertEquals(20.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenMediumParkingDurationIs4Hours_thenSucceedingChargeIs60() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(4));

        assertEquals(60.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenLargeParkingDurationIs4Hours_thenSucceedingChargeIs100() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(4));

        assertEquals(100.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenSmallParkingDurationIs5Hours_thenSucceedingChargeIs40() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(40.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenMediumParkingDurationIs5Hours_thenSucceedingChargeIs120() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(120.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenLargeParkingDurationIs5Hours_thenSucceedingChargeIs200() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(200.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenSmallParkingDurationIs5HoursAnd1Second_thenSucceedingChargeIs60() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusSeconds(1));

        assertEquals(60.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenMediumParkingDurationIs5HoursAnd1Second_thenSucceedingChargeIs180() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusSeconds(1));

        assertEquals(180.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenLargeParkingDurationIs5HoursAnd1Second_thenSucceedingChargeIs300() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusSeconds(1));

        assertEquals(300.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenSmallParkingDurationIs5HoursAnd1Minute_thenSucceedingChargeIs60() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusMinutes(1));

        assertEquals(60.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenMediumParkingDurationIs5HoursAnd1Minute_thenSucceedingChargeIs180() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusMinutes(1));

        assertEquals(180.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenLargeParkingDurationIs5HoursAnd1Minute_thenSucceedingChargeIs300() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5).plusMinutes(1));

        assertEquals(300.0, parkingService.getSucceedingCharge(parking));
    }

    @Test
    void whenSmallParkingDurationIs1Day_thenFullDayChargeIsFullDayRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1));

        assertEquals(Parking.FULL_DAY_RATE, parkingService.getFullDayCharge(parking));
    }

    @Test
    void whenLargeParkingDurationIs1Day_thenFullDayChargeIsFullDayRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1));

        assertEquals(Parking.FULL_DAY_RATE, parkingService.getFullDayCharge(parking));
    }

    @Test
    void whenMediumParkingDurationIs1Day_thenFullDayChargeIsFullDayRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);

        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1));

        assertEquals(Parking.FULL_DAY_RATE, parkingService.getFullDayCharge(parking));
    }

    @Test
    public void whenSmallParkingDurationIs3Hours_thenTotalalChargeIsFlatRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3));

        assertEquals(Parking.FLAT_RATE, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenMediumParkingDurationIs3Hours_thenTotalalChargeIsFlatRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3));

        assertEquals(Parking.FLAT_RATE, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenLargeParkingDurationIs3Hours_thenTotalalChargeIsFlatRate() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3));

        assertEquals(Parking.FLAT_RATE, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenSmallParkingDurationIs3HoursAnd1Minute_thenTotalalChargeIs60() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3).plusMinutes(1));

        assertEquals(60.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenMediumParkingDurationIs3Hours1Minute_thenTotalalChargeIs100() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3).plusMinutes(1));

        assertEquals(100.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenLargeParkingDurationIs3HoursAnd1Minute_thenTotalalChargeIs140() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(3).plusMinutes(1));

        assertEquals(140.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenSmallParkingDurationIs5Hours_thenTotalalChargeIs80() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(80.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenMediumParkingDurationIs5Hours_thenTotalalChargeIs160() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(160.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenLargeParkingDurationIs5Hours_thenTotalalChargeIs240() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusHours(5));

        assertEquals(240.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenSmallParkingDurationIs1Day1Hour_thenTotalalChargeIs5020() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(1));

        assertEquals(5020.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenMediumParkingDurationIs1Day1Hour_thenTotalalChargeIs5060() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(1));

        assertEquals(5060.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenLargeParkingDurationIs1Day1Hour_thenTotalalChargeIs5100() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(1));

        assertEquals(5100.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenSmallParkingDurationIs1Day3Hours1Minute_thenTotalalChargeIs5080() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.SP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(3).plusMinutes(1));

        assertEquals(5080.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenMediumParkingDurationIs1Day3Hours1Minute_thenTotalalChargeIs5240() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.MP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(3).plusMinutes(1));

        assertEquals(5240.0, parkingService.getTotalCharge(parking));
    }

    @Test
    public void whenLargeParkingDurationIs1Day1Hour_thenTotalalChargeIs5400() {
        LocalDateTime timeIn = LocalDateTime.now();
        Lot lot = new Lot();
        lot.setParkingSize(ParkingSize.LP);
        
        Parking parking = new Parking();
        parking.setLot(lot);
        parking.setTimeIn(timeIn);
        parking.setTimeOut(timeIn.plusDays(1).plusHours(3).plusMinutes(1));

        assertEquals(5400.0, parkingService.getTotalCharge(parking));
    }


    @Test
    public void whenSmallParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdDurationIs10Minutes_thenLastParkingTotalChargeIs20() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.SP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot1);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(20.0, parkingService.getTotalCharge(parking3));
    }

    @Test
    public void whenMediumParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdDurationIs10Minutes_thenLastParkingTotalChargeIs60() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.MP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot1);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(60.00, parkingService.getTotalCharge(parking3));
    }

    @Test
    public void whenLargeParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdDurationIs10Minutes_thenLastParkingTotalChargeIs100() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.LP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot1);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(100.00, parkingService.getTotalCharge(parking3));
    }

    @Test
    public void whenSmallParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdMediumParkingDurationIs10Minutes_thenLastParkingTotalChargeIs60() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.SP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);
       
        Lot lot2 = new Lot();
        lot2.setParkingSize(ParkingSize.MP);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot2);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(60.0, parkingService.getTotalCharge(parking3));
    }

    @Test
    public void whenMediumParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdLargeParkingDurationIs10Minutes_thenLastParkingTotalChargeIs100() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.MP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);

        
        Lot lot2 = new Lot();
        lot2.setParkingSize(ParkingSize.LP);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot2);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(100.00, parkingService.getTotalCharge(parking3));
    }

    @Test
    public void whenLargeParking_1stDurationIs4Hours15Minutes_After15Minutes2ndDurationIs15Minutes_After10Minutes3rdSmallParkingDurationIs10Minutes_thenLastParkingTotalChargeIs20() {
        Lot lot1 = new Lot();
        lot1.setParkingSize(ParkingSize.LP);
        
        LocalDateTime timeIn = LocalDateTime.now();
        Parking parking1 = new Parking();
        parking1.setLot(lot1);
        parking1.setTimeIn(timeIn);
        parking1.setTimeOut(timeIn.plusHours(4).plusMinutes(15));

        LocalDateTime timeIn2 = parking1.getTimeOut().plusMinutes(15);
        Parking parking2 = new Parking();
        parking2.setLot(lot1);
        parking2.setTimeIn(timeIn2);
        parking2.setTimeOut(timeIn2.plusMinutes(15));
        parking2.setPreviousParking(parking1);
        
        Lot lot2 = new Lot();
        lot2.setParkingSize(ParkingSize.SP);

        LocalDateTime timeIn3 = parking2.getTimeOut().plusMinutes(10);
        Parking parking3 = new Parking();
        parking3.setLot(lot2);
        parking3.setTimeIn(timeIn3);
        parking3.setTimeOut(timeIn3.plusMinutes(20));
        parking3.setPreviousParking(parking2);

        assertEquals(20.00, parkingService.getTotalCharge(parking3));
    }
    
}
