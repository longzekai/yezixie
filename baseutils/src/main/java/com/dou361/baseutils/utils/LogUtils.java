package com.dou361.baseutils.utils;

import android.util.Log;

import java.util.List;

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
 * 创建日期：2015/12/26 17:13
 * <p>
 * 描 述：日志输出工具类
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class LogUtils {


    /**
     * 日志输出时的TAG
     */
    private static String mTag = "dou361";
    /**
     * 当前日志输出级别
     */
    private static int mDebuggable = LogType.LEVEL_NONE;
    /**
     * 当前日志是否输出
     */
    private static boolean mIsPrintLog = false;

    /**
     * 设置一级标签名称
     */
    public static void setFristTag(String flag) {
        mTag = flag;
    }

    /**
     * 设置日志输出状态
     */
    public static void setPrintLog(boolean flag) {
        mIsPrintLog = flag;
    }

    /**
     * 设置日志输出级别
     */
    public static void setLogLevel(int flag) {
        mDebuggable = flag;
    }

    /**
     * 用于记时的变量
     */
    private static long mTimestamp = 0;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    private LogUtils() {
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG，并且打印异常信息
     */
    public static void log(String msg, Throwable e) {
        if (mIsPrintLog) {
            switch (mDebuggable) {
                case LogType.LEVEL_NONE:
                    Log.d(mTag, msg, e);
                    break;
                case LogType.LEVEL_VERBOSE:
                    Log.v(mTag, msg, e);
                    break;
                case LogType.LEVEL_DEBUG:
                    Log.d(mTag, msg, e);
                    break;
                case LogType.LEVEL_INFO:
                    Log.i(mTag, msg, e);
                    break;
                case LogType.LEVEL_WARN:
                    Log.w(mTag, msg, e);
                    break;
                case LogType.LEVEL_ERROR:
                    Log.e(mTag, msg, e);
                    break;
            }
        }
    }

    /**
     * 添加二级Tag，该方法需要在打印日志的类中获得类对象，比较方便的是在类的父类中获取即可
     */
    public static synchronized LogTagName logTagName(String tag) {
        LogTagName mLogTagName = new LogTagName();
        mLogTagName.setSecondTag(tag + "|<----->|>>");
        return mLogTagName;
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG
     */
    public static void log(String msg) {
        log(msg, null);
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG
     */
    public static void log(Throwable e) {
        log("", e);
    }

    /**
     * 输出msg信息,附带时间戳，用于输出一个时间段起始点 @param msg 需要输出的msg
     */
    public static void logStart(String msg) {
        mTimestamp = System.currentTimeMillis();
        log("[Started：" + mTimestamp + "]" + msg);
    }

    /**
     * 输出msg信息,附带时间戳，用于输出一个时间段结束点* @param msg 需要输出的msg
     */
    public static void logElapsed(String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mTimestamp;
        mTimestamp = currentTime;
        log("[Elapsed：" + elapsedTime + "]" + msg);
    }

    /**
     * 把Log存储到文件中，默认追加 @param log 需要存储的日志 @param path 存储路径
     */
    public static void logToFile(String log, String path) {
        logToFile(log, path, true);
    }

    /**
     * 把Log存储到文件中 @param log 需要存储的日志 @param path 存储路径 @param append 是否追加
     */
    public static void logToFile(String log, String path, boolean append) {
        synchronized (mLogLock) {
            FileUtils.writeFile(log + "\r\n", path, append);
        }
    }

    /**
     * 打印list
     */
    public static <T> void printList(List<T> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        int size = list.size();
        log("---begin---");
        for (int i = 0; i < size; i++) {
            log(i + ":" + list.get(i).toString());
        }
        log("---end---");
    }

    /**
     * 打印array
     */
    public static <T> void printArray(T[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        int length = array.length;
        log("---begin---");
        for (int i = 0; i < length; i++) {
            log(i + ":" + array[i].toString());
        }
        log("---end---");
    }

}
