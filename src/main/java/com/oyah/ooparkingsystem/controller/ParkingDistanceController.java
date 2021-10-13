package com.oyah.ooparkingsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.ParkingDistance;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingDistanceData;
import com.oyah.ooparkingsystem.entity.datamodel.DataRequest;
import com.oyah.ooparkingsystem.service.ParkingDistanceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ParkingDistanceController {

    @Autowired
    private ParkingDistanceService parkingDistanceService;

    @GetMapping("/entrances/{entranceId}/parking-distances")
    public ResponseEntity<List<ParkingDistanceData>> getAllByEntranceId(@PathVariable Long entranceId) {
        List<ParkingDistance> parkingDistances = parkingDistanceService.getAllByEntranceId(entranceId);
        List<ParkingDistanceData> parkingDistanceDataList = parkingDistances.stream()
            .map(pd -> entityToData(pd))
            .collect(Collectors.toList());
        return new ResponseEntity<>(parkingDistanceDataList, HttpStatus.OK);
    }

    @PostMapping("/entrances/{entranceId}/parking-distances")
    public ResponseEntity<List<ParkingDistanceData>> createParkingDistancesByEntranceId(@PathVariable Long entranceId, @Valid @RequestBody DataRequest<List<ParkingDistanceData>> requestData) {
        List<ParkingDistance> newParkingDistances = parkingDistanceService.saveAll(entranceId, requestData.getData());
        List<ParkingDistanceData> newParkingDistanceDataList = newParkingDistances.stream()
            .map(pd -> entityToData(pd))
            .collect(Collectors.toList());
        return new ResponseEntity<>(newParkingDistanceDataList, HttpStatus.OK);
    }

    @GetMapping("/entrances/{entranceId}/parking-distances/{lotId}")
    public ResponseEntity<ParkingDistanceData> getByEntranceIdAndLotId(@PathVariable Long entranceId, @PathVariable Long lotId) {
        ParkingDistance parkingDistance = parkingDistanceService.getByEntranceIdAndLotId(entranceId, lotId);
        return new ResponseEntity<>(entityToData(parkingDistance), HttpStatus.OK);
    }

    private ParkingDistanceData entityToData(ParkingDistance parkingDistance) {
        return ParkingDistanceData.builder()
            .lotId(parkingDistance.getLot().getId())
            .distance(parkingDistance.distance)
            .build();
    }
}
