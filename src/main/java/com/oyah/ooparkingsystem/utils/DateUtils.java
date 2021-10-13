package com.oyah.ooparkingsystem.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static Long getDateDiffInHours(LocalDateTime startDate, LocalDateTime endDate) {
        Double hours = ChronoUnit.MINUTES.between(startDate, endDate) / 60.0;
        return (long)Math.ceil(hours);
    }

    public static long getDateDiffInDays(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
}
