package com.oyah.ooparkingsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.datamodel.EntranceData;
import com.oyah.ooparkingsystem.entity.datamodel.RequestData;
import com.oyah.ooparkingsystem.entity.datamodel.ResponseData;
import com.oyah.ooparkingsystem.service.EntranceService;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class EntranceController {

    @Autowired
    private EntranceService entranceService;

    @GetMapping("/entrances")
    public ResponseEntity<ResponseData<List<EntranceData>>> getAll() {
        List<Entrance> entrances = entranceService.getAll();
        if (entrances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EntranceData> entranceDataList = entrances.stream()
            .map(e -> EntranceData.fromEntity(e))
            .collect(Collectors.toList());

        ResponseData<List<EntranceData>> responseData = new ResponseData<List<EntranceData>>(entranceDataList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/entrances/{id}")
    public ResponseEntity<ResponseData<EntranceData>> getById(@PathVariable Long id) {
        Entrance entrance = entranceService.findById(id);
        EntranceData entranceData = EntranceData.fromEntity(entrance);
        ResponseData<EntranceData> responseData = new ResponseData<EntranceData>(entranceData);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/entrances")
    public ResponseEntity<ResponseData<EntranceData>> create(@Valid @RequestBody RequestData<EntranceData> requestData) {
        Entrance entranceRequest = requestData.getData().toEntity();
        Entrance newEntrance = entranceService.create(entranceRequest);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newEntrance.getId())
            .toUri();
		return ResponseEntity.created(location).build();
    }

    @PutMapping("/entrances/{id}")
    public ResponseEntity<ResponseData<EntranceData>> update(@PathVariable Long id, @Valid @RequestBody RequestData<EntranceData> requestData) {
        Entrance entranceRequest = requestData.getData().toEntity();
        Entrance updatedEntrance = entranceService.update(id, entranceRequest);
        EntranceData entranceData = EntranceData.fromEntity(updatedEntrance);
        ResponseData<EntranceData> responseData = new ResponseData<EntranceData>(entranceData);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/entrances/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        entranceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
