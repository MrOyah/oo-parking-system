package com.oyah.ooparkingsystem.entity.datamodel;

import lombok.Data;

@Data
public class Violation {
    
    private final String fieldName;

    private final String message;
}
