package com.yezixie.yezixie.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

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
 * 创建日期：$date$ $time$
 * <p>
 * 描 述：$desc$
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class LocaleLanguageUtils {

    /**
     * 重启应用
     * @param context 当前页面
     * @param clazz 需要回到的首页
     */
    public static void reStartApp(Context context, Class<?> clazz){
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 切换语言
     * @param context 当前上下文
     * @param localeLanguage 要切换到的语言
     */
    public static void changeLanguage(Context context,Locale localeLanguage){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        config.locale = localeLanguage;
        resources.updateConfiguration(config, dm);
    }

}
