package com.lip.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/** 
 * Copyright (C)
 * 
 * Description: 时间处理工具类
 *
 * @author lip
 * @version 1.0
 *
 */

public abstract class TimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // ---当前日期的年，月，日，时，分，秒
    public static Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    int date = now.get(Calendar.DAY_OF_MONTH);
    int month = now.get(Calendar.MONTH) + 1;
    int hour = now.get(Calendar.HOUR);
    int min = now.get(Calendar.MINUTE);
    int sec = now.get(Calendar.SECOND);

    // -------------------------------日期类型转换---------------------------------------------------------------------------
    /**
     * 字符型日期转化util.Date型日期
     * 
     * @Param:p_strDate 字符型日期
     * @param p_format
     *          格式:"yyyy-MM-dd" / "yyyy-MM-dd HH:mm:ss"
     * @Return:java.util.Date util.Date型日期
     * @Throws: ParseException
     */
    public static Date toUtilDateFromStrDateByFormat(String p_strDate,
            String p_format) throws ParseException {
        Date l_date = null;
        java.text.DateFormat df = new SimpleDateFormat(p_format);
        if (p_strDate != null && (!"".equals(p_strDate)) && p_format != null
                && (!"".equals(p_format))) {
            l_date = df.parse(p_strDate);
        }
        return l_date;
    }

    /**
     * 字符型日期转化成sql.Date型日期
     *
     * @param p_strDate
     *          字符型日期
     * @return java.sql.Date sql.Date型日期
     * @throws ParseException
     */
    public static java.sql.Date toSqlDateFromStrDate(String p_strDate)
            throws ParseException {
        java.sql.Date returnDate = null;
        java.text.DateFormat sdf = new SimpleDateFormat();
        if (p_strDate != null && (!"".equals(p_strDate))) {
            returnDate = new java.sql.Date(sdf.parse(p_strDate).getTime());
        }
        return returnDate;
    }

    /**
     * util.Date型日期转化指定格式的字符串型日期
     *
     * @param p_date
     *          Date
     * @param p_format
     *          String 格式1:"yyyy-MM-dd" 格式2:"yyyy-MM-dd hh:mm:ss EE"
     *          格式3:"yyyy年MM月dd日 hh:mm:ss EE" 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写
     * @return String
     */
    public static String toStrDateFromUtilDateByFormat(Date p_utilDate,
            String p_format) throws ParseException {
        String l_result = "";
        if (p_utilDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(p_format);
            l_result = sdf.format(p_utilDate);
        }
        return l_result;
    }

    /**
     * util.Date型日期转化转化成Calendar日期
     *
     * @param p_utilDate
     *          Date
     * @return Calendar
     */
    public static Calendar toCalendarFromUtilDate(Date p_utilDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_utilDate);
        return c;
    }

    /**
     * util.Date型日期转化sql.Date(年月日)型日期
     *
     * @Param: p_utilDate util.Date型日期
     * @Return: java.sql.Date sql.Date型日期
     */
    public static java.sql.Date toSqlDateFromUtilDate(Date p_utilDate) {
        java.sql.Date returnDate = null;
        if (p_utilDate != null) {
            returnDate = new java.sql.Date(p_utilDate.getTime());
        }
        return returnDate;
    }

    /**
     * util.Date型日期转化sql.Time(时分秒)型日期
     *
     * @Param: p_utilDate util.Date型日期
     * @Return: java.sql.Time sql.Time型日期
     */
    public static java.sql.Time toSqlTimeFromUtilDate(Date p_utilDate) {
        java.sql.Time returnDate = null;
        if (p_utilDate != null) {
            returnDate = new java.sql.Time(p_utilDate.getTime());
        }
        return returnDate;
    }

    /**
     * util.Date型日期转化sql.Date(时分秒)型日期
     *
     * @Param: p_utilDate util.Date型日期
     * @Return: java.sql.Timestamp sql.Timestamp型日期
     */
    public static java.sql.Timestamp toSqlTimestampFromUtilDate(
            Date p_utilDate) {
        java.sql.Timestamp returnDate = null;
        if (p_utilDate != null) {
            returnDate = new java.sql.Timestamp(p_utilDate.getTime());
        }
        return returnDate;
    }

    /**
     * sql.Date型日期转化util.Date型日期
     *
     * @Param: sqlDate sql.Date型日期
     * @Return: java.util.Date util.Date型日期
     */
    public static Date toUtilDateFromSqlDate(java.sql.Date p_sqlDate) {
        Date returnDate = null;
        if (p_sqlDate != null) {
            returnDate = new Date(p_sqlDate.getTime());
        }
        return returnDate;
    }

    // -----------------获取指定日期的年份，月份，日份，小时，分，秒，毫秒----------------------------
    /**
     * 获取指定日期的年份
     *
     * @param p_date
     *          util.Date日期
     * @return int 年份
     */
    public static int getYearOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的月份
     *
     * @param p_date
     *          util.Date日期
     * @return int 月份
     */
    public static int getMonthOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的日份
     *
     * @param p_date
     *          util.Date日期
     * @return int 日份
     */
    public static int getDayOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期的小时
     *
     * @param p_date
     *          util.Date日期
     * @return int 日份
     */
    public static int getHourOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定日期的分钟
     *
     * @param p_date
     *          util.Date日期
     * @return int 分钟
     * @author zhuqx
     * @Date: 2006-10-31
     */
    public static int getMinuteOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取指定日期的秒钟
     *
     * @param p_date
     *          util.Date日期
     * @return int 秒钟
     */
    public static int getSecondOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param p_date
     *          util.Date日期
     * @return long 毫秒
     */
    public static long getMillisOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.getTimeInMillis();
    }

    // -----------------获取当前/系统日期(指定日期格式)-----------------------------------------------------------------------------------
    /**
     * 获取指定日期格式当前日期的字符型日期
     *
     * @param p_format
     *          日期格式 格式1:"yyyy-MM-dd" 格式2:"yyyy-MM-dd HH:mm:ss EE"
     *          格式3:"yyyy年MM月dd日 HH:mm:ss EE" 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写
     * @return String 当前时间字符串
     */
    public static String getNowOfDateByFormat(String p_format) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(p_format);
        String dateStr = sdf.format(d);
        return dateStr;
    }

    /**
     * 获取指定日期格式系统日期的字符型日期
     *
     * @param p_format
     *          日期格式 格式1:"yyyy-MM-dd" 格式2:"yyyy-MM-dd hh:mm:ss EE"
     *          格式3:"yyyy年MM月dd日 hh:mm:ss EE" 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写
     * @return String 系统时间字符串
     */
    public static String getSystemOfDateByFormat(String p_format) {
        long time = System.currentTimeMillis();
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(p_format);
        String dateStr = sdf.format(d);
        return dateStr;
    }

    /**
     * 获取字符日期一个月的天数
     *
     * @param p_date
     * @return 天数
     */
    public static long getDayOfMonth(Date p_date) throws ParseException {
        int year = getYearOfDate(p_date);
        int month = getMonthOfDate(p_date) - 1;
        int day = getDayOfDate(p_date);
        int hour = getHourOfDate(p_date);
        int minute = getMinuteOfDate(p_date);
        int second = getSecondOfDate(p_date);
        Calendar l_calendar = new GregorianCalendar(year, month, day, hour, minute,
                second);
        return l_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // -----------------获取指定月份的第一天,最后一天
    // ---------------------------------------------------------------------------
    /**
     * 获取指定月份的第一天
     *
     * @param p_strDate
     *          指定月份
     * @param p_formate
     *          日期格式
     * @return String 时间字符串
     */
    public static String getDateOfMonthBegin(String p_strDate, String p_format)
            throws ParseException {
        Date date = toUtilDateFromStrDateByFormat(p_strDate, p_format);
        return toStrDateFromUtilDateByFormat(date, "yyyy-MM") + "-01";
    }

    /**
     * 获取指定月份的最后一天
     *
     * @param p_strDate
     *          指定月份
     * @param p_formate
     *          日期格式
     * @return String 时间字符串
     */
    public static String getDateOfMonthEnd(String p_strDate, String p_format)
            throws ParseException {
        Date date = toUtilDateFromStrDateByFormat(
                getDateOfMonthBegin(p_strDate, p_format), p_format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return toStrDateFromUtilDateByFormat(calendar.getTime(), p_format);
    }

    /**
     * 将日期时间字符串根据转换为指定时区的日期时间.
     * 
     * @param srcFormater
     *          待转化的日期时间的格式.
     * @param srcDateTime
     *          待转化的日期时间.
     * @param dstFormater
     *          目标的日期时间的格式.
     * @param destTimeZone
     *          目标的时区.
     * 
     * @return 转化后的日期时间.
     */
    public static String convertByTimezone(String srcFormater, String srcDateTime,
            String dstFormater, TimeZone destTimeZone) {
        if (srcFormater == null || "".equals(srcFormater))
            return null;
        if (srcDateTime == null || "".equals(srcDateTime))
            return null;
        if (dstFormater == null || "".equals(dstFormater))
            return null;
        if (destTimeZone == null)
            destTimeZone = TimeZone.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);
        try {
            int diffTime = getDiffTimeZoneRawOffset(destTimeZone);
            Date d = sdf.parse(srcDateTime);
            long nowTime = d.getTime();
            long newNowTime = nowTime - diffTime;
            d = new Date(newNowTime);
            return toStrDateFromUtilDateByFormat(d, dstFormater);
        } catch (ParseException e) {
            logger.error("", e);
            return null;
        } finally {
            sdf = null;
        }
    }
    
    /**
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
     * @param destTimeZone 目标时区
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
     */
    private static int getDiffTimeZoneRawOffset(TimeZone destTimeZone) {
        return getDiffTimeZoneRawOffset(TimeZone.getDefault(), destTimeZone);
    }
    
    /**
     * 获取源时区与目标时区的时间差.(单位:毫秒)
     * @param srcTimeZone 源时区
     * @param destTimeZone 目标时区
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
     */
    private static int getDiffTimeZoneRawOffset(TimeZone srcTimeZone, TimeZone destTimeZone) {
        return srcTimeZone.getRawOffset()
                - destTimeZone.getRawOffset();
    }

    /**
     * 将日期时间字符串根据转换为指定时区的日期时间.
     * 
     * @param srcDateTime
     *          待转化的日期时间.
     * @param destTimeZone
     *          目标时区.
     * 
     * @return 转化后的日期时间.
     * @see #convertByTimezone(String, String, String, TimeZone)
     */
    public static String convertByTimezone(String srcDateTime, String timeFormat,
            TimeZone destTimeZone) {
        if (timeFormat == null || timeFormat.trim().length() == 0) {
            timeFormat = DEFAULT_TIME_FORMAT;
        }
        return convertByTimezone(timeFormat, srcDateTime,
                timeFormat, destTimeZone);
    }
    
    /**
     * 将日期转换为制定时区的日期
     * @param srcDate 待转换的日期
     * @param destTimeZone 目标时区
     * @return
     */
    public static Date convertByTimezone(Date srcDate, TimeZone destTimeZone) {
        if (destTimeZone == null) {
            return srcDate;
        }
        return convertByTimezone(srcDate, TimeZone.getDefault(), destTimeZone);
    }
    
    /**
     * 将日期转换为制定时区的日期
     * @param srcDate 待转换的日期
     * @param srcTimeZone 待转换的日期所在的时区
     * @param destTimeZone 目标时区
     * @return
     */
    public static Date convertByTimezone(Date srcDate, TimeZone srcTimeZone, TimeZone destTimeZone) {
        if (srcTimeZone == null || destTimeZone == null) {
            return srcDate;
        }
        int diffTime = getDiffTimeZoneRawOffset(srcTimeZone, destTimeZone);
        Date destDate = new Date(srcDate.getTime() - diffTime);
        return destDate;
    }
}
