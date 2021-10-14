package com.oyah.ooparkingsystem.entity.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {
    
    private T data;

    public static ResponseData<?> createFrom(Object object) {
        return new ResponseData<>(object);
    }
}
