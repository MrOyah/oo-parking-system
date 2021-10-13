package com.oyah.ooparkingsystem.repository;

import java.util.List;
import java.util.Optional;

import com.oyah.ooparkingsystem.entity.ParkingDistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingDistanceRepository extends JpaRepository<ParkingDistance, Long>{

    Optional<List<ParkingDistance>> findByEntranceId(Long entranceId);

    Optional<ParkingDistance> findByEntranceIdAndLotId(Long entranceId, Long lotId);
}
