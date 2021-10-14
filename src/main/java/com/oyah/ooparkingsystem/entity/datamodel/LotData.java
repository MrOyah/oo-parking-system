package com.oyah.ooparkingsystem.entity.datamodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LotData {

    private Long id;

    @NotBlank(message = "vehicle_size|The vehicle_size must not be blank.")
    @Pattern(regexp = "(Small)|(Medium)|(Large)", message = "parking_size|The parking_size value must be `Small`, `Medium` or `Large`")
    private String parkingSize;

    private boolean occupied;

    public Lot toEntity() {
        return Lot.builder()
            .id(this.id)
            .parkingSize(ParkingSize.valueOfLabel(this.parkingSize))
            .occupied(this.occupied)
            .build();
    }

    public static LotData fromEntity(Lot lot) {
        return LotData.builder()
            .id(lot.getId())
            .parkingSize(lot.getParkingSize().getLabel())
            .occupied(lot.isOccupied())
            .build();
    }
}
