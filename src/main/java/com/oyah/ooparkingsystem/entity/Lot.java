package com.oyah.ooparkingsystem.entity;

import java.util.List;

import com.oyah.ooparkingsystem.entity.Lot.Park.Size;

public class Lot {
    
    public static class Park {
        public enum Size {
            SP,
            MP,
            LP;
        }
    }

    private Long id;
    private Size size;
    private List<EntranceDistance> entraceDistances;

    public Lot(Long id, Size size) {
        this.id = id;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<EntranceDistance> getEntraceDistances() {
        return entraceDistances;
    }

    public void setEntraceDistances(List<EntranceDistance> entraceDistances) {
        this.entraceDistances = entraceDistances;
    }
}
