package com.oyah.ooparkingsystem.repository;

import com.oyah.ooparkingsystem.entity.ParkingDistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingDistanceRepository extends JpaRepository<ParkingDistance, Long>{
    
}
