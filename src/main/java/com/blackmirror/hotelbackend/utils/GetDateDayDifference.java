package com.blackmirror.hotelbackend.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GetDateDayDifference {

    public static long getDateDayDifference(Date date1, Date date2)  {

        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;

    }
}
