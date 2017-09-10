package com.dou361.baseutils.utils;

import android.content.Context;

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
 * 创建日期：2016/11/16 15:51
 * <p/>
 * 描 述：共享文件的SPUtils的包装类，打印信息中包含其对应的类名称
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class SPTagName {

    /**
     * 自定义共享文件名，相当于新开一个
     */
    private String spName;

    public void setSpName(String spName) {
        this.spName = spName;
    }

    /**
     * 自定义文件添加键的内容
     */
    public void putData(String key, Object value) {
        SPUtils.putData(UIUtils.getContext(), spName, key, value);
    }

    /**
     * 自定义文件添加键的内容
     */
    public void putData(Context context, String key, Object value) {
        SPUtils.putData(context, spName, key, value);
    }

    /**
     * 自定义文件获得键的内容
     */
    public Object getData(String key,
                          Object defValue) {
        return SPUtils.getData(UIUtils.getContext(), spName, key, defValue);
    }

    /**
     * 自定义文件获得键的内容
     */
    public Object getData(Context context, String key,
                          Object defValue) {
        return SPUtils.getData(context, spName, key, defValue);
    }

    /**
     * 自定义文件删除某个键的内容
     */
    public void remove(String key) {
        SPUtils.remove(UIUtils.getContext(), spName, key);
    }

    /**
     * 自定义文件删除某个键的内容
     */
    public void remove(Context context, String key) {
        SPUtils.remove(context, spName, key);
    }

    /**
     * 自定义文件清空所有的键值对
     */
    public void clearAll() {
        SPUtils.clearAll(UIUtils.getContext(), spName);
    }

    /**
     * 自定义文件清空所有的键值对
     */
    public void clearAll(Context context) {
        SPUtils.clearAll(context, spName);
    }

}