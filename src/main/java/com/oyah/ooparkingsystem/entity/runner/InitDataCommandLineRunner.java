package com.oyah.ooparkingsystem.entity.runner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.constant.ParkingEnum.VehicleSize;
import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.ParkingDistance;
import com.oyah.ooparkingsystem.repository.EntranceRepository;
import com.oyah.ooparkingsystem.repository.LotRepository;
import com.oyah.ooparkingsystem.repository.ParkingDistanceRepository;
import com.oyah.ooparkingsystem.repository.ParkingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataCommandLineRunner implements CommandLineRunner{

    @Autowired
    private EntranceRepository entranceRepository;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private ParkingDistanceRepository parkingDistanceRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Entrance> entrances = new ArrayList<>();
        Entrance entranceA = new Entrance("A");
        Entrance entranceB = new Entrance("B");
        Entrance entranceC = new Entrance("C");
        entrances.add(entranceA);
        entrances.add(entranceB);
        entrances.add(entranceC);
        entranceRepository.saveAll(entrances);

        List<Lot> lots = new ArrayList<>();
        Lot lot1 = new Lot(ParkingSize.SP, true);
        Lot lot2 = new Lot(ParkingSize.MP, true);
        Lot lot3 = new Lot(ParkingSize.LP, true);
        
        Lot lot4 = new Lot(ParkingSize.SP, true);
        Lot lot5 = new Lot(ParkingSize.MP, true);
        Lot lot6 = new Lot(ParkingSize.LP, true);

        Lot lot7 = new Lot(ParkingSize.SP, true);
        Lot lot8 = new Lot(ParkingSize.MP, true);
        Lot lot9 = new Lot(ParkingSize.LP, true);

        Lot lot10 = new Lot(ParkingSize.SP, true);

        Lot lot11 = new Lot(ParkingSize.SP, false);
        Lot lot12 = new Lot(ParkingSize.MP, false);
        Lot lot13 = new Lot(ParkingSize.LP, false);
        
        Lot lot14 = new Lot(ParkingSize.SP, false);
        Lot lot15 = new Lot(ParkingSize.MP, false);

        lots.add(lot1);
        lots.add(lot2);
        lots.add(lot3);
        lots.add(lot4);
        lots.add(lot5);
        lots.add(lot6);
        lots.add(lot7);
        lots.add(lot8);
        lots.add(lot9);
        lots.add(lot10);
        lots.add(lot11);
        lots.add(lot12);
        lots.add(lot13);
        lots.add(lot14);
        lots.add(lot15);
        lotRepository.saveAll(lots);

        List<ParkingDistance> parkingDistances = new ArrayList<>();
        //Entrance A
        parkingDistances.add(new ParkingDistance(entranceA, lot1, 1L));
        parkingDistances.add(new ParkingDistance(entranceA, lot2, 2L));
        parkingDistances.add(new ParkingDistance(entranceA, lot3, 3L));
        parkingDistances.add(new ParkingDistance(entranceA, lot4, 4L));
        parkingDistances.add(new ParkingDistance(entranceA, lot5, 5L));
        parkingDistances.add(new ParkingDistance(entranceA, lot6, 6L));
        parkingDistances.add(new ParkingDistance(entranceA, lot7, 7L));
        parkingDistances.add(new ParkingDistance(entranceA, lot8, 8L));
        parkingDistances.add(new ParkingDistance(entranceA, lot9, 9L));
        parkingDistances.add(new ParkingDistance(entranceA, lot10, 10L));
        parkingDistances.add(new ParkingDistance(entranceA, lot11, 11L));
        parkingDistances.add(new ParkingDistance(entranceA, lot12, 12L));
        parkingDistances.add(new ParkingDistance(entranceA, lot13, 13L));
        parkingDistances.add(new ParkingDistance(entranceA, lot14, 14L));
        parkingDistances.add(new ParkingDistance(entranceA, lot15, 15L));
        
        //Entrance B
        parkingDistances.add(new ParkingDistance(entranceB, lot1, 1L));
        parkingDistances.add(new ParkingDistance(entranceB, lot2, 2L));
        parkingDistances.add(new ParkingDistance(entranceB, lot3, 3L));
        parkingDistances.add(new ParkingDistance(entranceB, lot4, 4L));
        parkingDistances.add(new ParkingDistance(entranceB, lot5, 5L));
        parkingDistances.add(new ParkingDistance(entranceB, lot6, 6L));
        parkingDistances.add(new ParkingDistance(entranceB, lot7, 7L));
        parkingDistances.add(new ParkingDistance(entranceB, lot8, 8L));
        parkingDistances.add(new ParkingDistance(entranceB, lot9, 9L));
        parkingDistances.add(new ParkingDistance(entranceB, lot10, 10L));
        parkingDistances.add(new ParkingDistance(entranceB, lot11, 11L));
        parkingDistances.add(new ParkingDistance(entranceB, lot12, 12L));
        parkingDistances.add(new ParkingDistance(entranceB, lot13, 13L));
        parkingDistances.add(new ParkingDistance(entranceB, lot14, 14L));
        parkingDistances.add(new ParkingDistance(entranceB, lot15, 15L));

        //Entrance C
        parkingDistances.add(new ParkingDistance(entranceC, lot1, 1L));
        parkingDistances.add(new ParkingDistance(entranceC, lot2, 2L));
        parkingDistances.add(new ParkingDistance(entranceC, lot3, 3L));
        parkingDistances.add(new ParkingDistance(entranceC, lot4, 4L));
        parkingDistances.add(new ParkingDistance(entranceC, lot5, 5L));
        parkingDistances.add(new ParkingDistance(entranceC, lot6, 6L));
        parkingDistances.add(new ParkingDistance(entranceC, lot7, 7L));
        parkingDistances.add(new ParkingDistance(entranceC, lot8, 8L));
        parkingDistances.add(new ParkingDistance(entranceC, lot9, 9L));
        parkingDistances.add(new ParkingDistance(entranceC, lot10, 10L));
        parkingDistances.add(new ParkingDistance(entranceC, lot11, 11L));
        parkingDistances.add(new ParkingDistance(entranceC, lot12, 12L));
        parkingDistances.add(new ParkingDistance(entranceC, lot13, 13L));
        parkingDistances.add(new ParkingDistance(entranceC, lot14, 14L));
        parkingDistances.add(new ParkingDistance(entranceC, lot15, 15L));
        parkingDistanceRepository.saveAll(parkingDistances);
        
        LocalDateTime timeIn = LocalDateTime.now();
        List<Parking> parkingList = new ArrayList<>();
        //FLAT RATE
        parkingList.add(new Parking("PLATE_001", VehicleSize.S, timeIn.minusHours(1L), lot1));
        parkingList.add(new Parking("PLATE_002", VehicleSize.M, timeIn.minusHours(2L), lot2));
        parkingList.add(new Parking("PLATE_003", VehicleSize.L, timeIn.minusHours(2L), lot3));

        //EXCEEDING RATE
        parkingList.add(new Parking("PLATE_004", VehicleSize.S, timeIn.minusHours(3L), lot4));
        parkingList.add(new Parking("PLATE_005", VehicleSize.M, timeIn.minusHours(4L), lot5));
        parkingList.add(new Parking("PLATE_006", VehicleSize.L, timeIn.minusHours(5L), lot6));

        //EXCEEDING 24 HOUR
        parkingList.add(new Parking("PLATE_007", VehicleSize.S, timeIn.minusDays(1L), lot7));
        parkingList.add(new Parking("PLATE_008", VehicleSize.M, timeIn.minusDays(1L).plusHours(1), lot8));
        parkingList.add(new Parking("PLATE_009", VehicleSize.L, timeIn.minusDays(23), lot9));

        //PARKING HOUR ROUND UP
        parkingList.add(new Parking("PLATE_010", VehicleSize.S, timeIn.plusHours(3), lot10));

        //CONTINOUS PARKING
        parkingList.add(new Parking("PLATE_011", VehicleSize.S, timeIn.plusHours(2), lot11));

        parkingRepository.saveAll(parkingList);
    }
    
}
