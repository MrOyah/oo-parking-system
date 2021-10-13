package com.oyah.ooparkingsystem.entity.datamodel;

import javax.validation.constraints.Positive;

import com.oyah.ooparkingsystem.constraint.LotIdConstraint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingDistanceData {
    
    @LotIdConstraint(message = "lot_id|The lot_id doesn't exist.")
    private Long lotId;

    @Positive(message = "distance|The distance must be greater than zero.")
    private Long distance;
}
