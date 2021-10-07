package com.oyah.ooparkingsystem.repository;

import com.oyah.ooparkingsystem.entity.Lot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotRepository extends JpaRepository<Lot, Long> {
    
    @Query(
        value = "SELECT * FROM lot l LEFT JOIN parking_distance pd ON l.id = pd.lot_id WHERE pd.entrance_id = :entrance_id AND l.occupied = false ORDER BY pd.distance LIMIT 1", 
        nativeQuery = true
    )
    Lot findClosestLotFromEntrance(@Param("entrance_id") long entrance_id);
}
