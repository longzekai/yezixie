package com.dou361.baseutils.utils;

import android.content.Context;
import android.os.Handler;

/**
 * ========================================
 * <p>
 * 版 权：dou361 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2015/12/25
 * <p>
 * 描 述：用户sdk初始化配置，当前类是BaseUtils工具类的管理类，
 * 用户可以选择直接使用具体的工具类，或者通过初始化sdk来配置一些常用的对象，
 * applicationContext,主线程等来简化工具类的使用。
 * 例如：
 * 存储一个字符串未初始化前
 * SPUtils.put(mContext,key,value);
 * 初始化applicationContext之后可以简化为
 * SPUtils.put(key,value);
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class UtilsManager {

    private String TAG = this.getClass().getSimpleName();
    private static UtilsManager instance;
    /**
     * Application中的上下文
     */
    private Context appContext;
    /**
     * sdk生成的用户key
     */
    private String userKey;
    /**
     * 主线程ID
     */
    private int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private Thread mMainThread;
    /**
     * 主线程Handler
     */
    private Handler mMainThreadHandler;

    private UtilsManager() {
    }

    public static UtilsManager getInstance() {
        if (instance == null) {
            instance = new UtilsManager();
        }
        return instance;
    }

    /**
     * 初始化sdk，需要在Application的oncreate()方法中调用,userKey为sdk的key，当前还没有用到，可空
     */
    public UtilsManager init(Context appContext, String userKey, Handler mMainThreadHandler, Thread mMainThread) {
        this.appContext = appContext;
        this.userKey = userKey;
        this.mMainThread = mMainThread;
        this.mMainThreadId = mMainThread == null ? -1 : (int) mMainThread.getId();
        this.mMainThreadHandler = mMainThreadHandler;
        return this;
    }

    /**
     * 获取程序Application中的上下文
     */
    public Context getAppContext() {
        if (appContext == null) {
            throw new RuntimeException(TAG + " not initialized or appContext is null");
        }
        return appContext;
    }

    /**
     * 获取主线程ID
     */
    public int getMainThreadId() {
        if (mMainThreadId <= 0) {
            throw new RuntimeException(TAG + " not initialized or mMainThread is null");
        }
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public Thread getMainThread() {
        if (mMainThread == null) {
            throw new RuntimeException(TAG + " not initialized or mMainThread is null");
        }
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public Handler getMainThreadHandler() {
        if (mMainThreadHandler == null) {
            throw new RuntimeException(TAG + " not initialized or mMainThreadHandler is null");
        }
        return mMainThreadHandler;
    }

    /**
     * 设置一级标签名称 默认是dou361
     */
    public UtilsManager setFristTag(String flag) {
        LogUtils.setFristTag(flag);
        return this;
    }

    /**
     * 设置开发环境 默认是false,正式环境无日志输出
     */
    public UtilsManager setDebugEnv(boolean flag) {
        LogUtils.setPrintLog(flag);
        return this;
    }

    /**
     * 设置日志输出等级 默认是log.e()等级输出
     */
    public UtilsManager setLogLevel(int flag) {
        LogUtils.setLogLevel(flag);
        return this;
    }

    /**
     * 设置sputils中字符串的加密key
     */
    public UtilsManager setCryptKey(String key) {
        CryptUtil.setCryptKey(key);
        return this;
    }

}
