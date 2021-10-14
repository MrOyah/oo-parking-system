package com.oyah.ooparkingsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.datamodel.RequestData;
import com.oyah.ooparkingsystem.entity.datamodel.ResponseData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingUpdateData;
import com.oyah.ooparkingsystem.entity.datamodel.ParkingCreateData;
import com.oyah.ooparkingsystem.service.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class ParkingController {
    
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public ResponseEntity<ResponseData<List<ParkingData>>> getAll() {
        List<Parking> parkingList = parkingService.getAll();
        if (parkingList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No parking found.");
        }

        List<ParkingData> parkingDataList = parkingList.stream()
            .map(p -> ParkingData.fromEntity(p))
            .collect(Collectors.toList());
        ResponseData<List<ParkingData>> responseData = new ResponseData<List<ParkingData>>(parkingDataList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<ResponseData<ParkingData>> getById(@PathVariable long id) {
        Parking parking = parkingService.findById(id);
        ParkingData parkingData = ParkingData.fromEntity(parking);
        ResponseData<ParkingData> responseData = new ResponseData<ParkingData>(parkingData);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/parking")
    public ResponseEntity<Object> create(@Valid @RequestBody RequestData<ParkingCreateData> dataRequest) {
        ParkingCreateData parkingEntryDataRequest = dataRequest.getData();
        Parking parking = parkingService.create(parkingEntryDataRequest);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(parking.getId())
            .toUri();
		return ResponseEntity.created(location).build();
    }

    @PutMapping("/parking/{id}")
    public ResponseEntity<ResponseData<ParkingData>> update(@PathVariable Long id, @Valid @RequestBody RequestData<ParkingUpdateData> dataRequest) {
        ParkingUpdateData parkingEntryDataRequest = dataRequest.getData();
        Parking parking = parkingService.update(id, parkingEntryDataRequest);
        ParkingData parkingData = ParkingData.fromEntity(parking);
        ResponseData<ParkingData> responseData = new ResponseData<ParkingData>(parkingData);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @PostMapping("/parking/{id}/unpark") 
    public ResponseEntity<ResponseData<ParkingData>> unpark(@PathVariable Long id) {
        Parking parking = parkingService.unparkAndSave(id);
        ParkingData parkingData = ParkingData.fromEntity(parking);
        ResponseData<ParkingData> responseData = new ResponseData<ParkingData>(parkingData);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/parking/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        parkingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
