package com.oyah.ooparkingsystem.entity;

import java.util.List;

public class Lot {
    
    public enum Sizes {
        SP,
        MP,
        LP
    }

    private Long id;
    private Sizes size;
    private List<EntranceDistance> entraceDistances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public List<EntranceDistance> getEntraceDistances() {
        return entraceDistances;
    }

    public void setEntraceDistances(List<EntranceDistance> entraceDistances) {
        this.entraceDistances = entraceDistances;
    }
}
