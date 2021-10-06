package com.oyah.ooparkingsystem.entity;

import java.util.Date;

public class Parking {
    
    private Long id;
    private String plateNo;
    private Date timeIn;
    private Date timeOut;
    private Lot lot;
    private Boolean paid;
    private Parking lastParking;

    

    public Parking(Long id, String plateNo, Date timeIn, Date timeOut, Boolean paid) {
        this.id = id;
        this.plateNo = plateNo;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.paid = paid;
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

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
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
}
