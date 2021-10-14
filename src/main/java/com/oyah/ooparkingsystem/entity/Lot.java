package com.oyah.ooparkingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lot {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "parking_size")
    private ParkingSize parkingSize;
    
    private boolean occupied;

    public Lot(ParkingSize parkingSize, boolean occupied) {
        this.parkingSize = parkingSize;
        this.occupied = occupied;
    }
}
