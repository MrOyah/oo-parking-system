package com.oyah.ooparkingsystem.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static final Long HOURS_IN_DAY = 24L;
    public static final Double SECONDS_IN_DAY = 3600.0;

    public static Long getDateDiffInHoursInCeil(LocalDateTime startDate, LocalDateTime endDate) {
        Double hours = ChronoUnit.SECONDS.between(startDate, endDate) / SECONDS_IN_DAY;
        return (long)Math.ceil(hours);
    }

    public static Long getDateDiffInHoursExcludeDays(LocalDateTime startDate, LocalDateTime endDate) {
        return getDateDiffInHoursInCeil(startDate, endDate) % HOURS_IN_DAY;
    }

    public static Long getDateDiffInDays(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
}
