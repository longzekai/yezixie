package com.dou361.baseutils.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2015/12/26 22:17
 * <p>
 * 描 述：日期工具，String/long与date相互转换 两个日期的比较等
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * 输入日期获取对应的日期
     *
     * @param sdfTo    获取的日期类型，Date对象，long 毫秒值，String 字符串日期
     * @param defValue 输入要转换的日期，Date对象，long 毫秒值，String 字符串日期
     * @return 要求获取的类型的日期
     */
    public static Object getDate(String sdfTo, Date defValue) {
        return getDate(sdfTo, null, defValue);
    }

    /**
     * 输入日期获取对应的日期
     *
     * @param sdfTo    获取的日期类型，Date对象，long 毫秒值，String 字符串日期
     * @param defValue 输入要转换的日期，Date对象，long 毫秒值，String 字符串日期
     * @return 要求获取的类型的日期
     */
    public static Object getDate(String sdfTo, long defValue) {
        return getDate(sdfTo, null, defValue);
    }

    /**
     * 输入日期获取对应的日期
     *
     * @param sdfTo    获取的日期类型，Date对象，long 毫秒值，String 字符串日期
     * @param sdfFrom  输入要转换的日期类型，Date对象，long 毫秒值，String 字符串日期
     * @param defValue 输入要转换的日期，Date对象，long 毫秒值，String 字符串日期
     * @return 要求获取的类型的日期
     */
    public static Object getDate(String sdfTo, String sdfFrom, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        Date date;
        if ("Integer".equals(type) || "Long".equals(type)) {
            date = new Date((Long) defValue);
        } else if ("Date".equals(type)) {
            date = (Date) defValue;
        } else if ("String".equals(type)) {
            SimpleDateFormat sdf = new SimpleDateFormat(sdfFrom);
            try {
                date = sdf.parse((String) defValue);
            } catch (ParseException e) {
                throw new RuntimeException("the date type parse exception!");
            }

        } else {
            throw new RuntimeException("the date type illgel!");
        }
        if (DateType.DATE.equals(sdfTo)) {
            return date;
        } else if (DateType.DATETIME.equals(sdfTo)) {
            return date.getTime();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(sdfTo);
            return sdf.format(date);
        }

    }

    /**
     * 获取当前毫秒值
     */
    public static long getCurrentTimeMillis() {
        return new Date().getTime();
    }

    /**
     * 获取当前分的毫秒值忽略秒值 yyyy-mm-dd HH:mm
     */
    public static long getCurrentMinuteTimeMillis() {
        return (long) getDate(DateType.DATETIME, (Date) getDate(DateType.DATE, DateType.sdf_yyyy_MM_dd_HH_mm, new Date()));
    }

    /**
     * 比较两个日期的大小（-2转换异常0相等1大于-1小于）
     */
    public static int compareDate(Date dateF, Date dateS) {
        try {
            if (dateF.getTime() > dateS.getTime()) {
                return 1;
            } else if (dateF.getTime() < dateS.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return -2;
        }

    }

    /**
     * 获得指定年和月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        /** 把日期设置为当月第一天 */
        a.set(Calendar.DATE, 1);
        /** 日期回滚一天，也就是最后一天 */
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获得指定天在当月的天索引
     */
    public static int getDayOfMonth(Date date) {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.setTime(date);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }


    /**
     * 获取当天是星期几
     */
    public static String getDateToWeek(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "星期" + mWay;
    }


    /**
     * 获取今天的日期
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当天的年份
     *
     * @return
     */

    public static String getTodayYear() {
        String today = getToday();
        String year = today.substring(0, 4);
        return year;
    }

    /**
     * 获取当前日期的月数的位置
     *
     * @return
     */
    public static String getTodayMonth() {
        String today = getToday();
        String month = today.substring(5, 7);
        return month;
    }

    /**
     * 获取当前日期的天数的日子
     *
     * @return
     */
    public static String getTodayDay() {
        String today = getToday();
        String day = today.substring(8, 10);
        return day;
    }

    /**
     * 获取当前小时
     */
    public static String getTodayHour() {
        String today = getToday();
        String hour = today.substring(12, 14);
        return hour;
    }

    /**
     * 获取当前分钟
     */
    public static String getTodayMinute() {
        String today = getToday();
        String minute = today.substring(15, 17);
        return minute;
    }

    /**
     * 获取当前秒
     */
    public static String getTodaySecond() {
        String today = getToday();
        String second = today.substring(18, 20);
        return second;
    }

}
