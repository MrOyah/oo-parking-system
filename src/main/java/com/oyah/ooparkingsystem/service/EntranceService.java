package com.oyah.ooparkingsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import com.oyah.ooparkingsystem.entity.datamodel.EntranceData;
import com.oyah.ooparkingsystem.repository.EntranceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntranceService {
    
    @Autowired
    private EntranceRepository entranceRepository;

    public List<EntranceData> getAll() {
        return entranceRepository.findAll().stream()
            .map(e -> EntranceData.fromEntity(e))
            .collect(Collectors.toList());
    }
}
