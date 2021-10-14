package com.oyah.ooparkingsystem.controller;

import java.util.Arrays;
import java.util.List;

import com.oyah.ooparkingsystem.constant.ParkingEnum.VehicleSize;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.repository.ParkingRepository;
import com.oyah.ooparkingsystem.service.ParkingService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingService parkingService;
    
    @Test
    void getAllRecords_success() throws Exception {
        Parking RECORD_1 = new Parking("ABC", VehicleSize.S);
        Parking RECORD_2 = new Parking("DEF", VehicleSize.M);
        Parking RECORD_3 = new Parking("GHI", VehicleSize.L);
        List<Parking> records = Arrays.asList(RECORD_1, RECORD_2, RECORD_3);
    
        Mockito.when(parkingRepository.findAll()).thenReturn(records);
    
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/parking")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[2].plate_no", is("GHI")));
    }
}
