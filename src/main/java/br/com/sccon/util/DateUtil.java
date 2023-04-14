package br.com.sccon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
