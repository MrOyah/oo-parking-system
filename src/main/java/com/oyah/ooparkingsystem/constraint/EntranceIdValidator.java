package com.oyah.ooparkingsystem.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.oyah.ooparkingsystem.repository.EntranceRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class EntranceIdValidator implements ConstraintValidator<EntranceIdConstraint, Long> {

    @Autowired
    private EntranceRepository entranceRepository;

    @Override
    public boolean isValid(Long entranceId, ConstraintValidatorContext arg1) {
        return entranceRepository.findById(entranceId).isPresent();
    }
    
}
