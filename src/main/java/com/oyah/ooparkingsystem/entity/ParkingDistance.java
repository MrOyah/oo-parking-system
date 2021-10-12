package com.oyah.ooparkingsystem.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.oyah.ooparkingsystem.entity.key.ParkingDistanceKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDistance {

    @EmbeddedId
    private ParkingDistanceKey id;

    public Long distance;

    @ManyToOne
    @MapsId("entranceId")
    @JoinColumn(name = "entrance_id")
    Entrance entrance;

    @ManyToOne
    @MapsId("lotId")
    @JoinColumn(name = "lot_id")
    Lot lot;

    public ParkingDistance(Entrance entrance, Lot lot, Long distance) {
        this.id = new ParkingDistanceKey(entrance.getId(), lot.getId());
        this.entrance = entrance;
        this.lot = lot;
        this.distance = distance;
    }
}
