package com.oyah.ooparkingsystem.entity.datamodel;


import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestData<T> {
    
    @Valid
    public T data;
}
