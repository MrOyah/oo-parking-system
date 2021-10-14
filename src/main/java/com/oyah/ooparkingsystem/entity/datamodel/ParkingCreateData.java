package com.oyah.ooparkingsystem.entity.datamodel;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.constant.ParkingEnum.VehicleSize;

import lombok.Data;

@Data
public class ParkingCreateData {
    
    @NotBlank(message = "plate_no|The plate_no must not be blank.")
    private String plateNo;

    @NotBlank(message = "vehicle_size|The vehicle_size must not be blank.")
    @Pattern(regexp = "(Small)|(Medium)|(Large)", message = "vehicle_size|The vehicle_size value must be `Small`, `Medium` or `Large`")
    private String vehicleSize;

    @NotNull(message = "entrance_id|The entrance_id must not be null.")
    private Long entranceId;

    public ParkingSize getParkingSize() {
        switch (getVehicleSize()) {
            case S:
                return ParkingSize.SP;
            case M:
                return ParkingSize.MP;
            default:
                return ParkingSize.LP;
        }
    }

    public VehicleSize getVehicleSize() {
        return VehicleSize.valueOfLabel(this.vehicleSize);
    }
}