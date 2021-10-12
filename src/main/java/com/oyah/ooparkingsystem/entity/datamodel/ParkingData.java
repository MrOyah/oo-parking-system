package com.oyah.ooparkingsystem.entity.datamodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oyah.ooparkingsystem.constant.ParkingEnum.VehicleSize;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingData {

    private Long id;

    private String plateNo;

    private VehicleSize vehicleSize;

    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    @JsonIgnoreProperties({"occupied"})
    private Lot lot;

    private Boolean paid;
    
    private Parking previousParking;

    private Double totalCharge;

    public static ParkingData fromEntity(Parking parking) {
        if (parking == null) {
            return null;
        }
        return ParkingData.builder()
            .id(parking.getId())
            .plateNo(parking.getPlateNo())
            .vehicleSize(parking.getVehicleSize())
            .timeIn(parking.getTimeIn())
            .timeOut(parking.getTimeOut())
            .lot(parking.getLot())
            .paid(parking.getPaid())
            .previousParking(parking.getPreviousParking())
            .totalCharge(parking.getTotalCharge())
            .build();
    }
}
