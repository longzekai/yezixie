package com.dou361.baseutils.utils;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：chenguanming
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/11/16 15:49
 * <p/>
 * 描 述：打印日志的LogUtils的包装类，打印信息中包含其对应的类名称
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class LogTagName {

    /**
     * 二级Tag
     */
    private String secondTag;

    public void setSecondTag(String secondTag) {
        this.secondTag = secondTag;
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG
     */
    public void log(String msg) {
        LogUtils.log(secondTag + msg);
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG
     */
    public void log(Throwable e) {
        LogUtils.log(secondTag, e);
    }

    /**
     * 根据当前级别mDebuggable的形式输出LOG
     */
    public void log(String msg, Throwable e) {
        LogUtils.log(secondTag + msg, e);
    }

}