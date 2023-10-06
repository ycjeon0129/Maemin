package com.tft.payservice.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class TimeUtil {

    public static String parseTimestamp(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }

    public static LocalDateTime getHoursLater(int hour) {
        return LocalDateTime.now().plusHours(hour);
    }

}
