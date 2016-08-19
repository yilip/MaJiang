package com.lip.helper;

import com.lip.util.DateUtils;
import com.lip.util.TimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarConverter implements Converter<String, Calendar> {

    public Calendar convert(String source) {
        Date date = null;
        if (source == null || source.length() == 0) {
            return null;
        } else if (source.length() == 10) {
            date = DateUtils.stringToDate(source, DateUtils.DEFAULT_DATE_FORMAT);
        } else if (source.length() == 19) {
            try {
                date = TimeUtils.toUtilDateFromStrDateByFormat(source, TimeUtils.DEFAULT_TIME_FORMAT);
            } catch (ParseException e) {
                throw new IllegalArgumentException("string conveter to time error, source is[" + source + "]", e);
            }
        } else {
            throw new IllegalArgumentException("string conveter to time error, unsupport this format, source is[" + source + "]");
        }
        Calendar result = null;
        if (date != null) {
            result = GregorianCalendar.getInstance();
            result.setTime(date);
        }
        return result;
    }
    
}