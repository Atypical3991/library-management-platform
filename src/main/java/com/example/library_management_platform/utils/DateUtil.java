package com.example.library_management_platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //convertToDate :- An Util method to convert date string of format DD/MM/YYYY e.g. 10/11/2021  into `java.util.date` object.
    public static Date convertToDate(String dateStr) throws ParseException {
        return sdf.parse(dateStr);
    }

}
