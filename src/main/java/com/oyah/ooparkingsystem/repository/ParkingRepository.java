package com.oyah.ooparkingsystem.repository;

import java.time.LocalDateTime;

import com.oyah.ooparkingsystem.entity.Parking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    static final String FIND_PREIVOUS_PARKING_WITHIN_1_HOUR = ""
        + "SELECT p.* "
        + "FROM parking p "
        + "WHERE p.plate_no = :plate_no "
        + "   AND p.paid = true "
        + "   AND p.time_out >= :time_in";

    @Query(
        value = FIND_PREIVOUS_PARKING_WITHIN_1_HOUR, 
        nativeQuery = true
    )
    Parking findPreviousParking(@Param("plate_no") String plateNo, @Param("time_in") LocalDateTime timeIn);
 }
