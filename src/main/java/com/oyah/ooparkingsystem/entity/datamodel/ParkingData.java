package com.oyah.ooparkingsystem.entity.datamodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.utils.DateUtils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParkingData {

    private Long id;

    private String plateNo;

    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    @JsonIgnoreProperties({"parking_distances", "occupied"})
    private Lot lot;

    private Boolean paid;
    
    private Parking previousParking; 

    @JsonGetter
    public Double getSuceedRate() {
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

    @JsonGetter
    public long getSuceedHours() {
        if (timeOut == null || timeIn == null) {
            return 0L;
        }
        
        Long totalHours = DateUtils.getDateDiffInHours(timeIn, timeOut);
        return totalHours > Parking.FLAT_HOUR ? (totalHours - Parking.FLAT_HOUR) : 0L;
    }

    @JsonGetter
    public Double getTotalCharge() {
        return Parking.FLAT_RATE + (getSuceedHours() * getSuceedRate());
    }

    public static ParkingData fromEntity(Parking parking) {
        return ParkingData.builder()
            .id(parking.getId())
            .plateNo(parking.getPlateNo())
            .timeIn(parking.getTimeIn())
            .timeOut(parking.getTimeOut())
            .lot(parking.getLot())
            .paid(parking.getPaid())
            .previousParking(parking.getPreviousParking())
            .build();
    }
}
