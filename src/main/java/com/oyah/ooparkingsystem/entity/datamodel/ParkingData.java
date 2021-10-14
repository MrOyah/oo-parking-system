package com.oyah.ooparkingsystem.entity.datamodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.oyah.ooparkingsystem.constant.ParkingEnum.VehicleSize;
import com.oyah.ooparkingsystem.entity.Parking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class ParkingData {

    private Long id;

    private String plateNo;

    private VehicleSize vehicleSize;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeIn;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeOut;

    @JsonIgnoreProperties({"occupied"})
    private LotData lot;

    private Boolean paid;

    private Double totalCharge;

    public static ParkingData fromEntity(Parking parking) {
        return ParkingData.builder()
            .id(parking.getId())
            .plateNo(parking.getPlateNo())
            .vehicleSize(parking.getVehicleSize())
            .timeIn(parking.getTimeIn())
            .timeOut(parking.getTimeOut())
            .lot(LotData.fromEntity(parking.getLot()))
            .paid(parking.getPaid())
            .totalCharge(parking.getTotalCharge())
            .build();  
    }
}
