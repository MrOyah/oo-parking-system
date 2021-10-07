package com.oyah.ooparkingsystem.repository;

import com.oyah.ooparkingsystem.entity.Parking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    
    @Query(
        value = "SELECT * FROM parking p WHERE p.plate_no = :plate_no AND p.paid = true", 
        nativeQuery = true
    )
    Parking findPreviousParking(@Param("plate_no") String plateNo);
 }
