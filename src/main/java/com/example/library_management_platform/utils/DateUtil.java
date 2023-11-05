package com.example.library_management_platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertToDate(String dateStr) throws ParseException {
       return sdf.parse(dateStr);
    }

}
