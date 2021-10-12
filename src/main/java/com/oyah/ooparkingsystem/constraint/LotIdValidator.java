package com.oyah.ooparkingsystem.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.oyah.ooparkingsystem.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class LotIdValidator implements ConstraintValidator<LotIdConstraint, Long> {
    
    @Autowired
    private LotRepository lotRepository;

    @Override
    public boolean isValid(Long lotId, ConstraintValidatorContext arg1) {
        return lotRepository.findById(lotId).isPresent();
    }
    
}
