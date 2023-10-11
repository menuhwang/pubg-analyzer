package com.menu.pubganalyzer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {
    public static LocalDateTime parse(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME);
    }
}
