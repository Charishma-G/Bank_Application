package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
   
    public static long getDateDiffernce(Date oldDate, Date newDate, TimeUnit timeUnit) {
        if(newDate.getTime()>oldDate.getTime()){
    	long diffInMillies = newDate.getTime() - oldDate.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
        }else{
        	return -1;
        }
    }
}
