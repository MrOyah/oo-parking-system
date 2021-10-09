package com.oyah.ooparkingsystem.entity.runner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.oyah.ooparkingsystem.constant.ParkingEnum.ParkingSize;
import com.oyah.ooparkingsystem.entity.Entrance;
import com.oyah.ooparkingsystem.entity.Lot;
import com.oyah.ooparkingsystem.entity.Parking;
import com.oyah.ooparkingsystem.entity.ParkingDistance;
import com.oyah.ooparkingsystem.entity.key.ParkingDistanceKey;
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

        Set<Entrance> entrances = new HashSet<>();
        Entrance entranceA = new Entrance("A");
        Entrance entranceB = new Entrance("B");
        Entrance entranceC = new Entrance("C");
        entrances.add(entranceA);
        entrances.add(entranceB);
        entrances.add(entranceC);
        entranceRepository.saveAll(entrances);

        Set<Lot> lots = new HashSet<>();
        Lot lot1 = new Lot(ParkingSize.SP, true);
        Lot lot2 = new Lot(ParkingSize.MP, true);
        Lot lot3 = new Lot(ParkingSize.LP, true);
        Lot lot4 = new Lot(ParkingSize.MP, false);
        Lot lot5 = new Lot(ParkingSize.LP, false);
        lots.add(lot1);
        lots.add(lot2);
        lots.add(lot3);
        lots.add(lot4);
        lots.add(lot5);
        lotRepository.saveAll(lots);

        Set<ParkingDistance> parkingDistances = new HashSet<>();
        //Entrance A
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceA.getId(), lot1.getId()), 1L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceA.getId(), lot2.getId()), 2L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceA.getId(), lot3.getId()), 3L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceA.getId(), lot4.getId()), 4L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceA.getId(), lot5.getId()), 5L));
        //Entrance B
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceB.getId(), lot1.getId()), 5L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceB.getId(), lot2.getId()), 4L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceB.getId(), lot3.getId()), 3L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceB.getId(), lot4.getId()), 2L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceB.getId(), lot5.getId()), 1L));
        //Entrance C
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceC.getId(), lot1.getId()), 3L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceC.getId(), lot2.getId()), 2L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceC.getId(), lot3.getId()), 1L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceC.getId(), lot4.getId()), 2L));
        parkingDistances.add(new ParkingDistance(new ParkingDistanceKey(entranceC.getId(), lot5.getId()), 3L));
        parkingDistanceRepository.saveAll(parkingDistances);
        
        Set<Parking> parkingList = new HashSet<>();
        parkingList.add(new Parking("ABC", LocalDateTime.now().minusHours(5).minusMinutes(6), lot1));
        parkingList.add(new Parking("DEF", LocalDateTime.now(), lot2));
        parkingList.add(new Parking("GHI", LocalDateTime.now(), lot3));
        parkingRepository.saveAll(parkingList);
    }
    
}
