package com.dou361.baseutils.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;

import java.io.File;

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
 * 创建日期：2017/4/19 23:21
 * <p>
 * 描 述：Android各版本的兼容方法
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class MethodsCompat {
	
    @TargetApi(5)
    public static void overridePendingTransition(Activity activity, int enter_anim, int exit_anim) {
       	activity.overridePendingTransition(enter_anim, exit_anim);
    }

    @TargetApi(7)
    public static Bitmap getThumbnail(ContentResolver cr, long origId, int kind, Options options) {
       	return MediaStore.Images.Thumbnails.getThumbnail(cr,origId,kind, options);
    }
    
    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {

//	    // return context.getExternalCacheDir(); API level 8
//
//	    // e.g. "<sdcard>/Android/data/<package_name>/cache/"
//	    final File extCacheDir = new File(Environment.getExternalStorageDirectory(),
//	        "/Android/data/" + context.getApplicationInfo().packageName + "/cache/");
//	    extCacheDir.mkdirs();
//	    return extCacheDir;

        return context.getExternalCacheDir();
    }

    @TargetApi(11)
    public static void recreate(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        }
    }

    @TargetApi(11)
    public static void setLayerType(View view, int layerType, Paint paint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setLayerType(layerType, paint);
        }
    }

    @TargetApi(14)
    public static void setUiOptions(Window window, int uiOptions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            window.setUiOptions(uiOptions);
        }
    }
        
}