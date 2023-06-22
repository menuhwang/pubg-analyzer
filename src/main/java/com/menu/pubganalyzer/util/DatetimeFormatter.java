package com.menu.pubganalyzer.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatetimeFormatter {
    protected DatetimeFormatter() {
    }

    public static String offsetMinSec(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        long offset = offset(dateTime1, dateTime2);
        offset /= 1000;
        return String.format("%02d:%02d", offset / 60, offset % 60);
    }

    public static String offsetMinSecMill(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        long offset = offset(dateTime1, dateTime2);

        return String.format("%02d:%02d.%d", offset / 1000 / 60, offset / 1000 % 60, offset % 1000);
    }

    public static String UTCtoKST(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        ZonedDateTime createdAtKor = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        return createdAtKor.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    protected static long offset(LocalDateTime datetime1, LocalDateTime datetime2) {
        return Math.abs(datetime1.until(datetime2, ChronoUnit.MILLIS));
    }

    private static class DatetimeFormatterHolder {
        private static final DatetimeFormatter INSTANCE = new DatetimeFormatter();
    }

    public static DatetimeFormatter getInstance() {
        return DatetimeFormatterHolder.INSTANCE;
    }
}
