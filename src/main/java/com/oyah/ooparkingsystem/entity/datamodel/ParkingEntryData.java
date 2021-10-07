package com.oyah.ooparkingsystem.entity.datamodel;

import com.oyah.ooparkingsystem.entity.datamodel.ParkingEntryData.Vehicle.Size;

import lombok.Data;

@Data
public class ParkingEntryData {

    public static class Vehicle {
        public enum Size {
            S,
            M,
            L;
        }
    }
    
    private String plateNo;
    private Size vehichleSize;
    private Long entranceId;
}
