package com.oyah.ooparkingsystem.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.oyah.ooparkingsystem.entity.Lot.Park.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lot {
    
    public static class Park {
        public enum Size {
            SP,
            MP,
            LP;
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    private Size size;
    
    @OneToMany
    @JoinColumn(name = "lot_id")
    private Set<ParkingDistance> parkingDistances;

    public boolean occupied;

    public Lot(Size size, boolean occupied) {
        this.size = size;
        this.occupied = occupied;
    }
}
