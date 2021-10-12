package com.oyah.ooparkingsystem.entity.datamodel;

import com.oyah.ooparkingsystem.constraint.LotIdConstraint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingDistanceData {
    
    @LotIdConstraint(message = "The lot id doesn't exist.")
    private Long lotId;

    private Long distance;
}
