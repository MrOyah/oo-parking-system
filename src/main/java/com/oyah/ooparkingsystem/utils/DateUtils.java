package com.oyah.ooparkingsystem.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static long getDateDiffInHours(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.HOURS.between(startDate, endDate);
    }

    public static long getDateDiffInDays(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
}
