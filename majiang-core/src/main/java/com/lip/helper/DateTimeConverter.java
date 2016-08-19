package com.lip.helper;

import com.lip.util.DateUtils;
import com.lip.util.TimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class DateTimeConverter implements Converter<String, Date> {

    public Date convert(String source) {
        Date result = null;
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            long dateL = Long.parseLong(source);
            return new Date(dateL);
        } catch (Exception e1) {
            if (source.length() == 10) {
                result = DateUtils.stringToDate(source, DateUtils.DEFAULT_DATE_FORMAT);
            } else if (source.length() == 19) {
                try {
                    result = TimeUtils.toUtilDateFromStrDateByFormat(source, TimeUtils.DEFAULT_TIME_FORMAT);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("string conveter to time error, source is[" + source + "]", e);
                }
            } else {
                throw new IllegalArgumentException("string conveter to time error, unsupport this format, source is[" + source + "]");
            }
        }
        return result;
    }

}