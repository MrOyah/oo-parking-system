package com.oyah.ooparkingsystem.constant;

import java.util.HashMap;
import java.util.Map;

public class ParkingEnum {
    public enum ParkingSize {
        SP,
        MP,
        LP;
    }

    public enum VehicleSize {
        S("Small"),
        M("Medium"),
        P("Large");

        public final String label;

        private VehicleSize(String label) {
            this.label = label;
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
