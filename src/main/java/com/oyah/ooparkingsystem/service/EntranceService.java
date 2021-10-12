package com.oyah.ooparkingsystem.service;

import java.util.List;
import java.util.Optional;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.repository.EntranceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntranceService {
    
    @Autowired
    private EntranceRepository entranceRepository;

    public List<Entrance> getAll() {
        return entranceRepository.findAll();
    }

    public Optional<Entrance> findById(Long entranceId) {
        return entranceRepository.findById(entranceId);
    }

    public Entrance create(Entrance entranceRequest) {
        Entrance entrance = new Entrance(entranceRequest.getName());
        return entranceRepository.save(entrance);
    }

    public Entrance update(Long id, Entrance entranceRequest) {
        Entrance entrance = entranceRepository.findById(id).orElse(null);
        entrance.setName(entranceRequest.getName());
        return entranceRepository.save(entrance);
    }
}
