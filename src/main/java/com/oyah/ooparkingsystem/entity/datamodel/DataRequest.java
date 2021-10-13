package com.oyah.ooparkingsystem.entity.datamodel;

import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataRequest<T> {
    
    @Valid
    public T data;
}
