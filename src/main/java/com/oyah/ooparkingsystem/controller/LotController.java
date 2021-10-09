package com.oyah.ooparkingsystem.controller;

import java.util.List;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LotController {
    
    @Autowired
    private LotRepository lotRepository;

    @GetMapping("/lots")
    public List<Lot> retriveLots() {
        return lotRepository.findAll();
    }
}
