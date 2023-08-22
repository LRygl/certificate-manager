package com.example.calculatesignature.Utils;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DatetimeUtils {


    public static String getCurrentFormatedDate(){
        ZonedDateTime dateTime = ZonedDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
        return dateTime.format(formatter);
    }
}
