package com.oyah.ooparkingsystem.repository;

import com.oyah.ooparkingsystem.entity.Entrance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceRepository extends JpaRepository<Entrance, Long> {
    
}
