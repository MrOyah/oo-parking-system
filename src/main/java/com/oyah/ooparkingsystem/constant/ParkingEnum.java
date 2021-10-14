package com.oyah.ooparkingsystem.constant;

import java.util.HashMap;
import java.util.Map;

public class ParkingEnum {
    public enum ParkingSize {
        SP("Small"),
        MP("Medium"),
        LP("Large");

        final String label;

        private ParkingSize(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }

        private static final Map<String, ParkingSize> BY_LABEL = new HashMap<>();
    
        static {
            for (ParkingSize e: values()) {
                BY_LABEL.put(e.label, e);
            }
        }
        
        public static ParkingSize valueOfLabel(String label) {
            return BY_LABEL.get(label);
        }
    }

    public enum VehicleSize {
        S("Small"),
        M("Medium"),
        L("Large");

        final String label;

        private VehicleSize(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }

        private static final Map<String, VehicleSize> BY_LABEL = new HashMap<>();
    
        static {
            for (VehicleSize e: values()) {
                BY_LABEL.put(e.label, e);
            }
        }
        
        public static VehicleSize valueOfLabel(String label) {
            return BY_LABEL.get(label);
        }
    }
}
