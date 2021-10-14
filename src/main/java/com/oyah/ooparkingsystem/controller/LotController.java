package com.oyah.ooparkingsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.datamodel.LotData;
import com.oyah.ooparkingsystem.entity.datamodel.RequestData;
import com.oyah.ooparkingsystem.entity.datamodel.ResponseData;
import com.oyah.ooparkingsystem.service.LotService;

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
public class LotController {
    
    @Autowired
    private LotService lotService;

    @GetMapping("/lots")
    public ResponseEntity<ResponseData<List<LotData>>> getAll() {
        List<Lot> lots = lotService.getAll();

        if (lots.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No parking found.");
        }

        List<LotData> lotDataList = lots.stream()
            .map(p -> LotData.fromEntity(p))
            .collect(Collectors.toList());

        ResponseData<List<LotData>> responseData = new ResponseData<List<LotData>>(lotDataList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/lots/{id}")
    public ResponseEntity<ResponseData<LotData>> getById(@PathVariable Long id) {
        Lot lot = lotService.findById(id);
        return new ResponseEntity<>(createFrom(LotData.fromEntity(lot)), HttpStatus.OK);
    }
    
    @PostMapping("/lots")
    public ResponseEntity<ResponseData<LotData>> create(@Valid @RequestBody RequestData<LotData> requestData) {
        Lot lotRequestData = requestData.getData().toEntity();
        Lot lot = lotService.create(lotRequestData);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(lot.getId())
            .toUri();
		return ResponseEntity.created(location).build();
    }

    @PutMapping("/lots/{id}")
    public ResponseEntity<ResponseData<LotData>> update(@PathVariable Long id, @Valid @RequestBody RequestData<LotData> requestData) {
        Lot lotRequestData = requestData.getData().toEntity();
        Lot lot = lotService.update(id, lotRequestData);
        return new ResponseEntity<>(createFrom(LotData.fromEntity(lot)), HttpStatus.OK);
    }

    @DeleteMapping("/lots/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        lotService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseData<LotData> createFrom(LotData lotData) {
        return new ResponseData<LotData>(lotData);
    }
}
