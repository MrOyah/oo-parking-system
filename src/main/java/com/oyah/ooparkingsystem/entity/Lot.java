package com.oyah.ooparkingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lot {
    
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ParkingSize size;

    public boolean occupied;

    public Lot(ParkingSize size, boolean occupied) {
        this.size = size;
        this.occupied = occupied;
    }
}
