package com.oyah.ooparkingsystem.repository;

import java.util.Optional;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotRepository extends JpaRepository<Lot, Long> {

    static final String FIND_CLOSEST_LOT_FROM_ENTRACE_QUERY = ""
        + "SELECT l.* "
        + "FROM lot l "
        + "JOIN parking_distance pd "
        + "   ON l.id = pd.lot_id "
        + "WHERE pd.entrance_id = :entrance_id "
        + "   AND l.parking_size <= :parking_size "
        + "   AND l.occupied = false "
        + "ORDER BY pd.distance "
        + "LIMIT 1";

    @Query(
        value = FIND_CLOSEST_LOT_FROM_ENTRACE_QUERY, 
        nativeQuery = true
    )
    Optional<Lot> findClosestLotFromEntrance(@Param("entrance_id") Long entrance_id, @Param("parking_size") ParkingSize parkingSize);
}
