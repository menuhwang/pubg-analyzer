package com.menu.pubganalyzer.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatetimeFormatterTest {

    @Test
    void offset_1() {
        LocalDateTime datetime1 = LocalDateTime.parse("2023-04-16T07:00:00.000");
        LocalDateTime datetime2 = LocalDateTime.parse("2023-04-16T07:00:00.500");

        long result = DatetimeFormatter.offset(datetime1, datetime2);

        assertEquals(500, result);
    }

    @Test
    void offset_2() {
        LocalDateTime datetime1 = LocalDateTime.parse("2023-04-16T07:00:00.500");
        LocalDateTime datetime2 = LocalDateTime.parse("2023-04-16T07:00:00.000");

        long result = DatetimeFormatter.offset(datetime1, datetime2);

        assertEquals(500, result);
    }

    @Test
    void offsetMinSec() {
        LocalDateTime datetime1 = LocalDateTime.parse("2023-04-16T07:05:30.500");
        LocalDateTime datetime2 = LocalDateTime.parse("2023-04-16T07:00:00.300");

        String offset = DatetimeFormatter.offsetMinSec(datetime1, datetime2);

        assertEquals("5:30", offset);
    }

    @Test
    void offsetMinSecMill_1() {
        LocalDateTime datetime1 = LocalDateTime.parse("2023-04-16T07:05:30.500");
        LocalDateTime datetime2 = LocalDateTime.parse("2023-04-16T07:00:00.250");

        String offset = DatetimeFormatter.offsetMinSecMill(datetime1, datetime2);

        assertEquals("05:30.250", offset);
    }

    @Test
    void offsetMinSecMill_2() {
        LocalDateTime datetime1 = LocalDateTime.parse("2023-04-16T07:05:05.500");
        LocalDateTime datetime2 = LocalDateTime.parse("2023-04-16T07:00:00.125");

        String offset = DatetimeFormatter.offsetMinSecMill(datetime1, datetime2);

        assertEquals("05:05.375", offset);
    }
}