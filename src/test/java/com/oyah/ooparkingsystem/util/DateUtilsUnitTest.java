package com.oyah.ooparkingsystem.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateUtilsUnitTest {

    @Test
    void whenDurationsIs4Hours_thenTotalDiffInHoursIs4Hours() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(4L);

        assertEquals(4L, DateUtils.getDateDiffInHoursInCeil(startDate, endDate));
    }

    @Test
    void whenDurationsIs4Hours1Minute_thenTotalDiffInHoursIs5Hours() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(4L).plusMinutes(1L);

        assertEquals(5L, DateUtils.getDateDiffInHoursInCeil(startDate, endDate));
    }

    @Test
    void whenDurationsIs1Day4Hours_thenTotalDiffInHoursExcludingDaysIs4Hours() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1L).plusHours(4L);

        assertEquals(4L, DateUtils.getDateDiffInHoursExcludeDays(startDate, endDate));
    }

    @Test
    void whenDurationsIs1Day4Hours1Minute_thenTotalDiffInHoursExcludingDaysIs5Hours() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1L).plusHours(4L).plusMinutes(1);

        assertEquals(5L, DateUtils.getDateDiffInHoursExcludeDays(startDate, endDate));
    }

    @Test
    void whenDurationsIs1Day_thenTotalDiffInDaysIs1Day() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1L);

        assertEquals(1L, DateUtils.getDateDiffInDays(startDate, endDate));
    }

    @Test
    void whenDurationsIs2Days1Minute_thenTotalDiffInDaysIs2Days() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(2L).plusMinutes(1);

        assertEquals(2L, DateUtils.getDateDiffInDays(startDate, endDate));
    }

    @Test
    void whenDurationsIs3Days1Second_thenTotalDiffInDaysIs2Days() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(3L).plusSeconds(1);

        assertEquals(3L, DateUtils.getDateDiffInDays(startDate, endDate));
    }

    
}
