package com.oyah.ooparkingsystem.repository;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Lot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotRepository extends JpaRepository<Lot, Long> {

    static final String FIND_CLOSES_LOT_FROM_ENTRACE_QUERY = ""
        + "SELECT l.* "
        + "FROM lot l "
        + "JOIN parking_distance pd "
        + "   ON l.id = pd.lot_id "
        + "WHERE pd.entrance_id = :entrance_id "
        + "   AND l.size <= :size "
        + "   AND l.occupied = false "
        + "ORDER BY pd.distance "
        + "LIMIT 1";

    @Query(
        value = FIND_CLOSES_LOT_FROM_ENTRACE_QUERY, 
        nativeQuery = true
    )
    Lot findClosestLotFromEntrance(@Param("entrance_id") Long entrance_id, @Param("size") ParkingSize parkingSize);
}
