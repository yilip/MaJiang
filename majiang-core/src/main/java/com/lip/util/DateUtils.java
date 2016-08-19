package com.lip.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/** 
 *
 * Description: 日期处理工具类
 *
 * @author lip
 * @version 1.0
 *
 */

public class DateUtils extends TimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 滚动日期
     * 
     * @author 武清明
     * @param srcDate
     *          源日期
     * @param ymd
     *          日期滚动标志（‘y’:年，‘m’:月，‘d’:天）
     * @param rollNum
     *          滚动的数值
     * @return java.util.Date
     * @throws Exception
     */
    public static Date addDate(Date srcDate, String ymd, int rollNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(srcDate);
        if ("y".equals(ymd)) {
            cal.add(Calendar.YEAR, rollNum);
        } else if ("m".equals(ymd)) {
            cal.add(Calendar.MONTH, rollNum);
        } else if ("d".equals(ymd)) {
            cal.add(Calendar.DATE, rollNum);
        }
        return cal.getTime();
    }

    /**
     * @author 武清明 将字符型转化成日期型类型
     * @param str
     *          要转化的字符
     * @return Date 日期型 （如 2009-07-20）
     */
    public static Date stringToDate(String dateSrc, String format) {
        if (dateSrc == null) {
            return null;
        }
        if (dateSrc.equalsIgnoreCase("")) {
            return null;
        }
        String formatTemp = format;
        if (formatTemp == null || "".equals(formatTemp)) {
            formatTemp = DEFAULT_DATE_FORMAT;
        }
        Date resultDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);
            resultDate = dateFormat.parse(dateSrc);
        } catch (Exception e) {
            logger.error("", e);
            System.err.println("字符转化成日期出现异常");
        }
        return resultDate;
    }

    /**
     * @author 武清明 将字符型转化成日期型类型
     * @param Date
     *          要转化的日期型 (如 Fri Sep 05 17:20:28 CST 2008)
     * @return String 字符 （如 2009-07-20）
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        String resultStr = "";
        String formatTemp = format;
        if (formatTemp == null || "".equals(formatTemp)) {
            formatTemp = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);

        resultStr = dateFormat.format(date).trim();

        return resultStr;

    }

    // ----------------------日期计算---------------------------------------------------------------------------------

    /**
     * 是否开始日期在结束日期之前(不包括相等)
     * 
     * @param p_startDate
     * @param p_endDate
     * @return boolean 在结束日期前:ture;否则：false
     */
    public static boolean isStartDateBeforeEndDate(Date p_startDate,
            Date p_endDate) {
        long l_startTime = getMillisOfDate(p_startDate);
        long l_endTime = getMillisOfDate(p_endDate);
        return (l_startTime - l_endTime > (long) 0) ? true : false;
    }

    /**
     * 获取2个字符日期的天数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 天数差
     */
    public static long getDaysOfTowDiffDate(Date p_startDate, Date p_endDate) {
        /*
         * Date l_startDate = toUtilDateFromStrDateByFormat( p_startDate,
         * DEFAULT_DATE_FORMAT); Date l_endDate = toUtilDateFromStrDateByFormat(
         * p_endDate, DEFAULT_DATE_FORMAT);
         */
        long l_startTime = getMillisOfDate(p_startDate);
        long l_endTime = getMillisOfDate(p_endDate);
        long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
        return betweenDays;
    }

    /**
     * 获取2个字符日期的周数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 周数差
     */
    public static long getWeeksOfTowDiffDate(Date p_startDate, Date p_endDate) {
        return getDaysOfTowDiffDate(p_startDate, p_endDate) / 7;
    }

    /**
     * 获取2个字符日期的月数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 月数差
     */
    public static long getMonthsOfTowDiffDate(Date p_startDate, Date p_endDate) {
        return getDaysOfTowDiffDate(p_startDate, p_endDate) / 30;
    }

    /**
     * 获取2个字符日期的年数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 年数差
     */
    public static long getYearsOfTowDiffDate(Date p_startDate, Date p_endDate) {
        return getDaysOfTowDiffDate(p_startDate, p_endDate) / 365;
    }

    /**
     * 在给定的日期基础上添加年，月，日、时，分，秒 例如要再2006－10－21（uitl日期）添加3个月，并且格式化为yyyy-MM-dd格式，
     * 这里调用的方式为 addDate(2006－10－21,3,Calendar.MONTH,"yyyy-MM-dd")
     * 
     * @param p_startDate
     *          给定的日期
     * @param p_count
     *          时间的数量
     * @param p_field
     *          添加的域
     * @param p_format
     *          时间转化格式，例如：yyyy-MM-dd hh:mm:ss 或者yyyy-mm-dd等
     * @return 添加后格式化的时
     */
    public static String addDate(Date p_startDate, int p_count, int p_field,
            String p_format) throws ParseException {

        // 年，月，日、时，分，秒
        int l_year = getYearOfDate(p_startDate);
        int l_month = getMonthOfDate(p_startDate) - 1;
        int l_day = getDayOfDate(p_startDate);
        int l_hour = getHourOfDate(p_startDate);
        int l_minute = getMinuteOfDate(p_startDate);
        int l_second = getSecondOfDate(p_startDate);
        Calendar l_calendar = new GregorianCalendar(l_year, l_month, l_day, l_hour,
                l_minute, l_second);
        l_calendar.add(p_field, p_count);
        return toStrDateFromUtilDateByFormat(l_calendar.getTime(), p_format);
    }

}
