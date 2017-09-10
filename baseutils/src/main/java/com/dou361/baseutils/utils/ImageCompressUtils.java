package com.dou361.baseutils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
 * 创建日期：2017/4/19 23:22
 * <p>
 * 描 述：图片压缩
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class ImageCompressUtils {

    private ImageCompressUtils() {
    }

    /**
     * 质量压缩方法
     */
    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /** 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中 */
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        /** 循环判断如果压缩后图片是否大于100kb,大于继续压缩 */
        while (baos.toByteArray().length / 1024 > 400) {
            /** 重置baos即清空baos */
            baos.reset();
            /** 这里压缩options%，把压缩后的数据存放到baos中 */
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            /** 每次都减少10 */
            options -= 10;
        }
        /** 把压缩后的数据baos存放到ByteArrayInputStream中 */
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        /** 把ByteArrayInputStream数据生成图片 */
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap image, String strPath) {
        File f = new File(strPath);
        if (f.exists()) {
            f.delete();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        ByteArrayOutputStream baos11 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos11);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos11.toByteArray().length / 1024 > 400) { // 循环判断如果压缩后图片是否大于400kb,大于继续压缩
            baos11.reset();// 重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos11);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBms = new ByteArrayInputStream(
                baos11.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap imagebitmap = BitmapFactory.decodeStream(isBms, null, null);// 把ByteArrayInputStream数据生成图片

        try {
            FileOutputStream out = new FileOutputStream(f);
            imagebitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）：
     */
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        /** 开始读入图片，此时把options.inJustDecodeBounds 设回true了 */
        newOpts.inJustDecodeBounds = true;
        /** 此时返回bm为空 */
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        /** 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为 */
        /** 这里设置高度为800f */
        float hh = 800f;
        /** 这里设置宽度为480f */
        float ww = 480f;
        /** 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可 */
        /** be=1表示不缩放 */
        int be = 1;
        if (w > h && w > ww) {
            /** 如果宽度大的话根据宽度固定大小缩放 */
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            /** 如果高度高的话根据宽度固定大小缩放 */
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        /** 设置缩放比例 */
        newOpts.inSampleSize = be;
        /** 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了 */
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        /** 压缩好比例大小后再进行质量压缩 */
        return compressImage(bitmap);
    }

    /**
     * 图片按比例大小压缩方法（根据Bitmap图片压缩）：
     */
    public static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        /** 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出 */
        if (baos.toByteArray().length / 1024 > 1024) {
            /** 重置baos即清空baos */
            baos.reset();
            /** 这里压缩50%，把压缩后的数据存放到baos中 */
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        /** 开始读入图片，此时把options.inJustDecodeBounds 设回true了 */
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        /** 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为 */
        /** 这里设置高度为800f */
        float hh = 800f;
        /** 这里设置宽度为480f */
        float ww = 480f;
        /** be=1表示不缩放 */
        int be = 1;
        /** 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可 */
        if (w > h && w > ww) {
            /** 如果宽度大的话根据宽度固定大小缩放 */
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            /** 如果高度高的话根据宽度固定大小缩放 */
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        /** 设置缩放比例 */
        newOpts.inSampleSize = be;
        /** 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了 */
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        /** 压缩好比例大小后再进行质量压缩 */
        return compressImage(bitmap);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    public static Drawable BitmapTodrawable(Bitmap bitmap) {

        return new BitmapDrawable(bitmap);

    }

}
