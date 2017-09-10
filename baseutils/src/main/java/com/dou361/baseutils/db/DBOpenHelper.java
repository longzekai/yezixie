package com.dou361.baseutils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
 * 创建日期：2015/11/17
 * <p>
 * 描 述：数据库帮助工具
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /**
     * 默认数据库
     */
    private static String defalut_dbName = "dou361.db";
    /**
     * 默认版本号
     */
    private static int defalut_dbVersion = 1;
    /**
     * 表创建语句
     */
    private String strSql;

    /**
     * @param context 上下文
     */
    public DBOpenHelper(Context context) {
        this(context, defalut_dbName, null, defalut_dbVersion);
    }

    /**
     * @param context 上下文
     * @param dbName  数据库
     */
    public DBOpenHelper(Context context, String dbName) {
        this(context, dbName, null, defalut_dbVersion);
    }

    /**
     * @param context 上下文
     * @param dbName  数据库
     * @param sql     表创建语句
     */
    public DBOpenHelper(Context context, String dbName, String sql) {
        this(context, dbName, sql, defalut_dbVersion);
    }

    /**
     * @param context 上下文
     * @param dbName  数据库
     * @param sql     表创建语句
     * @param version 版本号
     */
    public DBOpenHelper(Context context, String dbName, String sql, int version) {
        super(context, dbName, null, version);
        this.strSql = sql;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 初始化表结构
         */
        if (strSql != null && !"".equals(strSql)) {
            db.execSQL(strSql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
