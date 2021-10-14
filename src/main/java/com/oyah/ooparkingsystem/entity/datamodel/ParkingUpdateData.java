package com.oyah.ooparkingsystem.entity.datamodel;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ParkingUpdateData {
    
    @NotBlank(message = "plate_no|The plate_no must not be blank.")
    private String plateNo;
}
