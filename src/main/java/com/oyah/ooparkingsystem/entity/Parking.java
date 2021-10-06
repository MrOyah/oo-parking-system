package com.oyah.ooparkingsystem.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oyah.ooparkingsystem.utils.DateUtils;

public class Parking {

    public static final Long FLAT_HOUR = 3L;
    public static final Double FLAT_RATE = 40.0;
    public static final Double SP_SUCCEED_RATE = 20.0;
    public static final Double MP_SUCCEED_RATE = 60.0;
    public static final Double LP_SUCCEED_RATE = 100.0;
    
    private Long id;
    private String plateNo;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private Lot lot;
    private Boolean paid;
    private Parking lastParking;

    public Parking(Long id, String plateNo, LocalDateTime timeIn, Lot lot, Parking lastParking) {
        this.id = id;
        this.plateNo = plateNo;
        this.timeIn = timeIn;
        this.paid = false;
        this.lot = lot;
        this.lastParking = lastParking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }
    
    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Parking getLastParking() {
        return lastParking;
    }

    public void setLastParking(Parking lastParking) {
        this.lastParking = lastParking;
    }

    @JsonIgnore
    public Double getSuceedRate() {
        switch (lot.getSize()) {
            case SP:
                return SP_SUCCEED_RATE;
            case MP:
                return MP_SUCCEED_RATE;
            default:
                return LP_SUCCEED_RATE;
        }
    }

    @JsonIgnore
    public long getSuceedHours() {
        Long totalHours = DateUtils.getDateDiffInHours(this.timeIn, this.timeOut);
        return totalHours > FLAT_HOUR ? (totalHours - FLAT_HOUR) : 0L;
    }

    @JsonIgnore
    public Double getTotalCharge() {
        return FLAT_RATE + (getSuceedHours() * getSuceedRate());
    }
}
