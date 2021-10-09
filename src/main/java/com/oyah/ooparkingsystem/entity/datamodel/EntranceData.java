package com.oyah.ooparkingsystem.entity.datamodel;

import java.util.HashMap;

import com.oyah.ooparkingsystem.entity.Entrance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntranceData {

    private Long id;

    private String name;

    private HashMap<Long, Long> parkingDistances;

    public static EntranceData fromEntity(Entrance entrance) {
        HashMap<Long,Long> parkingDistances = new HashMap<>();
        entrance.getParkingDistances().stream()
            .forEach(pd -> parkingDistances.put(pd.getId().getLotId(), pd.distance));
        return EntranceData.builder()
                .id(entrance.getId())
                .name(entrance.getName())
                .parkingDistances(parkingDistances)
                .build();
    }
}
