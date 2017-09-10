package com.dou361.baseutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
 * 创建日期：2015/12/20 23:15
 * <p>
 * 描 述：偏好设置
 * 1.默认偏好文件
 * SPUtils.putData(context,key,defValue);
 * 2.自定义偏好文件,@param spName 文件名称
 * SPUtils.putData(context,spName,key,defValue);
 * SPUtils.spTagName(spName).putData(context,key,defValue);
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class SPUtils {

    /**
     * 使用单例模式来创建共享对象的变量
     */
    private static SharedPreferences mSharedPreferences;
    /**
     * 自定义创建共享对象的变量
     */
    private static SharedPreferences mCustomSP;
    /**
     * 当前选择的对象
     */
    private static SharedPreferences currentSP;
    /**
     * 共享文件的名称
     */
    private static final String SP_NAME = "config";

    private SPUtils() {
    }

    /**
     * 初始化SharedPreferences
     */
    private static void init(Context context, String spName) {
        if (SP_NAME.equals(spName)) {
            /** 默认的对象 */
            if (mSharedPreferences == null) {
                mSharedPreferences = context.getSharedPreferences(spName,
                        Context.MODE_MULTI_PROCESS);
            }
        } else {
            if (!(mCustomSP != null && spName.equals(mCustomSP.getString(spName, "")))) {
                /** 为空或者是不同对象 */
                mCustomSP = context.getSharedPreferences(spName,
                        Context.MODE_MULTI_PROCESS);
                mCustomSP.edit().putString(spName, spName).commit();
            }
        }
    }

    /**
     * 添加二级Tag，该方法需要在打印日志的类中获得类对象，比较方便的是在类的父类中获取即可
     */
    public static synchronized SPTagName spTagName(String spName) {
        SPTagName mTagName = new SPTagName();
        mTagName.setSpName(spName);
        return mTagName;
    }

    /**
     * 使用默认文件添加键的内容
     */
    public static void putData(String key, Object data) {
        putData(UIUtils.getContext(), SP_NAME, key, data);
    }

    /**
     * 使用默认文件添加键的内容
     */
    public static void putData(Context context, String key, Object data) {
        putData(context, SP_NAME, key, data);
    }

    /**
     * 使用默认文件添加键的内容
     */
    public static void putData(String spName, String key, Object data) {
        putData(UIUtils.getContext(), spName, key, data);
    }

    /**
     * 自定义文件添加键的内容
     */
    public static void putData(Context context, String spName, String key, Object data) {
        String type = data.getClass().getSimpleName();
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        if ("Integer".equals(type)) {
            currentSP.edit().putInt(key, (Integer) data).commit();
        } else if ("Boolean".equals(type)) {
            currentSP.edit().putBoolean(key, (Boolean) data).commit();
        } else if ("String".equals(type)) {
            currentSP.edit().putString(key, CryptUtil.encrypt((String) data)).commit();
        } else if ("Float".equals(type)) {
            currentSP.edit().putFloat(key, (Float) data).commit();
        } else if ("Long".equals(type)) {
            currentSP.edit().putLong(key, (Long) data).commit();
        }
        currentSP = null;
    }

    /**
     * 使用默认文件获得键的内容
     */
    public static Object getData(String key, Object defValue) {
        return getData(UIUtils.getContext(), SP_NAME, key, defValue);
    }

    /**
     * 使用默认文件获得键的内容
     */
    public static Object getData(Context context, String key, Object defValue) {
        return getData(context, SP_NAME, key, defValue);
    }

    /**
     * 使用默认文件获得键的内容
     */
    public static Object getData(String spName, String key, Object defValue) {
        return getData(UIUtils.getContext(), spName, key, defValue);
    }

    /**
     * 自定义文件获得键的内容
     */
    public static Object getData(Context context, String spName, String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        if ("Integer".equals(type)) {
            return currentSP.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return currentSP.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return CryptUtil.decrypt(currentSP.getString(key, (String) defValue));
        } else if ("Float".equals(type)) {
            return currentSP.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return currentSP.getLong(key, (Long) defValue);
        }
        currentSP = null;
        return null;
    }

    /**
     * 使用默认文件删除某个键的内容
     */
    public static void remove(String key) {
        remove(UIUtils.getContext(), key);
    }

    /**
     * 使用默认文件删除某个键的内容
     */
    public static void remove(Context context, String key) {
        remove(context, SP_NAME, key);
    }

    /**
     * 自定义文件删除某个键的内容
     */
    public static void remove(String spName, String key) {
        remove(UIUtils.getContext(), spName, key);
    }

    /**
     * 自定义文件删除某个键的内容
     */
    public static void remove(Context context, String spName, String key) {
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        currentSP.edit().remove(key).commit();
        currentSP = null;
    }

    /**
     * 使用默认文件清空所有的键值对
     */
    public static void clearAll() {
        clearAll(UIUtils.getContext(), SP_NAME);
    }

    /**
     * 使用默认文件清空所有的键值对
     */
    public static void clearAll(Context context) {
        clearAll(context, SP_NAME);
    }

    /**
     * 自定义文件清空所有的键值对
     */
    public static void clearAll(String spName) {
        clearAll(UIUtils.getContext(), spName);
    }

    /**
     * 自定义文件清空所有的键值对
     */
    public static void clearAll(Context context, String spName) {
        init(context, spName);
        if (SP_NAME.equals(spName)) {
            currentSP = mSharedPreferences;
        } else {
            currentSP = mCustomSP;
        }
        currentSP.edit().clear().commit();
        currentSP = null;
    }

}
