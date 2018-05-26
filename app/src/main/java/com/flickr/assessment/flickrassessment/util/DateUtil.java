package com.flickr.assessment.flickrassessment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class to handle calender date data.
 */
public class DateUtil {

    public static final String FLICKR_API_DATE_TAKEN = "yyyy-MM-dd'T'HH:mm:SSSXXX";
    public static final String PHOTO_DATE_TAKEN = "dd MMM yyyy";

    public static Date parseFlickrDateTaken(String dateData) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FLICKR_API_DATE_TAKEN, Locale.ENGLISH);
        try {
            date = simpleDateFormat.parse(dateData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateInFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }
}
