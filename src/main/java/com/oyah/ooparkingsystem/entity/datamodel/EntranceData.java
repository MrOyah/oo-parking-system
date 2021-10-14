package com.oyah.ooparkingsystem.entity.datamodel;

import javax.validation.constraints.NotBlank;

import com.oyah.ooparkingsystem.entity.Entrance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntranceData {

    private Long id;

    @NotBlank(message = "The name must not be blank.")
    private String name;

    public Entrance toEntity() {
        return Entrance.builder()
            .id(this.id)
            .name(this.name)
            .build();
    }

    public static EntranceData fromEntity(Entrance entrance) {
        return EntranceData.builder()
            .id(entrance.getId())
            .name(entrance.getName())
            .build();
    }
}
