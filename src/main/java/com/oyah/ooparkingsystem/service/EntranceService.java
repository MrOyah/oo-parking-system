package com.oyah.ooparkingsystem.service;

import java.util.List;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.repository.EntranceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntranceService {
    
    @Autowired
    private EntranceRepository entranceRepository;

    public List<Entrance> getAll() {
        return entranceRepository.findAll();
    }

    public Entrance findById(Long id) {
        return entranceRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrance id not found.")
        );
    }

    public Entrance create(Entrance entranceRequest) {
        Entrance entrance = new Entrance(entranceRequest.getName());
        return entranceRepository.save(entrance);
    }

    public Entrance update(Long id, Entrance entranceRequest) {
        Entrance entrance = findById(id);
        entrance.setName(entranceRequest.getName());
        return entranceRepository.save(entrance);
    }

    public void delete(Long id) {
        Entrance entrance = findById(id);
        entranceRepository.delete(entrance);
    }
}
