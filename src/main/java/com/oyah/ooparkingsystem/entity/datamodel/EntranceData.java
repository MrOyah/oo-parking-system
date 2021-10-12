package com.oyah.ooparkingsystem.entity.datamodel;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntranceData {

    private Long id;

    @NotBlank(message = "The name must not be blank.")
    private String name;
}
