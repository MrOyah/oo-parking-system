package com.oyah.ooparkingsystem.entity.datamodel;

import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDistanceDataList<T> {
    
    @Valid
    public List<T> data;
}
