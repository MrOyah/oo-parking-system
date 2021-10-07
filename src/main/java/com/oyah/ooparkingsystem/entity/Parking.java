package com.oyah.ooparkingsystem.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class Parking {

    public static final Long FLAT_HOUR = 3L;
    public static final Double FLAT_RATE = 40.0;
    public static final Double SP_SUCCEED_RATE = 20.0;
    public static final Double MP_SUCCEED_RATE = 60.0;
    public static final Double LP_SUCCEED_RATE = 100.0;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plate_no")
    private String plateNo;

    @Column(name = "time_in")
    private LocalDateTime timeIn = LocalDateTime.now();

    @Column(name = "time_out")
    private LocalDateTime timeOut;

    @OneToOne
    @JoinColumn(name = "lot_id")
    @JsonIgnoreProperties({"parking_distances", "occupied"})
    private Lot lot;

    private Boolean paid = false;

    @OneToOne
    @JoinColumn(name = "previous_parking_id")
    private Parking previousParking;

    public Parking(String plateNo, LocalDateTime timeIn, Lot lot) {
        this(plateNo, lot);
        this.timeIn = timeIn;
    }

    public Parking(String plateNo, Lot lot, Parking previousParking) {
        this(plateNo, lot);
        this.previousParking = previousParking;
    }

    public Parking(String plateNo, Lot lot) {
        this.plateNo = plateNo;
        this.lot = lot;
    }
}
