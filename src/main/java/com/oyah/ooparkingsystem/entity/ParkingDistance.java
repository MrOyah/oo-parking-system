package com.oyah.ooparkingsystem.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.oyah.ooparkingsystem.entity.key.ParkingDistanceKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParkingDistance {

    @EmbeddedId
    private ParkingDistanceKey id;

    public Long distance;

    public ParkingDistance(ParkingDistanceKey id, Long distance) {
        this.id = id;
        this.distance = distance;
    }
}
