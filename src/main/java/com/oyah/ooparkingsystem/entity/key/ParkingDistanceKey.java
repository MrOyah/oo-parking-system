package com.oyah.ooparkingsystem.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ParkingDistanceKey implements Serializable {
    
    @Column(name = "entrance_id")
    private Long entranceId;

    @Column(name = "lot_id")
    private Long lotId;

    public ParkingDistanceKey(Long entranceId, Long lotId) {
        this.entranceId = entranceId;
        this.lotId = lotId;
    }
}
