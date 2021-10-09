package com.oyah.ooparkingsystem.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lot {
    
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ParkingSize size;
    
    @OneToMany
    @JoinColumn(name = "lot_id")
    private List<ParkingDistance> parkingDistances;

    public boolean occupied;

    public Lot(ParkingSize size, boolean occupied) {
        this.size = size;
        this.occupied = occupied;
    }
}
